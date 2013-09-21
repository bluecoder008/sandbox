import java.util.*;

// This program took a about 1.5 hour to debug and complete..
// while there's a similar efficient algorithm which much simpler:
// http://stackoverflow.com/questions/12056068/finding-least-common-ancestor-in-binary-tree
//
// It pays to learn from others!!!!
//
class FindLowestCommonAncestor {
 
   static boolean debug = false;

   static class Node {
       Node leftChild, rightChild;
       int value;
   }
   
   static class Result {
        Node node1, node2;
        Map<Node, Node> parents = new HashMap<Node,Node>();
   }

   // find the lowest common ancestor for the nodes
   // with values v1 and v2
   static Node find(Node root, int v1, int v2) {
       
       Result result = new Result();
       traverse(root, v1, v2, result);
       
       Node node1 = result.node1;
       Node node2 = result.node2;

       if (debug) System.out.println("node1 = " + node1.value);
       if (debug) System.out.println("node2 = " + node2.value);
       
       Map<Node, Boolean> ancestors = new HashMap<Node,Boolean>();
       ancestors.put(node1, true);
       ancestors.put(node2, true);
       do {
           if ( node1 != null ) {
               Node parent1 = result.parents.get(node1);
	       if (debug) System.out.println("parent of " + node1.value + " => "
                 + (parent1 == null ? "<null>" : parent1.value) );
               if (ancestors.get(parent1) != null ) 
                   return parent1;
               ancestors.put(parent1, true);
               node1 = parent1;
           }
           if ( node2 != null ) {
               Node parent2 = result.parents.get(node2);
	       if (debug) System.out.println("parent of " + node2.value + " => "
                 + (parent2 == null ? "<null>" : parent2.value) );
               if (ancestors.get(parent2) != null )
                   return parent2;
               ancestors.put(parent2, true);
               node2 = parent2;
          }
       } while ( node1 != null || node2 != null );
       return null;
   }

   static void traverse(Node root, int v1, int v2, Result result) {
   
      if ( root.value == v1 ) 
          result.node1 = root;
      if ( root.value == v2 )
          result.node2 = root;
      Node left = root.leftChild;
      if ( left != null ) {
          result.parents.put(left, root);
          traverse(left, v1, v2, result);
      }
      Node right = root.rightChild;
      if ( right != null ) {
          result.parents.put(right, root);
          traverse(right, v1, v2, result);
      }
  }

  static Node buildTree(int[] values) {

      Node root = new Node();
      LinkedList<Node> queue = new LinkedList<Node>();
      queue.add(root);

      for(int n=0; n < values.length && queue.size()>0; n++) {

          Node node = queue.removeFirst();
          int value = values[n];
          node.value = value;

          if ( value != -1 ) {
              node.leftChild = new Node();
              queue.add(node.leftChild);
              node.rightChild = new Node();
              queue.add(node.rightChild);
          }
      }

      return root;
  }
  
  public static void main(String[] args) {
  
      System.out.println("Please enter list of nodes: ");
      Scanner sc = new Scanner(System.in);

      List<Integer> theList = new ArrayList<Integer>();
      int n;

      while( sc.hasNextInt() ) {
          n = sc.nextInt();
          if ( n ==-2) break;
          //System.out.format("read: %d\n", n );
          theList.add(n);
      }
      Integer[] vals = theList.toArray(new Integer[theList.size()]);
     
      int[] values = new int[ vals.length ];
      n = 0;
      for( Integer v : vals ) {
          //System.out.println("value = " + v );
          values[n++] = v.intValue();
      }

      Node tree = buildTree(values);

      System.out.println("Please enter the two nodes: ");
      int n1 = sc.nextInt();
      int n2 = sc.nextInt();
      Node node = find(tree, n1, n2);
 
      System.out.println("least common ancestor: " + node.value);
  }
}
