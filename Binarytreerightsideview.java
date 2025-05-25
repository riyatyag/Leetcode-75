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
// Given the root of a binary tree, imagine yourself standing on the right side of it,
// return the values of the nodes you can see ordered from top to bottom.

// Approach:
// This problem can be solved using a Level Order Traversal (BFS) or Depth-First Search (DFS).
//
// 1. BFS (Level Order Traversal):
//    The idea is to traverse the tree level by level. For each level, the last node processed (or the first node from the right) will be the one visible from the right side.
//    - Initialize an empty list `result` to store the rightmost nodes.
//    - If the `root` is null, return `result`.
//    - Create a queue and add the `root` to it.
//    - While the queue is not empty:
//        - Get the `size` of the current level (number of nodes in the queue).
//        - Iterate from `0` to `size - 1` (or `size` down to `1` for efficiency for rightmost):
//            - Dequeue a node.
//            - If this is the *last* node for the current level (i.e., `i == size - 1`), then its value is visible from the right. Add its value to `result`.
//            - Enqueue its left child if it exists.
//            - Enqueue its right child if it exists.
//    - Return `result`.
//
// 2. DFS (Pre-order traversal - customized):
//    Alternatively, we can use DFS. The key is to traverse the right child first, then the left child.
//    - Initialize an empty list `result`.
//    - Create a recursive helper function `dfs(node, level, result)`.
//    - Base Case: If `node` is null, return.
//    - If `level == result.size()`, it means we are encountering the first node at this level (from the right side due to traversal order), so add `node.val` to `result`.
//    - Recursively call `dfs(node.right, level + 1, result)`.
//    - Recursively call `dfs(node.left, level + 1, result)`.
//    - Start the DFS from `dfs(root, 0, result)`. (If level is 1-indexed, use 1).

// Time Complexity:
// O(N), where N is the number of nodes in the binary tree.
// Both BFS and DFS visit each node exactly once.

// Space Complexity:
// O(W) for BFS, where W is the maximum width of the tree. In the worst case, W can be N/2 (for a complete binary tree), so O(N).
// O(H) for DFS, where H is the height of the tree. In the worst case (skewed tree), H can be N, so O(N).

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                if (i == levelSize - 1) { 
                    result.add(current.val);
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return result;
    }
}