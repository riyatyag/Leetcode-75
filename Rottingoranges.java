// Problem Statement:
// You are given an m x n grid where each cell can have one of three values:
// 0 representing an empty cell,
// 1 representing a fresh orange, or
// 2 representing a rotten orange.
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
// Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

// Approach:
// This problem can be solved using a Breadth-First Search (BFS) approach.
// We can treat the initial rotten oranges as the starting points of a BFS.
// In each step (minute), the rot spreads to adjacent fresh oranges.
// We can use a queue to store the coordinates of rotten oranges.
//
// 1. Initialize:
//    - Count the total number of fresh oranges.
//    - Create a queue and add the coordinates of all initially rotten oranges to it.
//    - Initialize a variable `minutes` to 0.
//
// 2. BFS Traversal:
//    - While the queue is not empty and there are still fresh oranges:
//        - Get the current size of the queue. This represents the number of oranges that rot in the current minute.
//        - Iterate `size` times:
//            - Dequeue a rotten orange's coordinates (row, col).
//            - For each of its 4-directional neighbors:
//                - If the neighbor is within the grid boundaries and is a fresh orange (value 1):
//                    - Change its value to 2 (rotten).
//                    - Decrement the `freshCount`.
//                    - Enqueue its coordinates.
//        - If any new oranges rotted in this minute (i.e., the queue was not empty after processing current level), increment `minutes`.
//
// 3. Result:
//    - After the BFS completes, if `freshCount` is 0, it means all fresh oranges have rotted, so return `minutes`.
//    - Otherwise, if `freshCount` is greater than 0, it means some fresh oranges could not be reached by the rot, so return -1.

// Time Complexity:
// O(m*n), where m is the number of rows and n is the number of columns in the grid.
// Each cell is visited and processed at most a constant number of times (when it becomes rotten and its neighbors are checked).
// In the worst case, every cell could be a fresh orange that eventually rots.

// Space Complexity:
// O(m*n), in the worst case, all oranges could be fresh and then become rotten, requiring storing their coordinates in the queue.

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; 
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                        rottedThisMinute = true;
                    }
                }
            }
            if (rottedThisMinute) {
                minutes++;
            }
        }

        return freshCount == 0 ? minutes : -1;
    }
}