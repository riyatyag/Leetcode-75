// Problem Statement:
// Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

// Approach:
// This is a classic sliding window problem. We want to find the longest subarray that contains at most `k` zeros.
// The idea is to expand the window from the right and shrink it from the left when the constraint (at most `k` zeros) is violated.

// Algorithm:
// 1. Initialize two pointers, `left` and `right`, to 0.
// 2. Initialize `zeroCount` to 0, which will keep track of the number of zeros in the current window.
// 3. Initialize `maxLength` to 0, which will store the maximum length of a valid subarray found so far.
// 4. Iterate with the `right` pointer from 0 to `nums.length - 1`:
//    a. If `nums[right]` is 0, increment `zeroCount`.
//    b. While `zeroCount` is greater than `k`: (meaning we have more zeros than allowed)
//       i. If `nums[left]` is 0, decrement `zeroCount`.
//       ii. Increment `left` to shrink the window from the left.
//    c. After the `while` loop, the current window `[left, right]` is valid (contains at most `k` zeros).
//    d. Calculate the length of this valid window: `currentWindowLength = right - left + 1`.
//    e. Update `maxLength = Math.max(maxLength, currentWindowLength)`.
// 5. After the `for` loop finishes, `maxLength` will hold the maximum number of consecutive 1's achievable by flipping at most `k` zeros. Return `maxLength`.

// Time Complexity:
// O(N), where N is the length of the `nums` array.
// Both `left` and `right` pointers traverse the array at most once. Each element is visited and processed a constant number of times.

// Space Complexity:
// O(1), as only a few variables are used to store pointers and counts, independent of the input array size.

// Optimal Solution:
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}