// Problem Statement:
// Given an input string s, reverse the order of the words.
// A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
// Return a string of the words in reverse order concatenated by a single space.
// Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

// Approach:
// The most straightforward approach is to first split the string by spaces, then filter out any empty strings that result from multiple spaces, and finally reverse the order of the words.
// 1. Trim the input string to remove leading and trailing spaces.
// 2. Split the trimmed string by one or more spaces using a regular expression. This will give an array of words.
// 3. Iterate through the array of words from the end to the beginning.
// 4. Append each word to a StringBuilder, followed by a single space.
// 5. Finally, convert the StringBuilder to a string and trim any trailing space that might have been added after the last word.

// Time Complexity:
// O(N), where N is the length of the input string.
// - Trimming the string takes O(N).
// - Splitting the string takes O(N) in the worst case (e.g., if there are many spaces).
// - Iterating through the array of words and building the result takes O(M), where M is the number of words. Since M <= N, this is also O(N).
// - Building the final string from StringBuilder takes O(N).

// Space Complexity:
// O(N), where N is the length of the input string.
// - The `split()` method can create an array of strings, which in the worst case (many small words) can take O(N) space.
// - The `StringBuilder` used to construct the result can also take O(N) space to store the reversed string.

// Optimal Solution:
class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder reversedString = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversedString.append(words[i]);
            if (i > 0) {
                reversedString.append(" ");
            }
        }
        return reversedString.toString();
    }
}