// Problem Statement:
// A peak element is an element that is strictly greater than its neighbors.
// Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
// You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
// You must write an algorithm that runs in O(log n) time.

// Approach:
// The problem asks for an O(log n) solution, which strongly suggests using binary search.
// The key idea is to leverage the property nums[-1] = nums[n] = -∞ and the fact that nums[i] != nums[i + 1].
// This ensures that there is always at least one peak element.

// We can use a modified binary search:
// 1. Initialize `low = 0` and `high = nums.length - 1`.
// 2. While `low < high`:
//    a. Calculate `mid = low + (high - low) / 2`.
//    b. Compare `nums[mid]` with `nums[mid + 1]`.
//    c. If `nums[mid] > nums[mid + 1]`:
//       This means that `mid` could be a peak, or a peak exists to its left. We can eliminate the right half (from `mid + 1` onwards) because if `nums[mid]` is greater than `nums[mid+1]`, and eventually `nums[n]` is -∞, there must be a peak either at `mid` or somewhere before `mid`. So, we set `high = mid`.
//    d. If `nums[mid] < nums[mid + 1]`:
//       This means `mid` cannot be a peak (since its right neighbor is greater), and a peak must exist to its right. We eliminate `mid` and its left part. So, we set `low = mid + 1`.
// 3. When the loop terminates, `low` will be equal to `high`. This `low` (or `high`) will be the index of a peak element.
//    Why?
//    Consider the condition `nums[-1] = nums[n] = -∞`.
//    If `nums[mid] < nums[mid + 1]`, the "increasing slope" guarantees a peak to the right.
//    If `nums[mid] > nums[mid + 1]`, the "decreasing slope" guarantees a peak to the left or at `mid`.
//    The binary search continually narrows down the range until `low` and `high` converge on an index where `nums[mid]` is greater than its right neighbor (or it's the last element, where `nums[n]` is -∞). Since `nums[i] != nums[i+1]`, this ensures `nums[mid]` is also greater than its left neighbor if `mid` is not 0. If `mid` is 0, `nums[-1]` is -∞, so `nums[0]` is a peak.

// Time Complexity:
// The algorithm uses binary search, which halves the search space in each step.
// Thus, the time complexity is O(log n), where n is the number of elements in the `nums` array.

// Space Complexity:
// The algorithm uses a constant amount of extra space for variables like `low`, `high`, and `mid`.
// Thus, the space complexity is O(1).


class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid + 1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}