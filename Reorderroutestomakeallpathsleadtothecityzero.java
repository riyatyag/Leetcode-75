// Problem Statement:
// There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree).
// Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
// This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
// Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
// It's guaranteed that each city can reach city 0 after reorder.

// Approach:
// This problem can be solved using a graph traversal algorithm, specifically Depth-First Search (DFS) or Breadth-First Search (BFS).
// The key idea is to count how many existing roads are pointing away from city 0. These are the roads that need to be reordered.
// We can represent the connections as an adjacency list. Since we need to know the original direction of the roads, we can store both the "original" direction and a "reverse" direction.
//
// 1. Graph Representation:
//    - Use an adjacency list where each entry `adj.get(u)` stores a list of pairs `[v, direction_flag]`.
//    - `direction_flag` can be 1 if the original road was `u -> v` (meaning `u` can reach `v` directly), and 0 if the original road was `v -> u` (meaning `v` can reach `u` directly).
//    - For each `connection [ai, bi]`:
//        - Add `[bi, 1]` to `adj.get(ai)` (original direction `ai -> bi`).
//        - Add `[ai, 0]` to `adj.get(bi)` (reverse direction `bi -> ai`).
//
// 2. Traversal (DFS or BFS from city 0):
//    - We start a traversal (DFS is often intuitive for trees) from city 0.
//    - Maintain a `visited` array or set to keep track of visited cities to avoid cycles and redundant processing in a tree structure.
//    - Initialize `reorderCount` to 0.
//    - The DFS function will take `(current_city, parent_city)` or `(current_city, visited_set)` as parameters.
//
//    - DFS Logic:
//        - Mark `current_city` as visited.
//        - For each neighbor `(neighbor_city, direction_flag)` of `current_city`:
//            - If `neighbor_city` has not been visited:
//                - If `direction_flag == 1` (meaning the original road was `current_city -> neighbor_city`), it means this road is pointing away from city 0 (if `current_city` is closer to 0 than `neighbor_city`). So, we need to reorder it. Increment `reorderCount`.
//                - Recursively call DFS on `neighbor_city`.
//
// 3. Result:
//    - After the DFS completes, `reorderCount` will hold the minimum number of edges that need to be changed.

// Time Complexity:
// O(N), where N is the number of cities.
// We visit each city and each connection exactly once (in both directions for graph building).
// The DFS traversal visits each node and edge once.

// Space Complexity:
// O(N) for the adjacency list.
// O(N) for the recursion stack during DFS (in the worst case, a skewed tree).

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class Solution {
    int reorderCount = 0;
    Map<Integer, List<int[]>> adj; 
    public int minReorder(int n, int[][] connections) {
        adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] conn : connections) {
            int u = conn[0];
            int v = conn[1];
            adj.get(u).add(new int[]{v, 1});
            adj.get(v).add(new int[]{u, 0});
        }

        Set<Integer> visited = new HashSet<>();
        dfs(0, visited); 
        return reorderCount;
    }

    private void dfs(int currentCity, Set<Integer> visited) {
        visited.add(currentCity);

        for (int[] neighborInfo : adj.get(currentCity)) {
            int neighbor = neighborInfo[0];
            int directionFlag = neighborInfo[1];

            if (!visited.contains(neighbor)) {
                if (directionFlag == 1) { 
                    reorderCount++; 
                }
                dfs(neighbor, visited);
            }
        }
    }
}