import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        if (deck.isEmpty()){
            return;
        }
        int rotationType=(int)deck.pop();
        Node unBalancedNode=(Node)deck.pop();
        Node toRemove=(Node)deck.pop();
        Node parent=toRemove.parent;
        if (rotationType==0){
            removeNode(toRemove);
        }
        else if (rotationType==1){ //We need to rotate left.
            if (unBalancedNode.parent==null){
                root=rotateLeft(unBalancedNode);
            }
            else if (unBalancedNode.parent.right==unBalancedNode){
                unBalancedNode.parent.right=rotateLeft(unBalancedNode);
            }
            else{
                unBalancedNode.parent.left=rotateLeft(unBalancedNode);
            }
            removeNode(toRemove);
        }
        else if (rotationType==4){ //We need to rotate right.
            if (unBalancedNode.parent==null){
                root=rotateRight(unBalancedNode);
            }
            else if (unBalancedNode.parent.right==unBalancedNode){
                unBalancedNode.parent.right=rotateRight(unBalancedNode);
            }
            else {
                unBalancedNode.parent.left=rotateRight(unBalancedNode);
            }
            removeNode(toRemove);
        }
        else if (rotationType==2) { //We need to rotate left-right.
            if (unBalancedNode.parent==null){
                root=rotateLeft(unBalancedNode);
            }
            else if (unBalancedNode.parent.right==unBalancedNode){
                unBalancedNode.parent.right=rotateLeft(unBalancedNode);
            }
            else {
                unBalancedNode.parent.left=rotateLeft(unBalancedNode);
            }
            if (unBalancedNode.parent==null){
                root=rotateRight(unBalancedNode);

            }
            else if (unBalancedNode.parent.right==unBalancedNode){
                unBalancedNode.parent.right=rotateRight(unBalancedNode);
            }
            else {
                unBalancedNode.parent.left=rotateRight(unBalancedNode);
            }

            removeNode(toRemove);
        }
        else {  //We need to rotate right-left.
            if (unBalancedNode.parent==null){
                root=rotateRight(unBalancedNode);
            }
            else if (unBalancedNode.parent.right==unBalancedNode){
                unBalancedNode.parent.right=rotateRight(unBalancedNode);
            }
            else {
                unBalancedNode.parent.left=rotateRight(unBalancedNode);
            }
            if (unBalancedNode.parent==null){
                root=rotateLeft(unBalancedNode);

            }
            else if (unBalancedNode.parent.right==unBalancedNode){
                unBalancedNode.parent.right=rotateLeft(unBalancedNode);
            }
            else {
                unBalancedNode.parent.left=rotateLeft(unBalancedNode);
            }

            removeNode(toRemove);
        }
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        List<Integer> lst=new LinkedList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);
        return lst;
    }
    
    public int Select(int index) {
        if (root==null){
            throw new IllegalArgumentException();
        }
        Node temp=root;
        boolean isFound=false;
        int curr=0;
        while (temp!=null&!isFound){
            if (temp.left==null){
                curr=1;
            }
            else{
                curr=temp.left.size+1;
            }
            if (index==curr){
                isFound=true;
            }
            else if (index<curr){
                temp=temp.left;
            }
            else if (index>curr){
                temp=temp.right;
                index=index-curr;
            }
        }

        return temp.value;
    }

    public int Rank(int value) {
        if (root==null){ // If tree is empty.
            return 0;
        }
        Node temp=root;
        int curr=0;
        int res=0;
        boolean isFound=false;
        while (!isFound){
            if (temp.left==null){
                curr=0;
            }
            else{
                curr=temp.left.size;
            }
            if (temp.value==value){ //if this is the correct node
                return res+curr;
            }
            else if (value<temp.value){ //if we go left
                if (temp.left==null){ //left doesnt exist
                    return res;
                }
                else{
                    temp=temp.left;
                }
            }
            else{            //if we go right
                if(temp.right==null){
                    return res+curr+1;
                }
                else {
                    temp=temp.right;
                    res=res+curr+1;
                }
            }
        }
    return res;
    }

    public void removeNode(Node node){
        Node parent=node.parent;
        if (parent==null){
            root=null;
            return;
        }
        if (parent.left!=null&&parent.left.value == node.value){
            parent.left=null;
        }
        else{
            parent.right=null;
        }
        boolean isDifferent=true;
        while (parent!=null&isDifferent){  //We updated the heights as necessary.
            int h=parent.height;
            int s=parent.size;
            parent.updateHeight();
            parent.updateSize();
            isDifferent= (h!=parent.height)|(s!=parent.size);
            parent=parent.parent;
        }
    }
}
