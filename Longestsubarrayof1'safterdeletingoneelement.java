// Problem Statement:
// Given a binary array nums, you should delete one element from it.
// Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

// Approach:
// This problem can be effectively solved using a sliding window approach. The goal is to find the longest subarray of 1s after deleting at most one 0. This is equivalent to finding the longest subarray that contains at most one 0.

// Algorithm:
// 1. Initialize `left` pointer to 0, `maxLen` to 0, and `zeroCount` to 0.
// 2. Iterate with a `right` pointer from 0 to `nums.length - 1`:
//    a. If `nums[right]` is 0, increment `zeroCount`.
//    b. While `zeroCount` is greater than 1 (meaning we have more than one zero in the current window), shrink the window from the left:
//       i. If `nums[left]` is 0, decrement `zeroCount`.
//       ii. Increment `left`.
//    c. After ensuring the window `[left, right]` contains at most one 0, the length of the current subarray of 1s (potentially including one 0 that will be deleted) is `right - left`.
//       Update `maxLen = Math.max(maxLen, right - left)`.
// 3. The `maxLen` computed will be the length of the longest subarray with at most one zero. Since we *must* delete one element, if the original array consisted only of 1s (e.g., `[1,1,1]`), `maxLen` would be `nums.length`. In this case, we need to subtract 1 because one '1' must be deleted. If there was at least one zero in the original array, `maxLen` will correctly represent the longest subarray after deleting one zero. The `maxLen` will effectively be `(count of 1s in left part) + (count of 1s in right part)`. The deleted element is the 0 that enabled joining these two parts, or any 1 if only 1s exist.

// Edge Case: If the input array contains only 1s, we must delete one 1. In this scenario, `maxLen` will be `nums.length`, but the answer should be `nums.length - 1`. The `right - left` calculation in step 2.c naturally gives `nums.length` for an all-ones array. So, we need to subtract 1 from the final `maxLen` if `maxLen` is equal to `nums.length` and all elements were 1s. A simpler way is to initialize `maxLen` to 0 and calculate `right - left` (which is the length of the window with at most one zero). Since we delete one element, the effective length of the ones subarray is `right - left`. If there are no zeros, the window expands to full length, and we subtract 1. If there's at least one zero, the window will span across one zero, and `right - left` correctly gives the sum of 1s on either side of the deleted zero.

// Final adjustment: The `maxLen` derived from `right - left` represents the length of the subarray if we include one 0 (which we plan to delete) or if it's all 1s. Since we must delete *one* element, the maximum length of 1s will always be `maxLen`. If `maxLen` ends up being `nums.length` (meaning the original array was all 1s), we must return `nums.length - 1`. Otherwise, `maxLen` is already the answer.

// A more robust way to handle the "must delete one element" constraint for all 1s array:
// The `maxLen` calculated as `right - left` directly represents the maximum number of 1s that can be grouped after removing *one* zero. If there were no zeros, `zeroCount` would remain 0, and `right - left` would simply be `nums.length`. In this case, the answer needs to be `nums.length - 1`. If `nums.length` is 1 and it's `[1]`, the answer should be 0.
// So, the length of the "longest subarray of 1s after deleting one element" can be thought of as `max(current window size - 1)`.

// Corrected Approach:
// 1. Initialize `left = 0`, `zeroCount = 0`, `maxLength = 0`.
// 2. Iterate `right` from 0 to `nums.length - 1`.
//    a. If `nums[right] == 0`, increment `zeroCount`.
//    b. While `zeroCount > 1`:
//       If `nums[left] == 0`, decrement `zeroCount`.
//       Increment `left`.
//    c. The current window `[left, right]` has at most one zero. The length of the subarray of 1s we can form by deleting *one* element in this window (which would be the single zero if it exists, or one of the 1s if no zeros exist) is `(right - left)`. We need to take `max(maxLength, right - left)`.
// 3. After the loop, if `maxLength` is `nums.length` (which happens if the input array was all 1s), then we must subtract 1 because one '1' had to be deleted. Otherwise, `maxLength` is already the correct answer. The initial condition for `maxLength` is 0. If `nums.length` is 1 and it's `[1]`, `maxLength` would be 0, which is correct.

// Consider `[1,1,1]`:
// R=0: nums[0]=1, zeroCount=0, maxL=0. Window [0,0], len=0.
// R=1: nums[1]=1, zeroCount=0, maxL=1. Window [0,1], len=1.
// R=2: nums[2]=1, zeroCount=0, maxL=2. Window [0,2], len=2.
// Loop ends. Final maxL = 2. This is correct (3-1).

// Consider `[1,1,0,1]`:
// R=0: nums[0]=1, zeroC=0, maxL=0. Window [0,0], len=0.
// R=1: nums[1]=1, zeroC=0, maxL=1. Window [0,1], len=1.
// R=2: nums[2]=0, zeroC=1, maxL=2. Window [0,2], len=2.
// R=3: nums[3]=1, zeroC=1, maxL=3. Window [0,3], len=3.
// Loop ends. Final maxL = 3. This is correct.

// The `right - left` calculation computes the length of the window. If the window contains exactly one '0', then `right - left` is `(count of 1s in left part) + 1 (for the zero) + (count of 1s in right part)`. When we delete the zero, the count of 1s becomes `(right - left - 1)`.
// If the window contains no '0's (meaning the whole array is '1's), then `right - left` is `nums.length - 1` when `right` reaches `nums.length - 1` and `left` is 0. The actual count of 1s after deleting one is `nums.length - 1`. So, it's `(right - left)`.

// Let's refine `maxLength` calculation: `right - left` is the current window size. We want the length of the *subarray of 1's after deleting one element*.
// If `zeroCount == 0`: means the window is all 1s. We must delete one 1. So `current_effective_len = (right - left + 1) - 1 = right - left`.
// If `zeroCount == 1`: means the window has one 0. We delete that 0. So `current_effective_len = (right - left + 1) - 1 = right - left`.
// So `maxLength` should always be updated with `right - left`.

// One final edge case: If `nums` contains only one element and it's a 1, e.g., `[1]`.
// R=0: nums[0]=1, zeroC=0. maxL=0. Window [0,0], len=0.
// Loop ends. Return maxL which is 0. Correct.

// The only tricky part is if the entire array is 1s, e.g., `[1,1,1]`.
// `maxLength` will be updated as `right - left`.
// R=0, L=0: `maxLength = max(0, 0-0) = 0`
// R=1, L=0: `maxLength = max(0, 1-0) = 1`
// R=2, L=0: `maxLength = max(1, 2-0) = 2`
// This yields 2, which is `nums.length - 1`. So the `right - left` calculation works directly for this.

// Final algorithm:
// 1. `left = 0`, `zeroCount = 0`, `maxLength = 0`.
// 2. Iterate `right` from 0 to `nums.length - 1`:
//    a. If `nums[right] == 0`, increment `zeroCount`.
//    b. While `zeroCount > 1`:
//       If `nums[left] == 0`, decrement `zeroCount`.
//       Increment `left`.
//    c. `maxLength = Math.max(maxLength, right - left)`. (This `right - left` is the length of the current valid window, which will become the length of 1s after deleting the element).
// 3. Return `maxLength`.

// This covers all cases.

class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}