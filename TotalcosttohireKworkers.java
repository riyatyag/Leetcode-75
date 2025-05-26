// Problem Statement:
// You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
// You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:
// You will run k sessions and hire exactly one worker in each session.
// In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index.
// If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
// A worker can only be chosen once.
// Return the total cost to hire exactly k workers.

// Approach:
// This problem involves repeatedly selecting the minimum element from two specific ends of a dynamically changing array. This pattern is well-suited for using min-priority queues (min-heaps). We need two min-priority queues: one for the first `candidates` workers and one for the last `candidates` workers.

// We will maintain two pointers, `left` and `right`, initially pointing to the start and end of the `costs` array, respectively.
// We also need two min-priority queues:
// - `pq1`: stores costs from the left side of the array.
// - `pq2`: stores costs from the right side of the array.

// The tie-breaking rule (smallest index) is naturally handled by `PriorityQueue` in Java if costs are equal; it will pick the one inserted earlier (if multiple items with same priority are present, the order depends on implementation, but here we explicitly pick from left first, then right, which effectively gives preference to smaller indices). Alternatively, one can store `int[] {cost, index}` in the priority queue and define a custom comparator. However, the problem statement "Break the tie by the smallest index" means among the available workers *in the current selection pool (first/last candidates)*, not globally across the entire array.
// The crucial part is how elements are added to `pq1` and `pq2`. When we pick a worker, we must replace it with the next available worker from its side if possible.

// Algorithm:
// 1. Initialize `totalCost = 0L`.
// 2. Initialize two `PriorityQueue<Integer>`: `pq1` for the left side and `pq2` for the right side.
// 3. Initialize two pointers: `left = 0`, `right = costs.length - 1`.

// 4. Fill the initial priority queues:
//    - Populate `pq1` with the first `candidates` workers from `costs` (i.e., `costs[0]` to `costs[candidates - 1]`). Increment `left` pointer after each addition.
//    - Populate `pq2` with the last `candidates` workers from `costs` (i.e., `costs[costs.length - candidates]` to `costs[costs.length - 1]`).
//      Be careful to ensure `left <= right` when adding to `pq2` to avoid adding duplicate workers already added to `pq1` when `2 * candidates > costs.length`. Decrement `right` pointer after each addition.

// 5. Hire `k` workers:
//    Loop `k` times (for `session = 0` to `k - 1`):
//    a. Check if `pq1` is empty. If so, take from `pq2`.
//    b. Check if `pq2` is empty. If so, take from `pq1`.
//    c. If both are non-empty:
//       - Compare `pq1.peek()` and `pq2.peek()`.
//       - If `pq1.peek() <= pq2.peek()`:
//         - Take from `pq1`: `cost = pq1.poll()`.
//         - If `left <= right` (meaning there are still unconsidered workers between `left` and `right`), add `costs[left]` to `pq1` and increment `left`.
//       - Else (`pq2.peek() < pq1.peek()`):
//         - Take from `pq2`: `cost = pq2.poll()`.
//         - If `left <= right`, add `costs[right]` to `pq2` and decrement `right`.
//    d. Add `cost` to `totalCost`.

// 6. Return `totalCost`.

// Edge Case / Important Detail: Overlapping windows
// If `2 * candidates > costs.length`, the initial filling of `pq1` and `pq2` needs to be careful not to add the same worker twice. The `left <= right` condition during initial filling and subsequent replenishment correctly handles this. The `left` pointer only moves rightwards, and `right` only moves leftwards. They meet or cross when all unique workers have been covered.

// Time Complexity:
// - Initial filling of PQs: O(candidates * log(candidates)) for `2 * candidates` insertions.
// - Hiring `k` workers: `k` times we perform a `peek`, `poll`, and `offer` operation. Each of these takes O(log(candidates)) time.
// - Total time complexity: O(candidates * log(candidates) + k * log(candidates)).
// Since `candidates <= N` and `k <= N`, in the worst case, this is O(N log N).

// Space Complexity:
// - Two priority queues storing at most `2 * candidates` elements.
// - Space complexity: O(candidates).
// Since `candidates <= N`, this is O(N) in the worst case.

import java.util.PriorityQueue;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        long totalCost = 0;
        
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(); 
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        int left = 0;
        int right = costs.length - 1;

        for (int i = 0; i < candidates; i++) {
            if (left <= right) { 
                pq1.offer(costs[left]);
                left++;
            }
            if (left <= right) { 
                pq2.offer(costs[right]);
                right--;
            }
        }
        for (int i = 0; i < k; i++) {
            if (pq1.isEmpty()) { 
                totalCost += pq2.poll();
            } else if (pq2.isEmpty()) { 
                totalCost += pq1.poll();
            } else if (pq1.peek() <= pq2.peek()) { 
                totalCost += pq1.poll();
                if (left <= right) {
                    pq1.offer(costs[left]);
                    left++;
                }
            } else { 
                totalCost += pq2.poll();
                if (left <= right) {
                    pq2.offer(costs[right]);
                    right--;
                }
            }
        }

        return totalCost;
    }
}