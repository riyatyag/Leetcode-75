// Problem Statement:
// You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
// Implement the SmallestInfiniteSet class:
// SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
// int popSmallest() Removes and returns the smallest integer contained in the infinite set.
// void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.

// Approach:
// The problem asks us to simulate an infinite set of positive integers, with operations to `popSmallest` and `addBack`.
// The key is that we are dealing with an "infinite" set, but operations are only on numbers up to 1000.
// This suggests that we don't need to explicitly store all infinite numbers. We only need to manage the numbers that have been "popped" and then "added back," and keep track of the smallest number not yet popped.

// We can use a combination of a `PriorityQueue` (min-heap) and a counter for this.
// `minHeap`: This will store numbers that have been `addBack`ed. Since we want the smallest, a min-heap is perfect.
// `currentSmallest`: This integer will represent the smallest positive integer that has NOT yet been popped or added back. Initially, it's 1.

// Operations:
// 1. `SmallestInfiniteSet()`:
//    - Initialize `currentSmallest = 1`.
//    - Initialize `minHeap` as an empty `PriorityQueue`.
//    - Optionally, use a `HashSet` to quickly check if a number is already in the `minHeap` before adding it, to satisfy the "if it is not already in the infinite set" condition for `addBack`.

// 2. `popSmallest()`:
//    - If `minHeap` is not empty and its peek (smallest element) is less than `currentSmallest`:
//      - This means there's a smaller number that was added back. Remove it from `minHeap` and return it.
//    - Else (if `minHeap` is empty or its peek is greater than or equal to `currentSmallest`):
//      - This means the smallest available number is `currentSmallest`. Return `currentSmallest` and increment `currentSmallest` for the next call.

// 3. `addBack(int num)`:
//    - If `num` is less than `currentSmallest`:
//      - This means `num` was previously popped and is now being added back.
//      - Before adding, check if `num` is already in the `minHeap` (using the `HashSet` for O(1) check). If not, add it to `minHeap` and to the `HashSet`.
//    - If `num` is greater than or equal to `currentSmallest`:
//      - This `num` is either not yet popped or is already considered part of the "infinite" sequence represented by `currentSmallest` and beyond. So, we do nothing as it's already "in" the set conceptually or explicitly.

// Time Complexity:
// - `SmallestInfiniteSet()`: O(1)
// - `popSmallest()`: O(log M) where M is the number of elements in the `minHeap`. In the worst case, M can be up to 1000 (constrained by `num <= 1000`). So, O(log 1000) which is roughly O(1) for practical purposes.
// - `addBack(int num)`: O(log M) for adding to `minHeap` and O(1) for `HashSet` operations. Overall O(log M).

// Space Complexity:
// - `minHeap`: Stores numbers that are added back. In the worst case, up to `1000` numbers can be added back. So, O(M) where M is the count of unique numbers added back, up to 1000.
// - `HashSet`: Stores the same numbers as `minHeap` for quick lookups. So, O(M).
// - Total space complexity: O(M) or O(max_num_value) if using a boolean array for numbers up to 1000. Given `num <= 1000` and `1000` calls, this is practical.

import java.util.PriorityQueue;
import java.util.HashSet;

class SmallestInfiniteSet {
    private PriorityQueue<Integer> minHeap;
    private HashSet<Integer> addedBackSet; 
    private int currentSmallest; 

    public SmallestInfiniteSet() {
        minHeap = new PriorityQueue<>();
        addedBackSet = new HashSet<>();
        currentSmallest = 1;
    }
    
    public int popSmallest() {
        if (!minHeap.isEmpty()) {
            int smallestFromHeap = minHeap.peek();
            if (smallestFromHeap < currentSmallest) {
                addedBackSet.remove(smallestFromHeap);
                return minHeap.poll();
            }
        }
        int result = currentSmallest;
        currentSmallest++; 
        return result;
    }
    
    public void addBack(int num) {
        if (num < currentSmallest && !addedBackSet.contains(num)) {
            minHeap.offer(num);
            addedBackSet.add(num);
        }
    }
}

