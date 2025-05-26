// Problem Statement:
// You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k.
// You must choose a subsequence of indices from nums1 of length k.
// For chosen indices i0, i1, ..., ik - 1, your score is defined as:
// The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
// It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
// Return the maximum possible score.
// A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.

// Approach:
// The problem asks us to maximize (sum of nums1 elements) * (minimum of nums2 elements) for a subsequence of length k.
// The key observation here is that the `min(nums2)` part of the score makes it tricky. If we fix the minimum `nums2` element, say `nums2[j]`, then all other `k-1` elements we choose must have their corresponding `nums2` values greater than or equal to `nums2[j]`. To maximize the score, we would then pick the `k-1` largest `nums1` values among those satisfying the `nums2` condition, plus `nums1[j]`.

// This suggests an approach where we sort the pairs (nums1[i], nums2[i]) based on `nums2[i]` in a specific order.
// If we sort the pairs in decreasing order of `nums2[i]`, when we iterate through them, the current `nums2[i]` will be the minimum among all `nums2` values encountered so far (and thus, a candidate for the `min(nums2)` in our score).

// Algorithm:
// 1. Create an array of pairs, where each pair is `[nums1[i], nums2[i]]`.
// 2. Sort this array of pairs in descending order based on `nums2` values. If `nums2` values are equal, the order of `nums1` doesn't matter for the `min(nums2)` part, but sorting by `nums1` could be considered for deterministic behavior (though not strictly necessary for correctness). The primary sort key is `nums2` descending.
// 3. Initialize `maxScore = 0L` and `currentSum = 0L`.
// 4. Use a `min-priority queue` to keep track of the `k-1` largest `nums1` values corresponding to the chosen `nums2` elements (whose values are >= the current `min(nums2)`). When we consider a new pair `(n1, n2)`, `n2` becomes the candidate for `min(nums2)`. We need to ensure we have `k` elements in total (the current `n1` plus `k-1` from the PQ).
// 5. Iterate through the sorted pairs:
//    For each pair `(n1, n2)`:
//    a. Add `n1` to the `currentSum`.
//    b. Add `n1` to the `min-priority queue`.
//    c. If the size of the `min-priority queue` exceeds `k`:
//       - Remove the smallest element from the `min-priority queue` (which is `pq.poll()`) and subtract it from `currentSum`. This maintains the `k` largest `nums1` values up to this point.
//    d. If the size of the `min-priority queue` is exactly `k`:
//       - Calculate the current score: `currentScore = currentSum * n2`.
//       - Update `maxScore = Math.max(maxScore, currentScore)`.

// Why this works:
// By sorting by `nums2` in descending order, when we consider `nums2[i]`, it is guaranteed to be the *minimum* `nums2` value among the `k` elements we select, assuming we pick `nums1[i]` and `k-1` other `nums1` values from elements whose `nums2` values are `>= nums2[i]`. The min-priority queue helps us maintain the `k-1` largest `nums1` values efficiently.

// Time Complexity:
// - Creating pairs: O(N)
// - Sorting pairs: O(N log N)
// - Iterating through sorted pairs: N iterations.
//   - Inside the loop, priority queue operations (`offer`, `poll`, `peek`) take O(log k) time.
// - Total time complexity: O(N log N + N log k). Since `k <= N`, this simplifies to O(N log N).

// Space Complexity:
// - Storing pairs: O(N)
// - Priority queue: O(k)
// - Total space complexity: O(N).

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);

        long maxScore = 0;
        long currentSum = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int n1 = pairs[i][0];
            int n2 = pairs[i][1]; 

            minHeap.offer(n1);
            currentSum += n1;

            if (minHeap.size() > k) {
                currentSum -= minHeap.poll();
            }

            if (minHeap.size() == k) {
                maxScore = Math.max(maxScore, currentSum * n2);
            }
        }

        return maxScore;
    }
}

