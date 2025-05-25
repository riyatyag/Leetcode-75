// Problem Statement:
// You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
// You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
// In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze.
// Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze.
// The entrance does not count as an exit.
// Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

// Approach:
// This problem can be solved using Breadth-First Search (BFS).
// BFS is suitable for finding the shortest path in an unweighted graph.
// We start the BFS from the `entrance` cell.
// Each level of the BFS represents one step taken.
//
// 1. Initialize:
//    - Get the dimensions of the maze, `m` (rows) and `n` (columns).
//    - Create a queue to store the cells to visit. Each element in the queue will be an array `[row, col, steps]`.
//    - Mark the `entrance` cell as visited to avoid cycles and redundant processing. We can do this by changing its character in the maze, or by using a separate `boolean[][] visited` array. Changing the maze directly is often simpler if allowed.
//    - Add the `entrance` cell with 0 steps to the queue.
//
// 2. BFS Traversal:
//    - While the queue is not empty:
//        - Dequeue the current cell `[r, c, steps]`.
//        - Check its 4-directional neighbors:
//            - `dr` and `dc` arrays can define the relative movements: `{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}` (right, left, down, up).
//            - For each neighbor `(nr, nc)`:
//                - Check if `(nr, nc)` is within the grid boundaries.
//                - Check if `(nr, nc)` is not a wall (`maze[nr][nc] == '.'`).
//                - Check if `(nr, nc)` has not been visited (`maze[nr][nc] != '+'` or `visited[nr][nc] == false`).
//                - If all conditions are met:
//                    - If `(nr, nc)` is an exit (i.e., it's on the border and not the entrance cell):
//                        - Return `steps + 1`. This is the shortest path.
//                    - Mark `(nr, nc)` as visited (e.g., `maze[nr][nc] = '+'`).
//                    - Enqueue `[nr, nc, steps + 1]`.
//
// 3. No Exit Found:
//    - If the queue becomes empty and no exit is found, it means no path exists. Return -1.

// Time Complexity:
// O(m*n), where m is the number of rows and n is the number of columns in the maze.
// In the worst case, we might visit every cell in the maze once. Each cell is added to the queue at most once.

// Space Complexity:
// O(m*n), in the worst case, the queue could store up to m*n cells if the maze is entirely empty and traversable.

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{entrance[0], entrance[1], 0});
        maze[entrance[0]][entrance[1]] = '+';

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int steps = current[2];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && maze[nr][nc] == '.') {
                    if (nr == 0 || nr == m - 1 || nc == 0 || nc == n - 1) {
                        return steps + 1;
                    }
                    maze[nr][nc] = '+';
                    queue.offer(new int[]{nr, nc, steps + 1});
                }
            }
        }
           return -1;
    }
}