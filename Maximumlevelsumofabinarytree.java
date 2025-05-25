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
// Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
// Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

// Approach:
// This problem asks us to find the level in a binary tree that has the maximum sum of node values. Since we need to process the tree level by level, a Breadth-First Search (BFS) is the most natural approach.
//
// 1. Initialize:
//    - `maxSum`: Stores the maximum sum found so far, initialized to a very small number (e.g., Integer.MIN_VALUE) to correctly handle negative sums.
//    - `minLevel`: Stores the level corresponding to `maxSum`, initialized to 0 or 1.
//    - `currentLevel`: Tracks the current level being processed, initialized to 1.
//    - `queue`: A `LinkedList` (which implements `Queue`) to perform BFS. Add the `root` to the queue.
//
// 2. BFS Traversal:
//    - Loop while the `queue` is not empty:
//        - Get the `size` of the queue. This `size` represents the number of nodes at the `currentLevel`.
//        - Initialize `currentLevelSum` to 0.
//        - Iterate `size` times (for all nodes at the current level):
//            - Dequeue a `TreeNode` from the `queue`.
//            - Add its `val` to `currentLevelSum`.
//            - If the dequeued node has a `left` child, enqueue it.
//            - If the dequeued node has a `right` child, enqueue it.
//        - After processing all nodes at `currentLevel`:
//            - Compare `currentLevelSum` with `maxSum`.
//            - If `currentLevelSum > maxSum`:
//                - Update `maxSum = currentLevelSum`.
//                - Update `minLevel = currentLevel`.
//        - Increment `currentLevel` to move to the next level.
//
// 3. Return `minLevel`.

// Time Complexity:
// O(N), where N is the number of nodes in the binary tree.
// Each node is visited and processed exactly once when it's dequeued from the queue.

// Space Complexity:
// O(W), where W is the maximum width of the binary tree.
// In the worst case (a complete binary tree), the queue can hold up to N/2 nodes (at the last level), so it's O(N).
// In the best case (a skewed tree), it's O(1) as the queue only holds one node at a time.

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        long maxSum = Long.MIN_VALUE; 
        int minLevel = 0;
        int currentLevel = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long currentLevelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevelSum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (currentLevelSum > maxSum) {
                maxSum = currentLevelSum;
                minLevel = currentLevel;
            }
            currentLevel++;
        }

        return minLevel;
    }
}