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
// You are given the root of a binary tree.
// A ZigZag path for a binary tree is defined as follow:
// 1. Choose any node in the binary tree and a direction (right or left).
// 2. If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
// 3. Change the direction from right to left or from left to right.
// 4. Repeat the second and third steps until you can't move in the tree.
// Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
// Return the longest ZigZag path contained in that tree.

// Approach:
// This problem can be effectively solved using a recursive Depth-First Search (DFS) approach.
// For each node in the tree, we need to calculate the longest ZigZag path that *ends* at that node, coming from its parent.
// A path can either come from the left or from the right.
//
// We can define a DFS function that returns two values for each node:
// - `leftLength`: The length of the longest ZigZag path ending at the current node, coming from its *left* child (meaning the last step was from left to right).
// - `rightLength`: The length of the longest ZigZag path ending at the current node, coming from its *right* child (meaning the last step was from right to left).
//
// Let's modify our DFS to pass the current direction and current path length.
// `dfs(node, goLeft, currentLength)`:
// - `node`: The current node being visited.
// - `goLeft`: A boolean flag. If `true`, the next step *should* be to the left child to continue the ZigZag. If `false`, the next step *should* be to the right child.
// - `currentLength`: The length of the ZigZag path ending at `node`.
//
// We will also need a global variable `maxLength` to keep track of the overall maximum ZigZag path found anywhere in the tree.
//
// Recursive DFS Function (`dfs(node, isLeftDirection, currentLength)`):
// 1. Base Case: If `node` is null, return.
//
// 2. Update Global Max:
//    - `maxLength = Math.max(maxLength, currentLength)`.
//
// 3. Recurse for Left Child:
//    - If `isLeftDirection` is true (meaning we *came* from the right, and now we *should* go left from `node` to continue the zigzag):
//        - Call `dfs(node.left, false, currentLength + 1)`. The next direction is right.
//    - If `isLeftDirection` is false (meaning we *came* from the left, and now we must *break* the current zigzag sequence and start a new one from this `node` going left):
//        - Call `dfs(node.left, false, 1)`. Start a new zigzag path of length 1 going right from the left child.
//
// 4. Recurse for Right Child:
//    - If `isLeftDirection` is false (meaning we *came* from the left, and now we *should* go right from `node` to continue the zigzag):
//        - Call `dfs(node.right, true, currentLength + 1)`. The next direction is left.
//    - If `isLeftDirection` is true (meaning we *came* from the right, and now we must *break* the current zigzag sequence and start a new one from this `node` going right):
//        - Call `dfs(node.right, true, 1)`. Start a new zigzag path of length 1 going left from the right child.
//
// Initial Calls:
// We need to initiate the DFS from the `root` assuming it could be the start of a ZigZag path going either left or right.
// - `dfs(root.left, false, 1)` (start a zigzag path from root going left)
// - `dfs(root.right, true, 1)` (start a zigzag path from root going right)
//
// A single node has length 0, so initialize `maxLength = 0`.
//
// Note: An alternative and often cleaner DFS approach involves the recursive function returning the max length ending at the current node (from left and right) and updating a global max.
// Let `dfs(node)` return `[left_zigzag_length, right_zigzag_length]` ending at `node`.
// `left_zigzag_length` = 1 + `right_zigzag_length` of `node.left`
// `right_zigzag_length` = 1 + `left_zigzag_length` of `node.right`
// Update global max with `max(left_zigzag_length, right_zigzag_length)`.

// Time Complexity:
// O(N), where N is the number of nodes in the binary tree.
// Each node is visited exactly once during the DFS traversal.

// Space Complexity:
// O(H), where H is the height of the binary tree.
// This space is used by the recursion stack. In the worst case (a skewed tree), H can be N, leading to O(N) space.
// In the best/average case (a balanced tree), H is log N, leading to O(log N) space.

class Solution {
    private int maxLength = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root.left, true, 1); 
        dfs(root.right, false, 1);
        return maxLength;
    }
    private void dfs(TreeNode node, boolean isLeft, int currentLength) {
        if (node == null) {
            return;
        }

        maxLength = Math.max(maxLength, currentLength);
        if (isLeft) {
            dfs(node.right, false, currentLength + 1);
            dfs(node.left, true, 1);
        }
        else {
            dfs(node.left, true, currentLength + 1);
            dfs(node.right, false, 1);
        }
    }
}