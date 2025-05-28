// Problem Statement:
// Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
// You have the following three operations permitted on a word:
// 1. Insert a character
// 2. Delete a character
// 3. Replace a character

// Approach:
// This is a classic dynamic programming problem known as Edit Distance or Levenshtein Distance.
// We'll use a 2D DP array, `dp[i][j]`, which will store the minimum number of operations required to convert the first `i` characters of `word1` to the first `j` characters of `word2`.

// Let `m` be the length of `word1` and `n` be the length of `word2`.
// The `dp` table will have dimensions `(m+1) x (n+1)`.

// Initialization:
// - `dp[i][0]` represents converting `word1[0...i-1]` to an empty string. This requires `i` deletions. So, `dp[i][0] = i` for `0 <= i <= m`.
// - `dp[0][j]` represents converting an empty string to `word2[0...j-1]`. This requires `j` insertions. So, `dp[0][j] = j` for `0 <= j <= n`.
// - `dp[0][0]` will be 0, as converting an empty string to an empty string requires 0 operations.

// Filling the DP table:
// Iterate `i` from 1 to `m` and `j` from 1 to `n`.
// For each `dp[i][j]`, consider the characters `word1.charAt(i-1)` and `word2.charAt(j-1)`:

// Case 1: `word1.charAt(i-1) == word2.charAt(j-1)` (Characters match)
// If the last characters match, no operation is needed for them. The minimum operations will be the same as converting `word1[0...i-2]` to `word2[0...j-2]`.
// So, `dp[i][j] = dp[i-1][j-1]`.

// Case 2: `word1.charAt(i-1) != word2.charAt(j-1)` (Characters don't match)
// We have three choices, and we take the minimum of them plus 1 (for the current operation):
// a. **Insert:** Insert `word2.charAt(j-1)` into `word1`. This is equivalent to converting `word1[0...i-1]` to `word2[0...j-2]` and then inserting `word2.charAt(j-1)`.
//    Cost: `dp[i][j-1] + 1`
// b. **Delete:** Delete `word1.charAt(i-1)` from `word1`. This is equivalent to converting `word1[0...i-2]` to `word2[0...j-1]` and then deleting `word1.charAt(i-1)`.
//    Cost: `dp[i-1][j] + 1`
// c. **Replace:** Replace `word1.charAt(i-1)` with `word2.charAt(j-1)`. This is equivalent to converting `word1[0...i-2]` to `word2[0...j-2]` and then performing a replacement.
//    Cost: `dp[i-1][j-1] + 1`

// So, if characters don't match, `dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])`.

// The final answer will be `dp[m][n]`.

// Time Complexity:
// O(m * n), where `m` is the length of `word1` and `n` is the length of `word2`. We fill an `m x n` DP table, and each cell takes O(1) time to compute.

// Space Complexity:
// O(m * n) for the 2D DP array.

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertCost = dp[i][j - 1];   
                    int deleteCost = dp[i - 1][j];   
                    int replaceCost = dp[i - 1][j - 1]; 
                    dp[i][j] = 1 + Math.min(insertCost, Math.min(deleteCost, replaceCost));
                }
            }
        }
         return dp[m][n];
    }
}