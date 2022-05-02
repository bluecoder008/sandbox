
# Given the root of a binary tree, flatten the tree into a "linked list":
# The "linked list" should use the same TreeNode class where the right child pointer points to
#  the next node in the list and the left child pointer is always null.
# The "linked list" should be in the same order as a pre-order traversal of the binary tree.
# https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0):
        self.val = val
        self.left = None
        self.right = None
    def add_left(self, child):
        self.left = child
    def add_right(self, child):
        self.right = child

class Solution:
    def preorder(self, tree_node, results):
        if not tree_node:
            # results.append(None)
            return
        results.append(tree_node)
        self.preorder(tree_node.left, results)
        self.preorder(tree_node.right, results)

    def flatten(self, tree_node:TreeNode) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        if not tree_node:
            return
        node_list = []
        self.preorder(tree_node, node_list)
        for node in node_list:
            print(" {}".format(node.val))
        N = len(node_list)
        for n in range(N-1):
            if node_list[n]:
                node_list[n].left = None
                node_list[n].right = node_list[n+1]
        if node_list[N-1]:
            node_list[N-1].right = None
        

if __name__ == "__main__":
    solution = Solution()
    solution.flatten(None)
    node1 = TreeNode(1)
    solution.flatten(node1)
    assert node1.left == None
    assert node1.right == None
    assert node1.val ==  1

    (node2,node3,node4,node5,node6) = (TreeNode(2),TreeNode(3),TreeNode(4),TreeNode(5),TreeNode(6))
    node1.add_left(node2); node1.add_right(node5);
    node2.add_left(node3); node2.add_right(node4);
    node5.add_right(node6)
    
    solution.flatten(node1)
    assert node1.left == None
    assert node2.left == None
    assert node3.left == None
    assert node4.left == None
    assert node5.left == None
    assert node6.left == None
    assert node1.right == node2
    assert node2.right == node3
    assert node3.right == node4
    assert node4.right == node5
    assert node5.right == node6

