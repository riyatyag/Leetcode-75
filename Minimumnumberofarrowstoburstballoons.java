// Problem Statement:
// There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
// Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
// Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

// Approach:
// This is a classic greedy problem. To minimize the number of arrows, we want each arrow to burst as many balloons as possible.
//
// The key insight is to sort the balloons. There are two natural ways to sort: by `xstart` or by `xend`.
// Let's consider sorting by `xend` (the end coordinate of the balloon).
//
// 1. Sort the `points` array based on the `xend` coordinate of each balloon. If two balloons have the same `xend`, their relative order doesn't matter.
// 2. Initialize `arrows = 0` and `lastArrowPos = negative_infinity` (or a very small number like `Long.MIN_VALUE` to handle large coordinates).
// 3. Iterate through the sorted balloons:
//    a. For each balloon `[xstart, xend]`, check if it can be burst by the `lastArrowPos`.
//       If `xstart > lastArrowPos`, it means the current balloon starts after where the last arrow was shot, so a new arrow is needed for this balloon (and potentially subsequent overlapping balloons). In this case, increment `arrows` by 1 and set `lastArrowPos = xend`. We choose `xend` as the arrow's position because it allows us to cover the current balloon and potentially as many subsequent balloons as possible that overlap with the current balloon's end point. This is the greedy choice as it maximizes the chance of future overlaps.
//    b. If `xstart <= lastArrowPos`, it means the current balloon can be burst by the arrow shot at `lastArrowPos`. We don't need a new arrow, and we don't update `lastArrowPos`.
//
// Why sort by `xend` and pick `xend` as the arrow position?
// Sorting by `xend` ensures that when we consider a balloon, we are looking for an arrow position that can burst it and any *subsequent* balloons that also end "early". By placing the arrow at the `xend` of the *current* balloon (that required a new arrow), we maximize the potential overlap with future balloons. Any balloon that starts before or at this `xend` will be burst.
//
// Example walk-through: `[[10,16],[2,8],[1,6],[7,12]]`
// Sorted by `xend`: `[[1,6],[2,8],[7,12],[10,16]]`
//
// - `arrows = 0`, `lastArrowPos = Long.MIN_VALUE`
// - Balloon `[1,6]`: `1 > Long.MIN_VALUE`. Need new arrow. `arrows = 1`. `lastArrowPos = 6`.
// - Balloon `[2,8]`: `2 <= 6`. Burst by arrow at 6. No new arrow.
// - Balloon `[7,12]`: `7 > 6`. Need new arrow. `arrows = 2`. `lastArrowPos = 12`.
// - Balloon `[10,16]`: `10 <= 12`. Burst by arrow at 12. No new arrow.
//
// Result: 2 arrows.

// Time Complexity:
// O(N log N) due to sorting the `points` array, where N is the number of balloons.
// The iteration after sorting takes O(N) time.

// Space Complexity:
// O(log N) or O(N) depending on the sorting algorithm used (for the stack space in quicksort/mergesort). If an in-place sort is used, it's O(log N) for recursion stack. If a copy is made for sorting, it's O(N).
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return 0;
                }
                return a[1] < b[1] ? -1 : 1;
            }
        });

        int arrows = 1; 
        long lastArrowPos = points[0][1]; 
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > lastArrowPos) {
                arrows++;
                lastArrowPos = points[i][1]; 
            }
        }
        return arrows;
    }
}