// Problem Statement:
// There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.
// You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.

// Approach:
// To find the highest altitude, we need to simulate the biker's journey, keeping track of the current altitude at each point and updating the maximum altitude encountered so far.
// 1. The biker starts at point 0 with an altitude of 0. This is the initial `currentAltitude` and also the initial `maxAltitude`.
// 2. Iterate through the `gain` array. For each `gain[i]`:
//    a. Update the `currentAltitude` by adding `gain[i]` to it. This `currentAltitude` now represents the altitude at point `i+1`.
//    b. Compare the `currentAltitude` with `maxAltitude` and update `maxAltitude` if `currentAltitude` is greater.
// 3. After iterating through all elements in `gain`, `maxAltitude` will hold the highest altitude reached.

// Time Complexity:
// O(N), where N is the length of the `gain` array.
// We iterate through the `gain` array once. Each operation inside the loop takes constant time.

// Space Complexity:
// O(1), as only a few variables (`currentAltitude`, `maxAltitude`) are used, which do not depend on the input size.

// Optimal Solution:
class Solution {
    public int largestAltitude(int[] gain) {
        int currentAltitude = 0;
        int maxAltitude = 0; 
        for (int g : gain) {
            currentAltitude += g;
            if (currentAltitude > maxAltitude) {
                maxAltitude = currentAltitude;
            }
        }
        return maxAltitude;
    }
}