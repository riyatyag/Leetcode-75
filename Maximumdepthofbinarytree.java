/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
// Problem Statement:
// Given the root of a binary tree, return its maximum depth.
// A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

// Approach:
// The maximum depth of a binary tree can be found using either Depth-First Search (DFS) or Breadth-First Search (BFS).
//
// 1. Recursive DFS Approach:
//    This is often the most intuitive approach for tree depth problems.
//    The maximum depth of a node is 1 plus the maximum depth of its deepest child.
//    - Base Case: If the `root` is null (empty tree or reached beyond a leaf), its depth is 0.
//    - Recursive Step:
//        - Recursively calculate the maximum depth of the left subtree: `leftDepth = maxDepth(root.left)`.
//        - Recursively calculate the maximum depth of the right subtree: `rightDepth = maxDepth(root.right)`.
//        - The maximum depth of the current node's subtree is `Math.max(leftDepth, rightDepth) + 1` (add 1 for the current node itself).
//
// 2. Iterative BFS Approach (Level Order Traversal):
//    - Initialize `depth = 0`.
//    - If the `root` is null, return 0.
//    - Use a queue for BFS. Add the `root` to the queue.
//    - While the queue is not empty:
//        - Increment `depth` (as we are processing a new level).
//        - Get the `size` of the current level (number of nodes in the queue).
//        - Iterate `size` times:
//            - Dequeue a node.
//            - If it has a left child, enqueue it.
//            - If it has a right child, enqueue it.
//    - Return `depth`.

// Time Complexity:
// O(N), where N is the number of nodes in the binary tree.
// Both DFS and BFS approaches visit each node exactly once.

// Space Complexity:
// O(H) for the recursive DFS approach, where H is the height of the tree, due to the recursion stack. In the worst case (skewed tree), H can be N, leading to O(N) space. In the best/average case (balanced tree), H is log N, leading to O(log N) space.
// O(W) for the iterative BFS approach, where W is the maximum width of the tree. In the worst case (a complete binary tree), W can be N/2, leading to O(N) space for the queue. In the best case (skewed tree), W is 1, leading to O(1) space.

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}