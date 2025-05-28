// Problem Statement:
// You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
// You can either start from the step with index 0, or the step with index 1.
// Return the minimum cost to reach the top of the floor.

// Approach:
// This is a dynamic programming problem. We want to find the minimum cost to reach the "top of the floor". The "top of the floor" can be considered as one step beyond the last element of the `cost` array.
// Let `dp[i]` be the minimum cost to reach step `i`. The `cost` array has `n` elements, indexed `0` to `n-1`. The "top of the floor" is effectively step `n`.
// We can define `dp[i]` as the minimum cost to reach step `i` (meaning, the total cost *paid* to land on step `i`).

// Base Cases:
// `dp[0]` is the minimum cost to reach step 0. Since you can start directly at step 0 without paying an initial cost, `dp[0] = 0`.
// `dp[1]` is the minimum cost to reach step 1. You can start directly at step 1 without paying an initial cost, so `dp[1] = 0`.

// For `i` from 2 to `n` (where `n` is `cost.length`):
// To reach step `i`, you must have come from either step `i-1` or step `i-2`.
// If you came from step `i-1`: The cost incurred to reach step `i-1` was `dp[i-1]`. To move from `i-1` to `i` (which is the top of the floor if `i` is `n`, or a step if `i` < `n`), you would have paid `cost[i-1]`. So, the total cost would be `dp[i-1] + cost[i-1]`.
// If you came from step `i-2`: The cost incurred to reach step `i-2` was `dp[i-2]`. To move from `i-2` to `i`, you would have paid `cost[i-2]`. So, the total cost would be `dp[i-2] + cost[i-2]`.

// Thus, the recurrence relation for `dp[i]` (for `i >= 2`) is:
// `dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])`.

// The final answer will be `dp[n]`.

// Space Optimization:
// Notice that `dp[i]` only depends on the two previous values: `dp[i-1]` and `dp[i-2]`.
// This allows us to optimize the space complexity from O(N) to O(1) by only storing these two previous values.

// Let `prev2` store `dp[i-2]`.
// Let `prev1` store `dp[i-1]`.
// Initialize `prev2 = 0` (for `dp[0]`) and `prev1 = 0` (for `dp[1]`).

// Iterate from `i = 2` up to `n`:
// Calculate `current = Math.min(prev1 + cost[i-1], prev2 + cost[i-2])`.
// Update `prev2` to `prev1` (as `prev1` becomes the new `prev2` for the next iteration).
// Update `prev1` to `current` (as `current` becomes the new `prev1` for the next iteration).

// After the loop finishes, `prev1` will hold the minimum cost to reach step `n` (the top of the floor).

// Time Complexity:
// O(N), where N is the length of the `cost` array. We iterate through the array once.

// Space Complexity:
// O(1) because we use a constant number of variables regardless of the input size.

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int prev2 = 0; 
        int prev1 = 0; 
        for (int i = 2; i <= n; i++) {
            int costFromPrev1 = prev1 + cost[i - 1];

            int costFromPrev2 = prev2 + cost[i - 2];
            int current = Math.min(costFromPrev1, costFromPrev2);

            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}