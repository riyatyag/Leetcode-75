// Problem Statement:
// Given an array of integers nums, calculate the pivot index of this array.
// The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
// If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
// Return the leftmost pivot index. If no such index exists, return -1.

// Approach:
// A straightforward approach involves calculating prefix sums or, more efficiently, using a single pass with two sums: total sum and current left sum.

// Steps:
// 1. Calculate the total sum of all elements in the array. Let this be `totalSum`.
// 2. Initialize `leftSum` to 0. This will keep track of the sum of elements to the left of the current index.
// 3. Iterate through the array from `i = 0` to `nums.length - 1`.
// 4. For each index `i`:
//    a. The `rightSum` can be calculated as `totalSum - leftSum - nums[i]`.
//    b. If `leftSum` is equal to `rightSum`, then `i` is a pivot index. Since we are looking for the leftmost pivot index, we can return `i` immediately.
//    c. After checking, update `leftSum` by adding `nums[i]` to it, preparing for the next iteration.
// 5. If the loop completes without finding a pivot index, return -1.

// This approach avoids recalculating right sums repeatedly, making it efficient.

// Time Complexity:
// O(N), where N is the length of the `nums` array.
// - The first pass to calculate `totalSum` takes O(N).
// - The second pass to find the pivot index takes O(N).
// Overall, the time complexity is linear.

// Space Complexity:
// O(1), as only a few variables (`totalSum`, `leftSum`, `rightSum`) are used, which do not scale with the input size.

// Optimal Solution:
class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }
}