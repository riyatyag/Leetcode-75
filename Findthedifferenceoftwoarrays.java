// Problem Statement:
// Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
// answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
// answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
// Note that the integers in the lists may be returned in any order.

// Approach:
// To efficiently find distinct elements present in one array but not in another, HashSets are ideal because they provide O(1) average-time complexity for insertion and lookup (contains) operations.

// Steps:
// 1. Create two HashSets, `set1` and `set2`.
// 2. Populate `set1` with all distinct elements from `nums1`.
// 3. Populate `set2` with all distinct elements from `nums2`.
// 4. Initialize `answer0` (a list for elements in `nums1` not in `nums2`) and `answer1` (a list for elements in `nums2` not in `nums1`).
// 5. Iterate through each element `num` in `set1`:
//    a. If `set2` does not contain `num`, add `num` to `answer0`.
// 6. Iterate through each element `num` in `set2`:
//    a. If `set1` does not contain `num`, add `num` to `answer1`.
// 7. Create a `List<List<Integer>>` to hold `answer0` and `answer1` and return it.

// Time Complexity:
// O(N + M), where N is the length of `nums1` and M is the length of `nums2`.
// - Populating `set1`: O(N) on average.
// - Populating `set2`: O(M) on average.
// - Iterating `set1` (at most N distinct elements) and performing `contains` on `set2`: O(N) on average.
// - Iterating `set2` (at most M distinct elements) and performing `contains` on `set1`: O(M) on average.
// All operations combined result in linear time complexity with respect to the total number of elements.

// Space Complexity:
// O(N + M), where N is the number of distinct elements in `nums1` and M is the number of distinct elements in `nums2`.
// - `set1` can store up to N distinct elements.
// - `set2` can store up to M distinct elements.
// - The result lists `answer0` and `answer1` can also store up to N and M elements respectively.

// Optimal Solution:
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> answer0 = new ArrayList<>();
        for (int num : set1) {
            if (!set2.contains(num)) {
                answer0.add(num);
            }
        }

        List<Integer> answer1 = new ArrayList<>();
        for (int num : set2) {
            if (!set1.contains(num)) {
                answer1.add(num);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(answer0);
        result.add(answer1);

        return result;
    }
}