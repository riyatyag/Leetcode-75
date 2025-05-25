// Problem Statement:
// You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
// You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
// Return the answers to all queries. If a single answer cannot be determined, return -1.0.
// Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
// Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

// Approach:
// This problem can be modeled as a graph problem. Each variable can be considered a node, and an equation Ai / Bi = value can be considered a directed edge from Ai to Bi with weight `value`, and also an edge from Bi to Ai with weight `1 / value`.
// We need to find the path product from Cj to Dj for each query. This suggests using either Breadth-First Search (BFS) or Depth-First Search (DFS).
//
// 1. Build the Graph:
//    - Use a `Map<String, Map<String, Double>>` to represent the graph. The outer map's key is the source variable, and its value is another map. The inner map's key is the destination variable, and its value is the weight (ratio).
//    - For each equation `[Ai, Bi]` and `values[i]`:
//        - Add an edge `Ai -> Bi` with weight `values[i]`.
//        - Add an edge `Bi -> Ai` with weight `1.0 / values[i]`.
//
// 2. Process Queries using DFS (or BFS):
//    - For each query `[Cj, Dj]`:
//        - Initialize `result = -1.0`.
//        - Check if both `Cj` and `Dj` exist in the graph. If not, return -1.0.
//        - If `Cj` equals `Dj`, the answer is `1.0`.
//        - Otherwise, perform a DFS (or BFS) starting from `Cj` to find `Dj`.
//        - Use a `Set<String>` to keep track of visited nodes during the DFS to prevent cycles.
//        - The DFS function will take `(current_node, target_node, current_product, visited_set)` as parameters.
//        - If `current_node` is `target_node`, return `current_product`.
//        - For each neighbor of `current_node`:
//            - If the neighbor has not been visited:
//                - Recursively call DFS with `neighbor`, `target_node`, `current_product * weight_to_neighbor`.
//                - If the recursive call returns a valid result (not -1.0), propagate it up.
//        - If no path is found from `current_node`, return -1.0.
//
// 3. Store Results:
//    - Store the results for each query in a `double[]` array.

// Time Complexity:
// Building the graph: O(E), where E is the number of equations.
// For each query:
//   DFS/BFS in the worst case visits all nodes and edges in the connected component.
//   In the worst case, the graph can have N nodes (distinct variables) and E edges.
//   So, for each query, it's O(N + E).
// Total time complexity: O(E + Q * (N + E)), where Q is the number of queries.
// Given constraints (N, E, Q <= 20-40, assuming N <= 2*E), this is efficient enough.

// Space Complexity:
// O(N + E) for storing the graph.
// O(N) for the recursion stack during DFS or queue during BFS.
// Total space complexity: O(N + E).

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String A = equations.get(i).get(0);
            String B = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(A, new HashMap<>());
            graph.putIfAbsent(B, new HashMap<>());

            graph.get(A).put(B, value);
            graph.get(B).put(A, 1.0 / value);
        }

        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String C = queries.get(i).get(0);
            String D = queries.get(i).get(1);

            if (!graph.containsKey(C) || !graph.containsKey(D)) {
                results[i] = -1.0; 
            } else if (C.equals(D)) {
                results[i] = 1.0; 
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(graph, C, D, 1.0, visited);
            }
        }

        return results;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String current, String target, double product, Set<String> visited) {
        visited.add(current);

        if (current.equals(target)) {
            return product;
        }

        if (graph.containsKey(current)) {
            for (Map.Entry<String, Double> neighborEntry : graph.get(current).entrySet()) {
                String neighbor = neighborEntry.getKey();
                double weight = neighborEntry.getValue();

                if (!visited.contains(neighbor)) {
                    double result = dfs(graph, neighbor, target, product * weight, visited);
                    if (result != -1.0) { 
                        return result;
                    }
                }
            }
        }
        
        visited.remove(current); 
        return -1.0;
    }
}