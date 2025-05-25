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
// Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
// Return the number of good nodes in the binary tree.

// Approach:
// This problem can be solved efficiently using a Depth-First Search (DFS) traversal.
// As we traverse the tree from the root downwards, we need to keep track of the maximum value encountered so far along the current path from the root to the current node.
//
// We can define a recursive helper function `dfs(node, maxSoFar)`:
// - `node`: The current node being visited.
// - `maxSoFar`: The maximum value encountered on the path from the root to the parent of the `node`.
//
// 1. Initialize:
//    - A global counter `goodNodesCount` initialized to 0.
//    - Start the DFS from the `root`. The initial `maxSoFar` for the root should be `Integer.MIN_VALUE` (or the root's value if it's guaranteed to be positive, but MIN_VALUE is safer for negative node values).
//
// 2. DFS Function `dfs(node, maxSoFar)`:
//    - Base Case: If `node` is null, return.
//
//    - Check if current node is "good":
//        - If `node.val >= maxSoFar`, then this `node` is a good node. Increment `goodNodesCount`.
//        - Update `maxSoFar` for the subsequent recursive calls: `newMaxSoFar = Math.max(maxSoFar, node.val)`.
//
//    - Recursive Calls:
//        - Call `dfs(node.left, newMaxSoFar)`.
//        - Call `dfs(node.right, newMaxSoFar)`.
//
// 3. Return `goodNodesCount` after the initial DFS call completes.

// Time Complexity:
// O(N), where N is the number of nodes in the binary tree.
// Each node is visited exactly once during the DFS traversal.

// Space Complexity:
// O(H), where H is the height of the binary tree.
// This space is used by the recursion stack. In the worst case (a skewed tree), H can be N, leading to O(N) space.
// In the best/average case (a balanced tree), H is log N, leading to O(log N) space.

class Solution {
    private int goodNodesCount = 0;

    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, Integer.MIN_VALUE);
        return goodNodesCount;
    }

    private void dfs(TreeNode node, int maxSoFar) {
        if (node == null) {
            return;
        }

        if (node.val >= maxSoFar) {
            goodNodesCount++;
            maxSoFar = node.val; 
        }

        dfs(node.left, maxSoFar);
        dfs(node.right, maxSoFar);
    }
}