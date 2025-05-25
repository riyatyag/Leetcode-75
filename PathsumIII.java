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
// Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
// The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

// Approach:
// This problem can be solved using a recursive Depth-First Search (DFS) approach. Since paths can start from any node and end at any node (as long as they go downwards), we need a way to consider all possible starting points.
//
// The core idea is to perform a main DFS traversal that visits every node. For each node, we then perform *another* DFS traversal (a sub-DFS) starting from that node to find all paths that sum to `targetSum` starting from *this particular node* and going downwards.
//
// 1. Main DFS (Outer Recursion):
//    - `pathSum(root, targetSum)`:
//        - If `root` is null, return 0.
//        - Initialize `count = 0`.
//        - Add the number of paths found starting from `root` itself (using a helper function).
//        - Recursively call `pathSum` for the left child: `count += pathSum(root.left, targetSum)`.
//        - Recursively call `pathSum` for the right child: `count += pathSum(root.right, targetSum)`.
//        - Return `count`.
//
// 2. Helper DFS (Inner Recursion - `countPathsFromNode`):
//    - `countPathsFromNode(node, currentSum, targetSum)`:
//        - Base Case: If `node` is null, return.
//        - Add `node.val` to `currentSum`.
//        - If `currentSum` equals `targetSum`, increment a global or passed-by-reference counter (e.g., `totalPaths`).
//        - Recursively call `countPathsFromNode` for `node.left` with the updated `currentSum`.
//        - Recursively call `countPathsFromNode` for `node.right` with the updated `currentSum`.
//
// Note: `currentSum` should be `long` to avoid potential integer overflow if `Node.val` and number of nodes allow large sums. `targetSum` is within int range.

// Time Complexity:
// O(N^2) in the worst case (e.g., a skewed tree).
// For each of the N nodes, the `countPathsFromNode` helper function potentially traverses down to all its descendants. In a skewed tree, this can lead to O(N) work for each node, resulting in O(N*N).
// In a balanced tree, it's closer to O(N log N) because the depth of the sub-DFS is log N.

// Space Complexity:
// O(H), where H is the height of the tree.
// This is due to the recursion stack depth for the main `pathSum` calls and the nested `countPathsFromNode` calls. In the worst case (skewed tree), H can be N, leading to O(N) space.

// Optimal Solution (using HashMap for prefix sums):
// An O(N) solution exists using prefix sums and a HashMap.
//
// 1. Concept:
//    - When we traverse from root to a node, we keep track of the cumulative sum from the root to the current node (`current_sum`).
//    - If we are looking for a path that sums to `targetSum`, and the `current_sum` from root to current node is `C`, we are interested in finding if there was an ancestor node such that the sum from root to that ancestor was `C - targetSum`.
//    - We can use a HashMap to store the frequency of `prefix_sum` values encountered so far along the current path from the root.
//
// 2. Algorithm:
//    - `totalPaths = 0` (global counter).
//    - `map = new HashMap<Long, Integer>()` (stores prefix_sum -> count).
//    - `map.put(0L, 1)` (base case: a path sum of 0 exists before starting, which is useful if the targetSum path starts from the root itself).
//    - Recursive function `dfs(node, current_sum, targetSum, map)`:
//        - If `node` is null, return.
//        - Update `current_sum += node.val`.
//        - If `map.containsKey(current_sum - targetSum)`:
//            - `totalPaths += map.get(current_sum - targetSum)`. This means we found `map.get(current_sum - targetSum)` paths ending at `node` that sum to `targetSum`.
//        - Increment frequency of `current_sum` in `map`: `map.put(current_sum, map.getOrDefault(current_sum, 0) + 1)`.
//        - Recursively call `dfs` for `node.left` and `node.right`.
//        - Backtrack: After visiting children, decrement frequency of `current_sum` in `map`: `map.put(current_sum, map.get(current_sum) - 1)`. This is crucial because when we move up the recursion stack, `current_sum` is no longer part of the path, so its contribution should be removed from the map for sibling paths.
//
// This optimal solution has O(N) time complexity because each node is visited once, and HashMap operations are O(1) on average.
// Space complexity is O(H) for the recursion stack and O(H) for the HashMap in the worst case (number of distinct prefix sums equals height), so O(H).

import java.util.HashMap;
import java.util.Map;

class Solution {
    int totalPaths = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> prefixSumCounts = new HashMap<>();
        prefixSumCounts.put(0L, 1);

        dfs(root, 0L, targetSum, prefixSumCounts);

        return totalPaths;
    }

    private void dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCounts) {
        if (node == null) {
            return;
        }

        currentSum += node.val;

        if (prefixSumCounts.containsKey(currentSum - targetSum)) {
            totalPaths += prefixSumCounts.get(currentSum - targetSum);
        }
        prefixSumCounts.put(currentSum, prefixSumCounts.getOrDefault(currentSum, 0) + 1);

        dfs(node.left, currentSum, targetSum, prefixSumCounts);
        dfs(node.right, currentSum, targetSum, prefixSumCounts);

        prefixSumCounts.put(currentSum, prefixSumCounts.get(currentSum) - 1);
    }
}