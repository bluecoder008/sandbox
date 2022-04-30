/*
 * Find the lowest ranking common manger in org chart for a set of employees
 */

import java.io.*;
import java.util.*;

class FindLowestCommonMgr {
    
  static class Node {

      String name;
      List<Node> reports;

      Node(String name) {
          this.name = name;
          reports = new LinkedList<>();
      }

      Node addReport(Node report) {
          reports.add(report);
          return this;
      }
      @Override
      public boolean equals(Object object) {
          Node node = (Node)object;
          return this.name.equals(node.name);
      }
      @Override
      public int hashCode() {
          return name.hashCode();
      }
   };
  static List<List<Node>> mgmtChains = new LinkedList<>();

  static void traversOrgChart(Node root, 
                              Set<Node> employees, 
                              List<Node> mgmtChain) 
  {
      if (employees.contains(root)) {
          mgmtChains.add(mgmtChain);
          employees.remove(root);
      }

      if(!employees.isEmpty()) {
          if (root.reports.size()>0) {
              List<Node> mgrChain = (mgmtChain == null) ? new LinkedList<>() : new LinkedList<>(mgmtChain);
              mgrChain.add(root);
              for(Node report: root.reports) {
                  traversOrgChart(report, employees, mgrChain);
              }
          }
      }
  }

  static void printList(List<Node> theList) {
      for(Node n: theList) {
          System.out.print(n.name + " ");
      }
      System.out.println();
  }

  static String findLowestCommonSharedManager(Node root, 
                                 Set<Node> employees, 
                                 List<Node> mgmtChain) 
  {
      traversOrgChart(root, employees, mgmtChain);
      for(List<Node> mgrChain: mgmtChains) {
          printList(mgrChain);
      }
      int n = 0;
      Node prev = null;
      for(n=0; ;n++) {
          Node cur = null;
          for(List<Node> mgrChain: mgmtChains) {
              if (mgrChain.size() == n) {
                  return prev.name;
              }
              if (cur == null) {
                  cur = mgrChain.get(n);
              } else {
                  if (!cur.equals(mgrChain.get(n))) {
                      return prev.name;
                  }
              }
          }
          prev = cur;
      }
  }


  public static void main(String[] args) {
    Node A = new Node("A");
    Node B = new Node("B");
    Node C = new Node("C");
    Node D = new Node("D");
    Node E = new Node("E");
    Node F = new Node("F");
    Node G = new Node("G");
    Node W = new Node("W");
    Node H = new Node("H");
    Node K = new Node("K");
    Node J = new Node("J");
    A.addReport(B).addReport(C).addReport(D);
    B.addReport(E); C.addReport(F); D.addReport(G).addReport(H);
    G.addReport(W); H.addReport(K); H.addReport(J);
    Set<Node> workers = new HashSet<>(){{ //add(B); add(C);
          add(W);
          add(H);
          add(J);
    }};
    String commonMgr = findLowestCommonSharedManager(A, workers, null);
    System.out.println("lowest common mgr: " + commonMgr);
  }
}
