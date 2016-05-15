import java.util.List;
import java.util.ArrayList;

class PrintAncestors {

    static class Node {
 
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static void printAncestors(Node root, int data) {

        for(Node node : findAncestors(root, data)) {
            System.out.println( node.data );
        }
    }

    private static List<Node> findAncestors(Node subTree, int data) {

         List<Node> retList = new ArrayList<Node>();

         if (subTree == null) {
             	return retList;
         }

         if ( subTree.left.data == data || subTree.right.data == data) {
		retList.add(subTree);
		return retList;
         }

	 List<Node> subTreeList = findAncestors(subTree.left, data);
	 if (subTreeList.isEmpty()) {
             subTreeList = findAncestors(subTree.right, data);
         }

         if (!subTreeList.isEmpty() ) {
	     retList.addAll(subTreeList);
             retList.add(subTree);
	 }
	 return retList;
    }

    public static void main(String args[]) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.left.left.left = new Node(7);
 
        printAncestors(tree, 7);
    }
}
