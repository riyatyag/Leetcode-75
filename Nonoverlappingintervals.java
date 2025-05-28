// Problem Statement:
// Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
// Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.

// Approach:
// The problem asks us to find the minimum number of intervals to remove to make the remaining intervals non-overlapping.
// This is equivalent to finding the maximum number of non-overlapping intervals.
// If we find the maximum number of non-overlapping intervals, then the minimum number of removals will be total_intervals - max_non_overlapping_intervals.
// A greedy approach works well for this problem. We should sort the intervals based on their end times.
// If two intervals overlap, removing the one that ends later is always optimal, as it leaves more room for subsequent intervals.
// So, we sort the intervals by their end times. Then, we iterate through the sorted intervals.
// We keep track of the end time of the last chosen non-overlapping interval.
// For each interval, if its start time is greater than or equal to the end time of the last chosen interval, then it does not overlap, and we can include it. We then update the end time of the last chosen interval to the current interval's end time.
// If it overlaps, we skip it (effectively removing it).
// The count of skipped intervals will be the minimum number of removals.

// Time Complexity:
// O(N log N) due to sorting the intervals. N is the number of intervals.
// The iteration after sorting takes O(N) time.
// Overall: O(N log N).

// Space Complexity:
// O(1) if the sorting is done in-place (like Java's Arrays.sort for primitive arrays).
// O(log N) or O(N) depending on the sort implementation (e.g., merge sort uses O(N) space, quicksort uses O(log N) on average for recursion stack).

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        java.util.Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int end = intervals[0][1];
        int count = 1; 
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
}