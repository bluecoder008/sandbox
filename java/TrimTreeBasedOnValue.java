import java.util.*;

class TrimTreeBasedOnValue {

    static class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) {
            this.value = value;
        }
        @Override
        public String toString() {
           return "{ 'value': " + value + ",\n" + "'left': " + left + ",\n" + "'right': " + right + "}\n";
        }
    }

    static boolean shouldRemove(TreeNode node) {
       return node.value % 2 == 0;
    }

    /*
             5
            / \
           2   7
          / \   \
         4   1   9

       returns: 

       [ 5  ,  1 ]
          \
           7
            \
             9
     */

    static TreeNode trimBasedOnValue(TreeNode root, TreeNode parent, List<TreeNode> results) {

        if (root == null) {
            return null;
        }

        if (shouldRemove(root)) {
            if (parent != null) {
                if (parent.left == root) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            trimBasedOnValue(root.left, null, results);
            trimBasedOnValue(root.right, null, results);
            return null;
        }
        // not to remove
        trimBasedOnValue(root.left, root, results);
        trimBasedOnValue(root.right, root, results);
        if (parent == null) {
            results.add(root);
        }
        return root;
    }
    
    static public void main(String[] args) {

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(9);
        List<TreeNode> results = new LinkedList<>();
        System.out.println("root: " + root);
        trimBasedOnValue(root, null, results);
        assert results.size() == 2;
        System.out.println("results[0]:" + results.get(0));
        System.out.println("results[1]:" + results.get(1));
    }
}
