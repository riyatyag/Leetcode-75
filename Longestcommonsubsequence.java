// Problem Statement:
// Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
// A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
// A common subsequence of two strings is a subsequence that is common to both strings.

// Approach:
// This is a classic dynamic programming problem. We can define a 2D DP array `dp[i][j]` where `dp[i][j]` represents the length of the longest common subsequence of `text1[0...i-1]` and `text2[0...j-1]`.

// Let `m` be the length of `text1` and `n` be the length of `text2`.
// The `dp` table will have dimensions `(m+1) x (n+1)`.

// Initialization:
// - `dp[i][0]` will be 0 for all `0 <= i <= m`, as the LCS of any string with an empty string is 0.
// - `dp[0][j]` will be 0 for all `0 <= j <= n`, as the LCS of an empty string with any string is 0.
// This means the first row and first column of the DP table will be all zeros.

// Filling the DP table:
// Iterate `i` from 1 to `m` and `j` from 1 to `n`.
// For each `dp[i][j]`, consider the characters `text1.charAt(i-1)` and `text2.charAt(j-1)`:

// Case 1: `text1.charAt(i-1) == text2.charAt(j-1)` (Characters match)
// If the last characters of the current substrings match, then this character is part of the LCS.
// So, `dp[i][j]` will be 1 (for the current matching character) plus the LCS of the strings without these last characters (`text1[0...i-2]` and `text2[0...j-2]`).
// `dp[i][j] = 1 + dp[i-1][j-1]`

// Case 2: `text1.charAt(i-1) != text2.charAt(j-1)` (Characters don't match)
// If the last characters don't match, we cannot include both in the LCS. We have two options:
// a. Exclude `text1.charAt(i-1)`: The LCS would be `dp[i-1][j]` (LCS of `text1[0...i-2]` and `text2[0...j-1]`).
// b. Exclude `text2.charAt(j-1)`: The LCS would be `dp[i][j-1]` (LCS of `text1[0...i-1]` and `text2[0...j-2]`).
// We take the maximum of these two possibilities, as we want the *longest* common subsequence.
// `dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])`

// The final answer will be `dp[m][n]`.

// Time Complexity:
// O(m * n), where `m` is the length of `text1` and `n` is the length of `text2`. We iterate through each cell of the `m x n` DP table once, and each cell calculation takes O(1) time.

// Space Complexity:
// O(m * n) for the 2D DP array.

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}