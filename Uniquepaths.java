// Problem Statement:
// There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
// The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
// The robot can only move either down or right at any point in time.
// Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
// The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

// Approach:
// This is a classic dynamic programming problem.
// Let `dp[i][j]` be the number of unique paths to reach cell `(i, j)`.
// The robot can only move down or right. This means to reach `(i, j)`, the robot must have come from either `(i-1, j)` (moving down) or `(i, j-1)` (moving right).
// Therefore, the number of unique paths to `(i, j)` is the sum of unique paths to `(i-1, j)` and unique paths to `(i, j-1)`.
// `dp[i][j] = dp[i-1][j] + dp[i][j-1]`

// Base Cases:
// - The starting cell `(0, 0)` can be reached in 1 way (do nothing). So `dp[0][0] = 1`.
// - For any cell in the first row `(0, j)`, there's only one way to reach it: by moving right `j` times from `(0, 0)`. So `dp[0][j] = 1` for all `0 <= j < n`.
// - For any cell in the first column `(i, 0)`, there's only one way to reach it: by moving down `i` times from `(0, 0)`. So `dp[i][0] = 1` for all `0 <= i < m`.

// We will create a `m x n` DP table.

// Example Walkthrough (m=3, n=2):
// dp table (rows x columns):
// `dp[0][0] = 1` (start)
// `dp[0][1] = 1` (right from (0,0))
// `dp[1][0] = 1` (down from (0,0))

// For `dp[1][1]`: `dp[1][1] = dp[0][1] + dp[1][0] = 1 + 1 = 2`
// For `dp[2][0]`: `dp[2][0] = 1` (from base case)
// For `dp[2][1]`: `dp[2][1] = dp[1][1] + dp[2][0] = 2 + 1 = 3`

// Final `dp` table for m=3, n=2:
// 1 1
// 1 2
// 1 3

// The result is `dp[m-1][n-1]`.

// Space Optimization:
// Notice that `dp[i][j]` only depends on the values from the current row (`dp[i][j-1]`) and the previous row (`dp[i-1][j]`).
// This means we can optimize the space to O(N) by only keeping track of the current row and the previous row.
// Even further, we can observe that `dp[i][j]` can be calculated using `dp[j]` (current row, current column) and `dp[j-1]` (current row, previous column) from the *updated* array, and `dp[j]` (previous row, current column) from the *old* array value.

// Optimized DP State:
// We use a 1D array `dp` of size `n`.
// Initialize `dp` array with all 1s. `dp[j]` initially represents `dp[0][j]` (number of ways to reach (0, j)).
// For each subsequent row `i` from 1 to `m-1`:
//   `dp[0]` remains 1 (since `dp[i][0]` is always 1).
//   For each column `j` from 1 to `n-1`:
//     `dp[j]` (current cell in current row) = `dp[j]` (value from previous row, same column) + `dp[j-1]` (value from current row, previous column).
//     The `dp[j]` on the right side of the assignment is the `dp[i-1][j]` (from previous row), and `dp[j-1]` is `dp[i][j-1]` (already updated for current row).

// Time Complexity:
// O(m * n) for iterating through the DP table.

// Space Complexity:
// O(min(m, n)) if we choose to optimize based on the smaller dimension (e.g., if n < m, use an array of size n). In the provided code, it's O(n).

class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1); 
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}