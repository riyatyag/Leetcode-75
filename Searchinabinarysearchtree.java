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
// You are given the root of a binary search tree (BST) and an integer val.
// Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
// If such a node does not exist, return null.

// Approach:
// The fundamental property of a Binary Search Tree (BST) is that for any given node:
// - All values in its left subtree are less than the node's value.
// - All values in its right subtree are greater than the node's value.
// This property allows for efficient searching. We can traverse the tree, at each step deciding whether to go left, right, or if we've found the node.
//
// There are two common ways to implement this:
// 1. Recursive Approach:
//    - Base Case: If the `root` is null, or `root.val` is equal to `val`, then we have found the node (or reached the end of a path without finding it), so return `root`.
//    - Recursive Step:
//        - If `val` is less than `root.val`, the target node, if it exists, must be in the left subtree. Recursively call `searchBST(root.left, val)`.
//        - If `val` is greater than `root.val`, the target node, if it exists, must be in the right subtree. Recursively call `searchBST(root.right, val)`.
//
// 2. Iterative Approach:
//    - Start a `currentNode` pointer at `root`.
//    - Loop while `currentNode` is not null:
//        - If `val` equals `currentNode.val`, return `currentNode`.
//        - If `val` is less than `currentNode.val`, move `currentNode` to `currentNode.left`.
//        - If `val` is greater than `currentNode.val`, move `currentNode` to `currentNode.right`.
//    - If the loop finishes (meaning `currentNode` became null), it means the value was not found, so return `null`.

// Time Complexity:
// O(H), where H is the height of the BST.
// In the worst case (a skewed tree), H can be N (the number of nodes).
// In the best/average case (a balanced tree), H is log N.
// The search performs at most H comparisons.

// Space Complexity:
// O(H) for the recursive approach due to the recursion stack.
// O(1) for the iterative approach, as it only uses a few pointers.

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }

        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
}