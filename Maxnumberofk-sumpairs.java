// Problem Statement:
// You are given an integer array nums and an integer k.
// In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
// Return the maximum number of operations you can perform on the array.

// Approach:
// This problem can be efficiently solved using a hash map (HashMap in Java) or by sorting the array.

// Approach 1: Using a HashMap
// 1. Initialize a HashMap to store the frequency of each number in the array.
// 2. Initialize a counter for the number of operations to 0.
// 3. Iterate through the array `nums`. For each number `num`:
//    a. Calculate the `complement` needed to sum to `k`: `complement = k - num`.
//    b. Check if the `complement` exists in the hash map and its count is greater than 0.
//    c. If it exists and its count is positive, it means we found a pair. Increment the operation count.
//       Decrement the count of `complement` in the hash map.
//    d. If the `complement` does not exist or its count is 0, increment the count of the current `num` in the hash map.
// This approach ensures that each number is used at most once in a pair.

// Approach 2: Sorting and Two Pointers
// 1. Sort the `nums` array in ascending order.
// 2. Initialize two pointers, `left` at the beginning of the array (index 0) and `right` at the end of the array (index `nums.length - 1`).
// 3. Initialize a counter for the number of operations to 0.
// 4. While `left` is less than `right`:
//    a. Calculate the `currentSum = nums[left] + nums[right]`.
//    b. If `currentSum == k`:
//       Increment the operation count.
//       Move `left` pointer one step to the right (`left++`).
//       Move `right` pointer one step to the left (`right--`).
//    c. If `currentSum < k`:
//       The sum is too small, so we need a larger number. Move `left` pointer one step to the right (`left++`).
//    d. If `currentSum > k`:
//       The sum is too large, so we need a smaller number. Move `right` pointer one step to the left (`right--`).
// 5. Return the total operation count.

// Time Complexity:
// Approach 1 (HashMap): O(N) on average, where N is the number of elements in `nums`. In the worst case (hash collisions), it could be O(N^2), but this is rare with good hash functions.
// Approach 2 (Sorting + Two Pointers): O(N log N) due to sorting, where N is the number of elements in `nums`. The two-pointer traversal takes O(N).

// Space Complexity:
// Approach 1 (HashMap): O(N) in the worst case, where all numbers are distinct and stored in the hash map.
// Approach 2 (Sorting + Two Pointers): O(1) if sorting is in-place (like `Arrays.sort()` in Java which uses Timsort and might use O(log N) or O(N) auxiliary space depending on the implementation details and data distribution, but typically considered O(log N) for competitive programming contexts or O(N) for large arrays). If not in-place, it could be O(N).

// Optimal Solution (using HashMap as it's generally faster for average cases):
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int operations = 0;

        for (int num : nums) {
            int complement = k - num;
            if (freqMap.containsKey(complement) && freqMap.get(complement) > 0) {
                operations++;
                freqMap.put(complement, freqMap.get(complement) - 1);
            } else {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }
        }
        return operations;
    }
}