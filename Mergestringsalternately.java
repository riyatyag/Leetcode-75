    /**
     * Problem: You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.
     * Return the merged string.
     *
     * Approach:
     * 1. Initialize an empty StringBuilder to build the merged string.
     * 2. Use two pointers, `i` for `word1` and `j` for `word2`, both starting at 0.
     * 3. Iterate while both `i` is less than the length of `word1` and `j` is less than the length of `word2`. In each iteration:
     * a. Append the character at index `i` of `word1` to the StringBuilder.
     * b. Append the character at index `j` of `word2` to the StringBuilder.
     * c. Increment both `i` and `j`.
     * 4. After the loop, if there are any remaining characters in `word1` (i.e., `i` is less than the length of `word1`), append the rest of `word1` to the StringBuilder.
     * 5. Similarly, if there are any remaining characters in `word2` (i.e., `j` is less than the length of `word2`), append the rest of `word2` to the StringBuilder.
     * 6. Finally, convert the StringBuilder to a String and return it.
     *
     * Time Complexity: O(m + n), where m is the length of `word1` and n is the length of `word2`, as we iterate through both strings at most once.
     * Space Complexity: O(m + n), as the merged string can have a length up to m + n. The StringBuilder also uses space proportional to the length of the merged string.
     *
     * Optimal Solution: The described approach is optimal as it directly constructs the merged string in a single pass through the input strings.
     */

     class Solution {
      public String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder();
        int i = 0;
        int j = 0;

        while (i < word1.length() && j < word2.length()) {
            merged.append(word1.charAt(i++));
            merged.append(word2.charAt(j++));
        }

        while (i < word1.length()) {
            merged.append(word1.charAt(i++));
        }

        while (j < word2.length()) {
            merged.append(word2.charAt(j++));
        }

        return merged.toString();
    }
}