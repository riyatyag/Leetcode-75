/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
// Problem Statement:
// Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
// According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

// Approach:
// This problem can be solved using a recursive Depth-First Search (DFS) approach.
// The core idea is to traverse the tree and, for each node, determine if `p` or `q` (or both) are present in its left or right subtree.
//
// Let's define a recursive function `lowestCommonAncestor(currentNode, p, q)` that returns the LCA of `p` and `q` within the subtree rooted at `currentNode`.
//
// Base Cases:
// 1. If `currentNode` is null, it means we've reached the end of a path without finding `p` or `q`, so return `null`.
// 2. If `currentNode` is `p` or `currentNode` is `q`, then `currentNode` itself is the LCA for this subtree (since a node can be a descendant of itself). Return `currentNode`.
//
// Recursive Step:
// 1. Recursively call `lowestCommonAncestor` on the left child: `leftLCA = lowestCommonAncestor(currentNode.left, p, q)`.
// 2. Recursively call `lowestCommonAncestor` on the right child: `rightLCA = lowestCommonAncestor(currentNode.right, p, q)`.
//
// Combining Results:
// - If both `leftLCA` and `rightLCA` are non-null:
//   This means `p` was found in the left subtree and `q` was found in the right subtree (or vice-versa). In this case, `currentNode` is the first node that has both `p` and `q` as descendants. Therefore, `currentNode` is the LCA. Return `currentNode`.
// - If only `leftLCA` is non-null:
//   This means both `p` and `q` (or just one of them, and the other is `leftLCA` itself) are in the left subtree of `currentNode`. So, the LCA must be `leftLCA`. Return `leftLCA`.
// - If only `rightLCA` is non-null:
//   This means both `p` and `q` (or just one of them, and the other is `rightLCA` itself) are in the right subtree of `currentNode`. So, the LCA must be `rightLCA`. Return `rightLCA`.
// - If both `leftLCA` and `rightLCA` are null:
//   This means neither `p` nor `q` were found in the subtree rooted at `currentNode`. Return `null`.

// Time Complexity:
// O(N), where N is the number of nodes in the binary tree.
// In the worst case, we might visit all nodes of the tree. Each node is visited exactly once.

// Space Complexity:
// O(H), where H is the height of the binary tree.
// This space is used by the recursion stack. In the worst case (a skewed tree), H can be N, leading to O(N) space.
// In the best/average case (a balanced tree), H is log N, leading to O(log N) space.

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);

        if (leftLCA != null && rightLCA != null) {
            return root;
        } else if (leftLCA != null) {
            return leftLCA;
        } else {
            return rightLCA;
        }
    }
}