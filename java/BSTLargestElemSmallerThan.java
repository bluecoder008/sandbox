
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
         obj.findLEST(root, 1);
         Node result = obj.getResult();
	 System.out.println("Result is: " + (result != null ? result.data : null) + " for S=1");

	 obj = new BSTLargestElemSmallerThan();
         obj.findLEST(root, 6);
         result = obj.getResult();
	 System.out.println("Result is: " + (result != null ? result.data : null) + " for S=6");

	 obj = new BSTLargestElemSmallerThan();
         obj.findLEST(root, 9);
         result = obj.getResult();
	 System.out.println("Result is: " + (result != null ? result.data : null) + " for S=9");
    }

    Node result = null;

    void findLEST(Node root, int S) {

        if ( root == null ) {
            return;
        }

        findLEST(root.right, S);

	if ( result != null ) {
            return;
	}

        if ( root.data < S ) {
            result = root;
            return;
        }

        findLEST(root.left, S);
    }
   
    Node getResult() {
        return result;
    }
}
