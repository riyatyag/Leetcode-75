// Problem Statement:
// You are given an integer array nums consisting of n elements, and an integer k.
// Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

// Approach:
// This problem can be solved using the sliding window technique. We need to find the subarray of a fixed length `k` that has the maximum sum (and thus the maximum average, since the divisor `k` is constant).

// Algorithm:
// 1. Calculate the sum of the first `k` elements. Let this be `currentSum` and initialize `maxSum` with this value.
// 2. Use a sliding window:
//    a. Start a loop from index `k` to `n - 1` (where `n` is `nums.length`).
//    b. In each iteration, update `currentSum` by adding the new element `nums[i]` and subtracting the element that is falling out of the window `nums[i - k]`. This efficiently moves the window one step to the right.
//    c. After updating `currentSum`, compare it with `maxSum` and update `maxSum = Math.max(maxSum, currentSum)`.
// 3. After the loop completes, `maxSum` will hold the maximum sum of any contiguous subarray of length `k`.
// 4. The maximum average is `maxSum / k`. Return this value as a double.

// Time Complexity:
// O(N), where N is the length of the `nums` array.
// We iterate through the array essentially twice: once for the initial sum and once for the sliding window. This results in a single pass overall, making it linear time complexity.

// Space Complexity:
// O(1), as only a few variables are used to store sums and pointers, independent of the input array size.

// Optimal Solution:
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long currentSum = 0; 
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
       long maxSum = currentSum;

        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }
        return (double) maxSum / k;
    }
}