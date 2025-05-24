// Problem Statement:
// Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.

// Approach:
// To solve this problem, we need to count the occurrences of each number in the array and then check if these occurrence counts themselves are unique.
// This can be done using two hash structures:
// 1. A HashMap (or frequency array if constraints allow) to store the frequency of each number in the input array `arr`.
// 2. A HashSet to store the frequencies obtained from the first map. If we try to add a frequency to the HashSet that already exists, it means that frequency is not unique, and we can immediately return false.

// Steps:
// 1. Create a HashMap `counts` to store `(number -> its_frequency)`.
// 2. Iterate through the input array `arr`. For each number, increment its count in the `counts` HashMap.
// 3. Create a HashSet `uniqueFrequencies` to store the frequencies observed.
// 4. Iterate through the values (frequencies) in the `counts` HashMap.
// 5. For each frequency `f`:
//    a. Try to add `f` to the `uniqueFrequencies` HashSet.
//    b. If `add()` returns `false` (meaning `f` was already present in the set), then the frequencies are not unique, so return `false`.
// 6. If the loop completes without returning `false`, it means all frequencies are unique, so return `true`.

// Time Complexity:
// O(N), where N is the length of the input array `arr`.
// - Counting frequencies in the HashMap takes O(N) time (on average).
// - Iterating through the HashMap values and adding them to the HashSet takes O(M) time, where M is the number of unique elements in `arr`. In the worst case, M can be N, so it's O(N).
// Overall, the time complexity is dominated by iterating through the input array, making it O(N).

// Space Complexity:
// O(M), where M is the number of unique elements in `arr`.
// - The HashMap `counts` can store up to M unique elements.
// - The HashSet `uniqueFrequencies` can store up to M unique frequencies.
// In the worst case, if all elements are distinct, M could be N, leading to O(N) space.

// Optimal Solution:
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        Set<Integer> uniqueFrequencies = new HashSet<>();
        for (int freq : counts.values()) {
            if (!uniqueFrequencies.add(freq)) {
                return false;
            }
        }
        return true;
    }
}