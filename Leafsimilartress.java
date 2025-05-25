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
// Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
// Two binary trees are considered leaf-similar if their leaf value sequence is the same.
// Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

// Approach:
// To determine if two binary trees are leaf-similar, we need to extract the leaf value sequence for both trees and then compare these sequences.
// A leaf node is a node that has no children (both left and right children are null).
// The leaf value sequence must be extracted in left-to-right order, which suggests using a traversal that naturally visits nodes in that order, such as a Depth-First Search (DFS) (pre-order, in-order, or post-order will work as long as left child is visited before right child).

// Algorithm:
// 1. Create a helper function, say `getLeafSequence(node, leafList)`, that performs a DFS traversal.
//    - If `node` is null, return.
//    - If `node` is a leaf (i.e., `node.left == null` and `node.right == null`), add `node.val` to `leafList`.
//    - Recursively call `getLeafSequence(node.left, leafList)`.
//    - Recursively call `getLeafSequence(node.right, leafList)`.
//
// 2. In the main `leafSimilar` function:
//    - Create two empty lists, `list1` and `list2`.
//    - Call `getLeafSequence(root1, list1)` to populate `list1` with the leaf values of `root1`.
//    - Call `getLeafSequence(root2, list2)` to populate `list2` with the leaf values of `root2`.
//    - Compare `list1` and `list2` for equality. If they are equal, return `true`; otherwise, return `false`.

// Time Complexity:
// O(N1 + N2), where N1 is the number of nodes in `root1` and N2 is the number of nodes in `root2`.
// We traverse each tree once to extract its leaf sequence.

// Space Complexity:
// O(H1 + H2) for the recursion stack space, where H1 and H2 are the heights of `root1` and `root2` respectively. In the worst case (skewed tree), this can be O(N1 + N2).
// O(L1 + L2) for storing the leaf lists, where L1 and L2 are the number of leaf nodes in `root1` and `root2`. In the worst case, this can be O(N1 + N2) (e.g., a tree where almost all nodes are leaves).

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        getLeafSequence(root1, leaves1);
        getLeafSequence(root2, leaves2);

        return leaves1.equals(leaves2);
    }

    private void getLeafSequence(TreeNode node, List<Integer> leafList) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leafList.add(node.val);
            return;
        }

        getLeafSequence(node.left, leafList);
        getLeafSequence(node.right, leafList);
    }
}