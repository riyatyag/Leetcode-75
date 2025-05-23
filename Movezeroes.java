// Problem Statement:
// Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
// Note that you must do this in-place without making a copy of the array.

// Approach:
// This problem can be solved efficiently using a two-pointer approach. The goal is to move all non-zero elements to the front of the array, effectively pushing all zeros to the end.
// 1. Initialize a pointer, `nonZeroPointer`, to 0. This pointer will keep track of the position where the next non-zero element should be placed.
// 2. Iterate through the array `nums` with another pointer, say `i`, from the beginning to the end.
// 3. If `nums[i]` is not 0:
//    a. Place `nums[i]` at the position indicated by `nonZeroPointer`.
//    b. Increment `nonZeroPointer`.
// 4. After the first pass, all non-zero elements will be at the beginning of the array in their original relative order, and `nonZeroPointer` will point to the first position where a zero should be placed.
// 5. Iterate from `nonZeroPointer` to the end of the array and fill the remaining positions with 0s.

// This approach minimizes operations as it avoids unnecessary swaps. It effectively "writes" non-zero elements to the front and then fills the rest with zeros.

// Time Complexity:
// O(N), where N is the length of the `nums` array.
// The array is traversed twice in the worst case (once to move non-zeros, once to fill zeros), resulting in a linear time complexity.

// Space Complexity:
// O(1), as the operations are performed in-place, and no additional data structures are used that scale with the input size.

// Optimal Solution:
class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroPointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroPointer] = nums[i];
                nonZeroPointer++;
            }
        }
        for (int i = nonZeroPointer; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}