// Problem Statement:
// Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
// A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

// Approach:
// This problem can be solved using a two-pointer approach.
// 1. Initialize two pointers, `i` for string `s` and `j` for string `t`, both starting at index 0.
// 2. Iterate through string `t` using pointer `j`.
// 3. Inside the loop, check if `i` is still within the bounds of string `s`. If `i` reaches `s.length()`, it means we have found all characters of `s` in `t` in the correct order, so `s` is a subsequence of `t`.
// 4. If `s.charAt(i)` is equal to `t.charAt(j)`, it means we found a matching character for the current position in `s`. Increment `i` to look for the next character in `s`.
// 5. Always increment `j` to move to the next character in `t`.
// 6. After the loop finishes, if `i` is equal to `s.length()`, it means all characters of `s` were found in `t` in the correct relative order. Otherwise, `s` is not a subsequence of `t`.

// Time Complexity:
// O(M), where M is the length of string `t`.
// In the worst case, we iterate through the entire string `t` once. The pointer `i` for string `s` only moves forward, and `j` for `t` always moves forward.

// Space Complexity:
// O(1), as only a few variables (pointers) are used, which do not depend on the input string lengths.

// Optimal Solution:
class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0; 
        int j = 0; 
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
}