// Problem Statement:
// There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
// When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
// Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

// Approach:
// This problem can be modeled as a graph traversal problem. Each room is a node, and if room `i` contains a key for room `j`, then there is a directed edge from room `i` to room `j`. We start in room 0, and we want to determine if all nodes (rooms) are reachable from room 0.
// This is a classic connectivity problem that can be solved using either Depth-First Search (DFS) or Breadth-First Search (BFS).

// Using DFS:
// 1. Initialize:
//    - `n`: total number of rooms (`rooms.size()`).
//    - `visitedCount`: a counter for the number of rooms visited, initialized to 0.
//    - `visited`: a boolean array of size `n` to keep track of visited rooms, initialized to all false.
//
// 2. Start DFS from Room 0:
//    - Call a DFS function, `dfs(0, rooms, visited)`.
//    - The `dfs` function will increment `visitedCount` for each new room it visits.
//
// 3. DFS function:
//    - `dfs(roomNumber, rooms, visited)`:
//        - Mark `roomNumber` as visited (`visited[roomNumber] = true`).
//        - Increment `visitedCount`.
//        - For each `key` in `rooms.get(roomNumber)`:
//            - If the room corresponding to `key` (`key` itself) has not been visited (`!visited[key]`):
//                - Recursively call `dfs(key, rooms, visited)`.
//
// 4. Check Result:
//    - After the initial DFS call completes, compare `visitedCount` with `n`.
//    - If `visitedCount == n`, it means all rooms were visited, so return `true`.
//    - Otherwise, return `false`.

// Time Complexity:
// O(N + K), where N is the number of rooms and K is the total number of keys across all rooms.
// Each room (node) is visited at most once. Each key (edge) is processed at most once.

// Space Complexity:
// O(N) for the `visited` array.
// O(N) for the recursion stack space in DFS (in the worst case, a long chain of rooms).

import java.util.List;
import java.util.Stack; 
import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        visited.add(0);

        while (!stack.isEmpty()) {
            int currentRoom = stack.pop();

            for (int key : rooms.get(currentRoom)) {
                if (!visited.contains(key)) {
                    visited.add(key);
                    stack.push(key);
                }
            }
        }
        return visited.size() == n;
    }
}