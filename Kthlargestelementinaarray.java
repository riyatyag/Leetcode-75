// Problem Statement:
// Given an integer array nums and an integer k, return the kth largest element in the array.
// Note that it is the kth largest element in the sorted order, not the kth distinct element.
// Can you solve it without sorting?

// Approach:
// This problem can be efficiently solved using a min-priority queue (min-heap).
// The idea is to maintain a min-heap of size `k`.
// We iterate through each number in the input array `nums`.
// For each number, we add it to the min-heap.
// If the size of the min-heap exceeds `k`, it means we have more than `k` elements. Since it's a min-heap, the smallest element among these `k+1` elements is at the root. We remove this smallest element (`minHeap.poll()`) to ensure that the heap always contains the `k` largest elements encountered so far.
// After iterating through all numbers in `nums`, the root of the min-heap (`minHeap.peek()`) will be the `k`th largest element in the entire array.

// Example Walkthrough (nums = [3,2,1,5,6,4], k = 2):
// 1. Initialize minHeap = []
// 2. num = 3: minHeap = [3]
// 3. num = 2: minHeap = [2, 3] (heap property maintained)
// 4. num = 1: minHeap = [1, 3, 2] (heap property maintained)
// 5. num = 5: minHeap = [1, 3, 2, 5] (size > k=2). Poll smallest (1). minHeap = [2, 3, 5] (heap property maintained, effectively [2,3,5] in some internal order, smallest is 2).
// 6. num = 6: minHeap = [2, 3, 5, 6] (size > k=2). Poll smallest (2). minHeap = [3, 5, 6] (heap property maintained, effectively [3,5,6] in some internal order, smallest is 3).
// 7. num = 4: minHeap = [3, 5, 6, 4] (size > k=2). Poll smallest (3). minHeap = [4, 5, 6] (heap property maintained, effectively [4,5,6] in some internal order, smallest is 4).
// After loop, minHeap contains [4, 5, 6]. The smallest element in this heap is 4.
// Wait, the example output is 5. Let's re-check the logic.
// If we have a min-heap of size k, it means the k elements currently in the heap are the k largest elements seen so far.
// Let's re-trace with the correct understanding:
// minHeap of size k=2.
// 1. num = 3: minHeap = [3]
// 2. num = 2: minHeap = [2, 3]
// 3. num = 1: minHeap = [1, 2, 3]. Size is 3, k=2. Poll 1. minHeap = [2, 3].
// 4. num = 5: minHeap = [2, 3, 5]. Size is 3, k=2. Poll 2. minHeap = [3, 5].
// 5. num = 6: minHeap = [3, 5, 6]. Size is 3, k=2. Poll 3. minHeap = [5, 6].
// 6. num = 4: minHeap = [4, 5, 6]. Size is 3, k=2. Poll 4. minHeap = [5, 6].
// The smallest element in the minHeap, which is `minHeap.peek()`, is 5. This is the 2nd largest element.
// This logic is correct. The initial trace had a slight error in what was polled.

// Time Complexity:
// - We iterate through all `N` elements in the `nums` array.
// - For each element, we perform an `offer` operation (insertion) and potentially a `poll` operation (deletion). Both operations on a priority queue of size `k` take O(log k) time.
// - Therefore, the total time complexity is O(N log k).

// Space Complexity:
// - The min-priority queue stores at most `k` elements.
// - Therefore, the space complexity is O(k).

// Optimal Solution (using QuickSelect - not implemented here, but good to know):
// The QuickSelect algorithm (a variation of QuickSort) can find the k-th largest (or smallest) element in average O(N) time. In the worst case, it can be O(N^2), but with a good pivot selection strategy (e.g., median-of-medians), it can be guaranteed O(N). The priority queue approach is simpler to implement and often sufficient.


class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}