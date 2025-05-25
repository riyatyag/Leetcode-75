// Problem Statement:
// There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
// A province is a group of directly or indirectly connected cities and no other cities outside of the group.
// You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
// Return the total number of provinces.

// Approach:
// This problem is a classic application of graph traversal (DFS or BFS) or Disjoint Set Union (DSU).
// The goal is to count the number of connected components in a graph where cities are nodes and connections are edges.

// Using DFS:
// 1. Initialize:
//    - `n`: number of cities (which is `isConnected.length`).
//    - `provinces`: counter for the number of provinces, initialized to 0.
//    - `visited`: a boolean array of size `n` to keep track of visited cities, initialized to all false.
//
// 2. Iterate through cities:
//    - Loop from `i = 0` to `n - 1`.
//    - If city `i` has not been visited:
//        - Increment `provinces` count.
//        - Start a Depth-First Search (DFS) from city `i`.
//        - The DFS function will mark all cities connected to `i` (directly or indirectly) as visited.
//
// 3. DFS function:
//    - `dfs(city_index, isConnected, visited)`:
//        - Mark `city_index` as visited (`visited[city_index] = true`).
//        - Iterate through all other cities `j` from `0` to `n - 1`:
//            - If `city_index` and `j` are connected (`isConnected[city_index][j] == 1`) AND `j` has not been visited:
//                - Recursively call `dfs(j, isConnected, visited)`.
//
// 4. Return `provinces`.

// Time Complexity:
// O(N^2), where N is the number of cities.
// In the worst case, the DFS/BFS will visit every cell in the `isConnected` matrix once.
// Building an adjacency list first would be O(N^2) as well. The traversal itself is O(N+E).
// Since E can be up to N^2, the overall complexity remains O(N^2).

// Space Complexity:
// O(N) for the `visited` array and the recursion stack space (for DFS) or queue space (for BFS).
// In the worst case (a long chain of cities), the recursion stack could go N deep.

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(isConnected, i, visited);
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, int city, boolean[] visited) {
        visited[city] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[city][j] == 1 && !visited[j]) {
                dfs(isConnected, j, visited);
            }
        }
    }
}