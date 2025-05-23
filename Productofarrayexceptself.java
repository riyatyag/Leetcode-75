// Problem Statement:
// Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
// You must write an algorithm that runs in O(n) time and without using the division operation.
//
// Approach:
// The problem asks us to find the product of all elements except the current element at each index without using division and in O(n) time.
//
// We can solve this problem by using two passes.
//
// In the first pass, we calculate the product of all elements to the left of the current element. We store this in our result array.
// Let `answer[i]` be the product of all elements to the left of `nums[i]`.
// So, `answer[0]` will be 1 (as there are no elements to its left).
// For `i > 0`, `answer[i] = answer[i-1] * nums[i-1]`.
//
// In the second pass, we calculate the product of all elements to the right of the current element and multiply it with the existing product in `answer[i]` (which is the product of elements to the left).
// We can use a variable, say `rightProduct`, initialized to 1, to keep track of the product of elements to the right.
// We iterate from right to left (from `n-1` down to `0`).
// For each `i`, `answer[i] = answer[i] * rightProduct`.
// Then, we update `rightProduct = rightProduct * nums[i]`.
//
// This approach ensures that `answer[i]` eventually holds the product of all elements to its left multiplied by the product of all elements to its right, which is the product of all elements except `nums[i]`.
//
// Time Complexity:
// O(n) because we iterate through the array twice (two passes). Each pass takes O(n) time.
//
// Space Complexity:
// O(1) extra space complexity. The output array does not count as extra space for space complexity analysis.
// We only use a few extra variables (`rightProduct`).

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }

        return answer;
    }
}