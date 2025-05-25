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
// Given a root node reference of a BST and a key, delete the node with the given key in the BST.
// Return the root node reference (possibly updated) of the BST.
// Basically, the deletion can be divided into two stages:
// 1. Search for a node to remove.
// 2. If the node is found, delete the node.

// Approach:
// The deletion process in a Binary Search Tree (BST) involves finding the node to be deleted and then handling three cases based on the number of children the node has:
// 1. Node has no children (leaf node): Simply remove the node.
// 2. Node has one child: Replace the node with its single child.
// 3. Node has two children: This is the most complex case. To maintain the BST property, we need to find a replacement node. The standard approach is to find either:
//    a. The in-order successor: The smallest node in its right subtree.
//    b. The in-order predecessor: The largest node in its left subtree.
//    Once the successor/predecessor is found, its value replaces the deleted node's value, and then the successor/predecessor node itself is deleted from its original position (which will fall into case 1 or 2).
//
// The algorithm proceeds recursively:
// - If the root is null, return null.
// - If the key is less than the current node's value, recursively call deleteNode on the left subtree.
// - If the key is greater than the current node's value, recursively call deleteNode on the right subtree.
// - If the key matches the current node's value (node to be deleted):
//   - If the node has no left child, return its right child (could be null).
//   - If the node has no right child, return its left child.
//   - If the node has both children:
//     - Find the minimum value node in its right subtree (in-order successor).
//     - Replace the current node's value with the in-order successor's value.
//     - Recursively call deleteNode on the right subtree to delete the in-order successor.

// Time Complexity:
// O(H), where H is the height of the BST.
// In the worst case (skewed tree), H can be N (number of nodes).
// In the best/average case (balanced tree), H is log N.
// The search for the node takes O(H). Finding the in-order successor also takes O(H). The recursive deletion of the successor also takes O(H). Thus, the overall time complexity is O(H).

// Space Complexity:
// O(H) for the recursion stack space.
// In the worst case (skewed tree), H can be N.
// In the best/average case (balanced tree), H is log N.

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}