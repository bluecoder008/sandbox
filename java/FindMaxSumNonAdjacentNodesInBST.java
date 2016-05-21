
/**
 * Problem: In a BST, find the set of nodes that are non-adjacent which gives max value of sum of the nodes
 *          (the values for Tree nodes could be 0 or negative)
 *
 */

class FindMaxSumNonAdjacentNodesInBST {
    
     static int FindMaxSumNonAdjacentNodes(TreeNode<Integer> root, Boolean includeRoot) {

         if ( root == null ) {
             return 0;
         }
	
	 if ( includeRoot == null ) {
             return Math.max( FindMaxSumNonAdjacentNodes(root, true),
                              FindMaxSumNonAdjacentNodes(root, false) );
         }

         if ( includeRoot == false ) {
		return FindMaxSumNonAdjacentNodes(root.left, null) + FindMaxSumNonAdjacentNodes(root.right, null);
         }

	return root.data + FindMaxSumNonAdjacentNodes(root.left, false) + FindMaxSumNonAdjacentNodes(root.right, false);
     }

     public static void main(String[] args) {
     
         TreeNode<Integer> root = new TreeNode<>(1);
         root.left = new TreeNode<Integer>(2);
         root.right = new TreeNode<Integer>(3);

         root.left.left = new TreeNode<Integer>(4);
         root.left.right = new TreeNode<Integer>(8);

         root.right.right = new TreeNode<Integer>(7);

	 System.out.println("Max sum: " + FindMaxSumNonAdjacentNodes(root,null) );
     }
}
