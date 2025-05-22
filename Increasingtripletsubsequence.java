/**
 * Problem Statement:
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 * Approach:
 * This problem asks us to find if an increasing triplet subsequence exists in the given array.
 * A naive approach would be to use three nested loops, which would be O(N^3).
 * We are looking for an O(N) time complexity and O(1) space complexity solution as per the follow-up.
 *
 * We can achieve this by maintaining two variables:
 * `firstSmallest`: This variable will store the smallest number encountered so far.
 * `secondSmallest`: This variable will store the smallest number encountered so far that is greater than `firstSmallest`.
 *
 * Initialize both `firstSmallest` and `secondSmallest` to `Long.MAX_VALUE` (or some sufficiently large integer value).
 *
 * Iterate through the `nums` array:
 * 1. If `num <= firstSmallest`: Update `firstSmallest = num`. This means we found a new smallest number, which is potentially a better candidate for the start of a triplet.
 * 2. Else if `num <= secondSmallest`: Update `secondSmallest = num`. This means `num` is greater than `firstSmallest` but smaller than or equal to `secondSmallest`. It's a better candidate for the middle element of a triplet.
 * 3. Else (`num > secondSmallest`): This is the crucial part. If `num` is greater than `secondSmallest`, and we know `secondSmallest` is already greater than `firstSmallest`, then we have found `firstSmallest < secondSmallest < num`. Thus, an increasing triplet exists, and we can immediately return `true`.
 *
 * If the loop finishes without finding such a triplet, return `false`.
 *
 * Why this works:
 * - `firstSmallest` always holds the smallest number encountered so far.
 * - `secondSmallest` always holds the smallest number `x` that we've seen such that there is a number `y < x` that came before `x`.
 * - When we find a `num` greater than `secondSmallest`, it means we have `firstSmallest < secondSmallest < num`. The values stored in `firstSmallest` and `secondSmallest` might not be the actual elements from the specific triplet found, but their existence guarantees that such a triplet exists in the array. For example, if `firstSmallest` was set by `nums[i]` and `secondSmallest` by `nums[j]`, and we then find `nums[k]` such that `nums[k] > secondSmallest`, then `nums[i] < nums[j] < nums[k]` holds. Even if `firstSmallest` or `secondSmallest` were updated by elements that appeared after the true `i` or `j`, the property `firstSmallest < secondSmallest < num` still holds and proves the existence of *some* triplet.
 *
 * Time Complexity:
 * O(N), where N is the number of elements in the `nums` array. We iterate through the array exactly once.
 *
 * Space Complexity:
 * O(1), as we only use a few constant extra variables (`firstSmallest`, `secondSmallest`).
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        long firstSmallest = Long.MAX_VALUE;
        long secondSmallest = Long.MAX_VALUE;

        for (int num : nums) {
            if (num <= firstSmallest) {
                firstSmallest = num;
            } else if (num <= secondSmallest) { 
                secondSmallest = num;
            } else { 
                return true;
            }
        }

        return false;
    }
}