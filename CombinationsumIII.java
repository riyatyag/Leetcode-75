// Problem Statement:
// Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
// Only numbers 1 through 9 are used.
// Each number is used at most once.
// Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

// Approach:
// This problem can be solved using a backtracking approach. We need to find combinations of k numbers that sum up to n, using numbers from 1 to 9.
// The backtracking function will take the following parameters:
// - k: the number of elements needed in the combination
// - n: the target sum
// - start: the starting number to consider for the current combination (to avoid duplicate combinations and ensure numbers are used at most once)
// - currentCombination: the list holding the current combination being built
// - result: the list to store all valid combinations

// Base Cases:
// 1. If the current combination has k elements and its sum is n, then we found a valid combination. Add it to the result list.
// 2. If the current combination has k elements but its sum is not n, or if the sum exceeds n, or if we have exhausted numbers from 1 to 9, then backtrack.

// Recursive Step:
// Iterate from 'start' to 9. For each number 'i':
// 1. Add 'i' to the current combination.
// 2. Recursively call the backtracking function with k, n - i (reducing the target sum), i + 1 (to consider numbers greater than i), and the updated current combination.
// 3. Remove 'i' from the current combination (backtrack) to explore other possibilities.

// Time Complexity:
// The time complexity is roughly O(C(9, k) * k), where C(9, k) is the number of combinations of 9 items taken k at a time. This is because in the worst case, we explore all possible combinations. The 'k' factor comes from adding/removing elements to/from the current combination and copying the list when a valid combination is found.
// More precisely, it's closer to O(2^9) in the upper bound due to the recursive calls exploring possibilities for each number from 1 to 9. However, the constraints on k and n prune many branches early.

// Space Complexity:
// The space complexity is O(k) for the recursion stack depth (maximum depth of k) and O(k) for storing the current combination. The space for the result list depends on the number of valid combinations, which can be significant in the worst case.


class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        backtrack(k, n, 1, currentCombination, result);
        return result;
    }

    private void backtrack(int k, int n, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (currentCombination.size() == k) {
            if (n == 0) {
                result.add(new ArrayList<>(currentCombination));
            }
            return;
        }

        if (n < 0 || start > 9) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            currentCombination.add(i);
            backtrack(k, n - i, i + 1, currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}