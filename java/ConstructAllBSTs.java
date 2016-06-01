
import java.util.ArrayList;
import java.util.List;

/**
 * This class constructs all possible BST's given a list of integers (1,2,...N) for which
 * each integer is assigned to one of the nodes in a tree.
 *
 */
public class ConstructAllBSTs {

    static class TreeNode {
        int data;
        TreeNode left, right;
        
        TreeNode(int data) {
            this.data = data;
        }

        public String toString() {
           return "[" + data + " " + left + " , " + right + "]";
        }
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        List<Integer> numList = new ArrayList<>();

	System.out.print("input: [");
        for(int n = 1; n <= N; n++) {
            numList.add(n);
            System.out.print(" " + n);
        }
	System.out.println(" ]");

        List<TreeNode> trees = enumerateTrees(numList);

        trees.forEach(tree -> System.out.println(tree));
    }

    static List<TreeNode> enumerateTrees(List<Integer> integers) {

	//System.out.println("integer list: " + integers.toString());
        List<TreeNode> retList = new ArrayList<>();

        if (integers.size() == 0) {
            retList.add(null);
        } else {
            for(int n = 0; n < integers.size(); n++) {
		//System.out.println("picking " + integers.get(n) + " as root...");
		List<Integer> leftList = (n == 0 ? new ArrayList<Integer>() : new ArrayList<Integer>( integers.subList(0,n) ) );
		//System.out.println("leftlist " + leftList );
                List<TreeNode> leftTrees = enumerateTrees( leftList );
		List<Integer> rightList = (n == (integers.size() - 1) ? new ArrayList<Integer>() : new ArrayList<Integer>( integers.subList(n+1,integers.size())) );
		//System.out.println("rightlist " + rightList );
		List<TreeNode> rightTrees = enumerateTrees( rightList );
		for(TreeNode left : leftTrees ) {
                    for(TreeNode right : rightTrees ) {
                	TreeNode newTree = new TreeNode(integers.get(n));
                        newTree.left = left;
                        newTree.right = right;
                        retList.add(newTree);
                    }
                }
            }
        }
	//System.out.println("returning tree list:");
	//retList.forEach(tree -> System.out.println(tree));
	//System.out.println("=====================");
        return retList;
    }
}
