import java.util.List;
import java.util.*;
public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() {
		super();
	}

	public BacktrackingBTree(int order) {
		super(order);
	}

	//You are to implement the function Backtrack.
	public void Backtrack() {
		if (deck.isEmpty()){
			return;
		}
		T toRemove=(T)deck.pop();
		Integer splitInd=(Integer)deck.pop();
		if (splitInd==0){   //CASE 1: there were no splits during the insert.
			Node nodeVal=getNode(toRemove);
			nodeVal.removeKey(toRemove);
		}
		else {
			Node nodeVal=getNode(toRemove);
			nodeVal.removeKey(toRemove);
			while (splitInd!=0){
				splitInd=splitInd-1;
				T up=(T) deck.pop();
				Node node1=getNode(up);
				Integer upIndex=node1.indexOf(up);
				Node child1=node1.getChild(upIndex);
				Node child2=node1.getChild(upIndex+1);
				for (int i=child2.getNumberOfKeys()-1;i>=0;i=i-1){
					Integer kid= (Integer)child2.getKey(i);
					child1.addKey(kid);
				}
				for (int i=0;i< child2.getNumberOfChildren();i=i+1){
					child1.children[child1.getNumberOfChildren()]=child2.children[i];
					child1.numOfChildren=child1.numOfChildren+1;
				}
				child1.addKey(up);
				node1.removeKey(up);
				node1.removeChild(child2);
				if (node1.numOfKeys==0){
					Node temp=node1.parent;
					node1=child1;
					node1.parent=temp;
				}
			}
		}
		size=size-1;
		if (root.numOfKeys==0){
			if (root.getNumberOfChildren()==0){
				root=null;
			}
			else{
				root=root.getChild(0);
			}
		}
    }
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
		List<Integer> lst = new LinkedList<>();
		for (int i=1;i<=6;i=i+1){
			lst.add(i);
		}
		return lst;
	}
}
