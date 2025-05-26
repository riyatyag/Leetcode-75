// Problem Statement:
// You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
// You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
// Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.

// Approach:
// For each spell `s` in `spells`, we need to find how many potions `p` in `potions` satisfy `s * p >= success`.
// This inequality can be rewritten as `p >= success / s`.
// Since we are looking for the number of potions greater than or equal to a certain value, and we need to do this efficiently for all spells, sorting the `potions` array will be beneficial. Once `potions` is sorted, we can use binary search to find the first potion that meets the minimum strength requirement. All potions from that point to the end of the array will also satisfy the condition.

// Steps:
// 1. Sort the `potions` array in ascending order. This takes O(M log M) time.
// 2. Initialize an integer array `pairs` of length `n` to store the results.
// 3. Iterate through each `spell` in `spells` (from `i = 0` to `n-1`):
//    a. For the current `spell = spells[i]`, we need to find the minimum `potion_strength` such that `spell * potion_strength >= success`.
//    b. This means `potion_strength >= success / spell`. To avoid floating point issues and ensure correct integer division, we can calculate the `minPotionValue` required.
//       `minPotionValue = (success + spell - 1) / spell` (for positive numbers, this is equivalent to ceil(success / spell)).
//    c. Perform a binary search (specifically, `lower_bound` or `ceiling` search) on the sorted `potions` array to find the index of the first potion `p` such that `p >= minPotionValue`.
//       - Initialize `left = 0`, `right = m - 1`.
//       - Initialize `firstSuccessfulIndex = m` (if no such potion is found, it means 0 successful pairs).
//       - While `left <= right`:
//         - `mid = left + (right - left) / 2`.
//         - If `(long)potions[mid] * spell >= success`:
//           - This `potions[mid]` is a successful potion. It might be the first one, or there could be an earlier one. Store `mid` as a potential `firstSuccessfulIndex` and try to find an even smaller index in the left half: `firstSuccessfulIndex = mid; right = mid - 1;`.
//         - Else (`(long)potions[mid] * spell < success`):
//           - `potions[mid]` is too small. We need a larger potion. Search in the right half: `left = mid + 1;`.
//    d. The number of successful pairs for the current spell will be `m - firstSuccessfulIndex`. Store this in `pairs[i]`.
// 4. Return the `pairs` array.

// Time Complexity:
// - Sorting `potions`: O(M log M).
// - Iterating through `n` spells: O(N).
// - For each spell, performing binary search on `potions`: O(log M).
// - Total time complexity: O(M log M + N log M).
// Given N, M <= 10^5, N log M would be roughly 10^5 * log(10^5) ~ 10^5 * 17, which is efficient.

// Space Complexity:
// O(log M) or O(M) for sorting `potions` depending on the sort implementation (in-place or not), plus O(N) for the `pairs` array.
// Effectively, O(N + M) if considering the output array and sorting's auxiliary space. If sorting is in-place, then O(N) for output array.

import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions); 
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];

        for (int i = 0; i < n; i++) {
            long currentSpell = spells[i];
                        
            int low = 0;
            int high = m - 1;
            int firstSuccessfulIndex = m; 
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if ((long)potions[mid] * currentSpell >= success) {
                    firstSuccessfulIndex = mid; 
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
       pairs[i] = m - firstSuccessfulIndex;
        }

        return pairs;
    }
}