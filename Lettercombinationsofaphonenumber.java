// Problem Statement:
// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
// A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
// Example:
// Input: digits = "23"
// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

// Approach:
// This problem can be solved using a backtracking algorithm. We can define a mapping from digits to their corresponding letters.
// The backtracking function will recursively build combinations.
// It will take the current index of the digit being processed, the current string built so far, and the list to store all final combinations.

// Steps:
// 1. Initialize an empty list to store the results.
// 2. Handle the base case: if the input `digits` string is empty or null, return the empty list.
// 3. Create a mapping array (or HashMap) where each index corresponds to a digit (0-9) and its value is the string of letters it represents.
// 4. Implement a recursive helper function (e.g., `backtrack`) that takes:
//    - `digits`: The original input string of digits.
//    - `index`: The current digit index we are processing.
//    - `currentCombination`: A `StringBuilder` to efficiently build the current letter combination.
//    - `mapping`: The array containing digit-to-letter mappings.
//    - `result`: The list to which valid combinations will be added.
// 5. Base Case for `backtrack`: If `index` equals the length of `digits`, it means we have processed all digits and formed a complete combination. Add `currentCombination.toString()` to the `result` list and return.
// 6. Recursive Step for `backtrack`:
//    - Get the current digit character from `digits` at `index`.
//    - Convert this digit character to an integer to use as an index for the `mapping` array.
//    - Retrieve the corresponding letters string from the `mapping` array.
//    - Iterate through each character (letter) in the retrieved letters string:
//      - Append the current `letter` to `currentCombination`.
//      - Recursively call `backtrack` with `index + 1` (to move to the next digit).
//      - After the recursive call returns, remove the last appended `letter` from `currentCombination`. This is the "backtrack" step, allowing us to explore other possibilities for the current digit.

// Time Complexity:
// Let N be the length of the input `digits` string.
// Let M be the maximum number of letters a digit can map to (M is 4 for '7' and '9', and 3 for others).
// In the worst case, for each digit, we branch out M times. So, for N digits, the number of combinations can be up to M^N.
// For each combination, we perform string concatenation (or `StringBuilder` operations), which takes O(N) time.
// Therefore, the overall time complexity is O(M^N * N). Given the constraints (N <= 4), this is a small number.

// Space Complexity:
// The space complexity is primarily determined by the recursion depth and the space used to store the `currentCombination`.
// The maximum recursion depth is N (the length of `digits`).
// The `StringBuilder` `currentCombination` stores up to N characters.
// The `result` list can store up to M^N strings, each of length N.
// So, the space complexity is O(N + M^N * N), where the dominant term is O(M^N * N) for storing the results.


class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {
            "",     
            "",     
            "abc",  
            "def",  
            "ghi", 
            "jkl", 
            "mno", 
            "pqrs", 
            "tuv", 
            "wxyz" 
        };

        backtrack(digits, 0, new StringBuilder(), mapping, result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder currentCombination, String[] mapping, List<String> result) {
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        char digitChar = digits.charAt(index);
        int digitValue = digitChar - '0';
        String letters = mapping[digitValue];

        for (char letter : letters.toCharArray()) {
            currentCombination.append(letter);
            backtrack(digits, index + 1, currentCombination, mapping, result);
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}