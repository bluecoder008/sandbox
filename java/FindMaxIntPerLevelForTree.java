import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int data) {
        this.data = data;
        left = right = null;
    }

}


class FindMaxIntPerLevelForTree {


    static List<Integer> findMaxIntPerLevel(TreeNode root) {

        List<Integer> retList = new ArrayList<Integer>();
        Queue<TreeNode> A = new LinkedList<TreeNode>();
        Queue<TreeNode> B = new LinkedList<TreeNode>();

        A.add(root);
        while (A.isEmpty() == false) {
            int max = Integer.MIN_VALUE;
            while(A.isEmpty() == false) {
                TreeNode current = A.remove();
                if (current.left != null) {
                    B.add(current.left);
                }
                if (current.right != null) {
                    B.add(current.right);
                }
                if (current.data > max) {
                    max = current.data;
                }
            }

            retList.add(max);
            Queue<TreeNode> tmp = A;
            A = B;
            B = tmp;
        }
        return retList;
    }


    public static void main(String[] args) {

         TreeNode root = new TreeNode(6);
         root.left = new TreeNode(2);
         root.right = new TreeNode(4);
         root.left.left = new TreeNode(4);
         root.left.right = new TreeNode(10);
         root.right.left = new TreeNode(2);
         root.right.right = new TreeNode(20);
         root.right.right.left = new TreeNode(86);

         int n = 0;
         for(Integer m : findMaxIntPerLevel(root)) {
             System.out.println(String.format("max for level%d is %d", n, m));
             n++;
         }
    }
}
