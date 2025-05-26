// Problem Statement:
// Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
// Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
// Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
// Return the minimum integer k such that she can eat all the bananas within h hours.

// Approach:
// This problem asks for the minimum eating speed 'k'. The range of possible values for 'k' is from 1 (eating 1 banana per hour) up to the maximum number of bananas in any single pile (since Koko can finish a pile in one hour at most, if k is equal to or greater than the pile size).
// Notice that if Koko can finish all bananas with a speed 'k', she can also finish them with any speed 'k' > 'k'. This monotonic property suggests that we can use binary search on the possible values of 'k'.

// 1. Define the search space for 'k':
//    - The minimum possible speed 'k' is 1.
//    - The maximum possible speed 'k' is the maximum number of bananas in any single pile (max(piles[i])). This is because if k is greater than the largest pile, it acts the same as if k equals the largest pile for that specific pile, and larger k values will only finish piles faster or at the same rate.

// 2. Implement a helper function `canFinish(piles, h, k)`:
//    This function will check if Koko can finish all bananas within 'h' hours given a speed 'k'.
//    - Initialize `hoursNeeded = 0`.
//    - Iterate through each pile `p` in `piles`:
//      - Calculate hours needed for this pile: `Math.ceil((double) p / k)`. We use `Math.ceil` because if a pile has less than 'k' bananas, it still takes a full hour to finish. Integer division `(p + k - 1) / k` can also be used for ceiling division.
//      - Add this to `hoursNeeded`.
//    - Return `hoursNeeded <= h`.

// 3. Binary Search:
//    - Initialize `low = 1` and `high = max(piles[i])`.
//    - Initialize `ans = high` (or a very large number) to store the minimum 'k' found.
//    - While `low <= high`:
//      - Calculate `mid = low + (high - low) / 2`.
//      - If `canFinish(piles, h, mid)` is true:
//        - It means `mid` is a possible speed, and we might be able to do even better (find a smaller `k`). So, store `mid` as a potential answer and try to search in the lower half: `ans = mid; high = mid - 1;`
//      - Else (`canFinish(piles, h, mid)` is false):
//        - It means `mid` is too slow. We need a faster speed. Search in the upper half: `low = mid + 1;`
//    - Return `ans`.

// Time Complexity:
// - Finding the maximum pile: O(N), where N is the number of piles.
// - Binary search performs log(MaxPileValue) iterations.
// - Inside each iteration, `canFinish` function iterates through all N piles.
// - So, the total time complexity is O(N * log(MaxPileValue)).
// Given `piles.length <= 10^4` and `piles[i] <= 10^9`, `MaxPileValue` can be up to 10^9. `log(10^9)` is approximately 30.
// So, the complexity is roughly 10^4 * 30, which is efficient enough.

// Space Complexity:
// O(1) extra space, besides the input array.


class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for (int pile : piles) {
            if (pile > maxPile) {
                maxPile = pile;
            }
        }

        int low = 1;
        int high = maxPile;
        int minK = maxPile; 
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canFinish(piles, h, mid)) {
                minK = mid;
                high = mid - 1;
            } else {
                low = mid + 1; 
            }
        }

        return minK;
    }

    private boolean canFinish(int[] piles, int h, int k) {
        long hoursNeeded = 0; 
        for (int pile : piles) {
            hoursNeeded += (pile + k - 1) / k; 
            if (hoursNeeded > h) { 
                return false;
            }
        }
        return hoursNeeded <= h;
    }
}