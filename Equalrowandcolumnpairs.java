// Problem Statement:
// Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
// A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

// Approach:
// The most efficient way to solve this problem is to use a HashMap to store the frequency of each row.
// 1. Convert each row of the grid into a unique string representation (or a list/array) to use as keys in a HashMap.
// 2. Iterate through each row of the grid. For each row, convert it into its string representation and store its frequency in the HashMap.
// 3. Iterate through each column of the grid. For each column:
//    a. Extract the elements of the current column into a temporary list or array.
//    b. Convert this column's elements into the same string representation format used for rows.
//    c. Check if this column's string representation exists as a key in the HashMap.
//    d. If it exists, add its frequency (value) from the HashMap to a running total of equal pairs.
// 4. Return the total count of equal pairs.

// Example of string representation: For a row [3, 2, 1], convert it to "3,2,1".

// Time Complexity:
// O(N^2), where N is the dimension of the square grid (N x N).
// - Converting N rows and storing them in the HashMap: Each row has N elements. Converting a row to a string takes O(N) time. Doing this for N rows takes O(N^2) time. HashMap operations (put/get) take O(N) on average for string keys (due to hashing the string of length N). So, overall O(N^2) for rows.
// - Iterating through N columns: Each column has N elements. Extracting and converting a column to a string takes O(N) time. Checking its frequency in the HashMap takes O(N) time. Doing this for N columns takes O(N^2) time.
// Therefore, the total time complexity is O(N^2).

// Space Complexity:
// O(N^2) in the worst case.
// - The HashMap stores up to N unique row representations. Each row representation string has a length of O(N) (e.g., "val1,val2,...valN"). So, storing N such strings could take O(N * N) = O(N^2) space in the worst case if all rows are unique.

// Optimal Solution:
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rowFrequencies = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]);
                if (j < n - 1) {
                    sb.append(",");
                }
            }
            String rowString = sb.toString();
            rowFrequencies.put(rowString, rowFrequencies.getOrDefault(rowString, 0) + 1);
        }

        int count = 0;
        for (int j = 0; j < n; j++) { 
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) { 
                sb.append(grid[i][j]);
                if (i < n - 1) {
                    sb.append(",");
                }
            }
            String colString = sb.toString();
            if (rowFrequencies.containsKey(colString)) {
                count += rowFrequencies.get(colString);
            }
        }

        return count;
    }
}