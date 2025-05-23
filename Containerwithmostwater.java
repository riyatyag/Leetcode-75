// Problem Statement:
// You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
// Find two lines that together with the x-axis form a container, such that the container contains the most water.
// Return the maximum amount of water a container can store.
// Notice that you may not slant the container.

// Approach:
// This problem can be solved efficiently using the two-pointer approach.
// 1. Initialize two pointers, `left` at the beginning of the array (index 0) and `right` at the end of the array (index `n - 1`).
// 2. Initialize `maxArea` to 0.
// 3. While `left` is less than `right`:
//    a. Calculate the current width: `width = right - left`.
//    b. Calculate the current height: `h = Math.min(height[left], height[right])`. The height of the container is limited by the shorter of the two lines.
//    c. Calculate the current area: `currentArea = width * h`.
//    d. Update `maxArea = Math.max(maxArea, currentArea)`.
//    e. To find a potentially larger area, we need to try to increase the height of the container. This means moving the pointer of the shorter line inwards.
//       If `height[left] < height[right]`, increment `left`.
//       Else (if `height[right] <= height[left]`), decrement `right`.
// 4. Return `maxArea`.

// The intuition behind moving the shorter pointer:
// If we move the taller pointer inwards, the width will definitely decrease. Since the height is limited by the shorter line, moving the taller line might not increase the height (it might even decrease it if the new line is shorter than the original shorter line). Thus, the area would definitely decrease.
// However, if we move the shorter pointer inwards, the width decreases, but there's a chance that the new line at the shorter pointer's position is taller than the previous shorter line. If this new line is sufficiently tall, it could compensate for the reduced width and potentially lead to a larger area.

// Time Complexity:
// O(N), where N is the length of the `height` array.
// The two pointers traverse the array from both ends, meeting in the middle. Each pointer moves at most N times, resulting in a single pass.

// Space Complexity:
// O(1), as only a few variables are used to store pointers, current area, and max area. No additional data structures are used that scale with the input size.

// Optimal Solution:
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;
            maxArea = Math.max(maxArea, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}