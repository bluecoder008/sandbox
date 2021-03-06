
/**
 * Class that finds the largest element that is smaller than a number S,
 * in a BST root
 *
 */

class Node {

    int data;
    Node right, left;

    Node(int data) {
        this.data = data;
    }
}


class BSTLargestElemSmallerThan {

     public static void main(String[] args) {
         Node root = new Node(4);
	 root.left = new Node(2);
         root.right = new Node(8);
         root.left.left = new Node(1);
         root.left.right = new Node(3);
         root.right.left = new Node(7);

	 BSTLargestElemSmallerThan obj = new BSTLargestElemSmallerThan();
         Node result = obj.findLEST(root, 1);
	 System.out.println("Result is: " + (result != null ? result.data : null) + " for S=1");

	 obj = new BSTLargestElemSmallerThan();
         result = obj.findLEST(root, 6);
	 System.out.println("Result is: " + (result != null ? result.data : null) + " for S=6");

	 obj = new BSTLargestElemSmallerThan();
         result = obj.findLEST(root, 9);
	 System.out.println("Result is: " + (result != null ? result.data : null) + " for S=9");

	 obj = new BSTLargestElemSmallerThan();
         result = obj.findLEST(root, 7);
	 System.out.println("Result is: " + (result != null ? result.data : null) + " for S=7");
    }

    Node findLEST(Node root, int S) {
        if ( root == null ) {
            return null;
        }
	Node result = findLEST(root.right, S);
	if ( result != null ) {
            return result;
	}
        if ( root.data < S ) {
            return root;
        }
        return findLEST(root.left, S);
    }
}
