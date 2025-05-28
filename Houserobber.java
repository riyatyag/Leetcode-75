// Problem Statement:
// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. The only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
// Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

// Approach:
// This is a classic dynamic programming problem. Let's define `dp[i]` as the maximum amount of money that can be robbed from the first `i` houses without robbing adjacent houses.

// When considering `dp[i]` (the `i`-th house, 0-indexed at `nums[i]`):
// There are two choices:
// 1. Rob the `i`-th house: If we rob `nums[i]`, we cannot rob `nums[i-1]` (the previous house). So, the total profit would be `nums[i]` plus the maximum profit from houses up to `i-2`. This is `nums[i] + dp[i-2]`.
// 2. Do not rob the `i`-th house: If we don't rob `nums[i]`, then the maximum profit is simply the maximum profit from houses up to `i-1`. This is `dp[i-1]`.

// So, the recurrence relation is:
// `dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1])`

// Base Cases:
// - If there are no houses (`nums.length == 0`), the maximum profit is 0.
// - If there is only one house (`nums.length == 1`), the maximum profit is `nums[0]`.
// - If there are two houses (`nums.length == 2`):
//   - `dp[0] = nums[0]`
//   - `dp[1] = Math.max(nums[0], nums[1])` (rob either the first or the second, whichever is greater).

// Let's explicitly define dp for `i=0` and `i=1`:
// `dp[0] = nums[0]` (for the first house)
// `dp[1] = Math.max(nums[0], nums[1])` (for the first two houses)

// Example: `nums = [1, 2, 3, 1]`
// `n = 4`
// `dp` array of size `n`
// `dp[0] = 1`
// `dp[1] = Math.max(1, 2) = 2`
// `i = 2` (considering `nums[2] = 3`):
// `dp[2] = Math.max(nums[2] + dp[0], dp[1]) = Math.max(3 + 1, 2) = Math.max(4, 2) = 4`
// `i = 3` (considering `nums[3] = 1`):
// `dp[3] = Math.max(nums[3] + dp[1], dp[2]) = Math.max(1 + 2, 4) = Math.max(3, 4) = 4`
// Result is `dp[n-1] = dp[3] = 4`.

// Space Optimization:
// Notice that `dp[i]` only depends on `dp[i-1]` and `dp[i-2]`. We don't need the entire `dp` array.
// We can optimize space to O(1) by using just two variables to store the previous two maximum profits.

// Let `prev1` be `dp[i-1]` (max profit up to previous house).
// Let `prev2` be `dp[i-2]` (max profit up to house before previous).

// Initial values for optimized approach:
// If `nums.length == 0`, return 0.
// If `nums.length == 1`, return `nums[0]`.
// `prev2 = 0` (corresponds to dp[-1], max profit before any house, conceptually 0)
// `prev1 = nums[0]` (corresponds to dp[0], max profit from first house)

// Loop from `i = 1` to `nums.length - 1`:
// `current = Math.max(prev1, prev2 + nums[i])`
// `prev2 = prev1`
// `prev1 = current`

// The final result will be `prev1`.

// Time Complexity:
// O(N), where N is the number of houses. We iterate through the array once.

// Space Complexity:
// O(1) because we use a constant number of variables regardless of the input size.

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int prev2 = 0; 
        int prev1 = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int robCurrent = nums[i] + prev2;
            int notRobCurrent = prev1;

            int current = Math.max(robCurrent, notRobCurrent);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}