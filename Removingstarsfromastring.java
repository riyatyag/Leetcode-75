/**
 * Problem: 2390. Removing Stars From a String
 * You are given a string s, which contains stars *.
 * In one operation, you can:
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 *
 * Approach:
 * This problem involves removing characters based on their position relative to a '*' character, specifically the "closest non-star character to its left". This "last-in, first-out" (LIFO) nature of operations (the most recently added character that hasn't been removed is the one to be removed) is a classic indicator for using a stack.
 *
 * We can iterate through the input string character by character.
 *
 * 1. Initialize an empty stack (e.g., `java.util.Stack<Character>` or `java.util.ArrayDeque<Character>` for better performance as a stack).
 *
 * 2. For each character `c` in the input string `s`:
 * a. If `c` is a non-star character (i.e., a letter), push it onto the stack.
 * b. If `c` is a star ('*'):
 * - This means we need to remove the closest non-star character to its left.
 * - Since the stack stores characters in the order they appear from left to right, the "closest non-star character to its left" is simply the character at the top of the stack.
 * - So, pop the top character from the stack.
 *
 * 3. After iterating through the entire string, the characters remaining in the stack, when read from bottom to top, form the resulting string.
 * - To get the final string, we can pop all elements from the stack and prepend them to a `StringBuilder`, or push them into an `ArrayList` and then build the string. A more efficient way is to use a `StringBuilder` directly with the stack: build the string from the stack and then reverse it if needed, or append to a `StringBuilder` while popping and then convert to string. A simple `StringBuilder` can be used to simulate a stack by appending and then deleting the last character for a '*'.
 *
 * Let's refine step 3 to use `StringBuilder` for efficiency:
 * Instead of a `Stack<Character>`, we can use a `StringBuilder` directly to build the result.
 * - When we encounter a non-star character, append it to the `StringBuilder`.
 * - When we encounter a star, if the `StringBuilder` is not empty, delete the last character from it.
 *
 * This `StringBuilder` approach is more direct and often more performant than using `Stack<Character>` explicitly and then converting to string.
 *
 * Time Complexity: O(N)
 * We iterate through the input string once. Each character is appended to the `StringBuilder` at most once and deleted at most once. `append` and `deleteCharAt` (at the end) operations on `StringBuilder` are typically O(1) amortized time.
 *
 * Space Complexity: O(N)
 * In the worst case (e.g., no stars), the `StringBuilder` will store all N characters of the input string.
 */

class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1); 
                }
            } else {
                sb.append(c);
            }
        }
           return sb.toString();
    }
}