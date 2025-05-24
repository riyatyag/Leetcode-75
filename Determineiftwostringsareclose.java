// Problem Statement:
// Two strings are considered close if you can attain one from the other using the following operations:
// Operation 1: Swap any two existing characters. For example, abcde -> aecdb
// Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character. For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
// You can use the operations on either string as many times as necessary.
// Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

// Approach:
// For two strings to be "close", they must satisfy two conditions based on the allowed operations:
//
// Condition 1: They must have the same length. (Obvious: operations don't change length).
//
// Condition 2: They must contain the exact same set of unique characters.
//   - Operation 1 (swapping) does not change the set of characters present.
//   - Operation 2 (transforming occurrences) essentially renames characters. If you transform 'a's to 'b's and 'b's to 'a's, the set of characters (a, b) remains the same, just their counts are swapped. You cannot introduce a new character or remove an existing one using this operation. For example, if word1 has 'a' but word2 does not, you can't make them close.
//
// Condition 3: The *counts* of characters in both strings, when sorted, must be identical.
//   - Operation 1 (swapping) changes positions but not counts.
//   - Operation 2 (transforming occurrences) means you can effectively swap the *frequencies* of any two characters. For instance, if word1 has three 'a's and two 'b's, and word2 has three 'x's and two 'y's, you can transform 'a' to 'x' and 'b' to 'y' (or vice-versa) such that their frequency profiles match. This implies that if string1 has character counts {c1: f1, c2: f2, ...} and string2 has {c'1: f'1, c'2: f'2, ...}, then the *multiset* of frequencies {f1, f2, ...} must be the same as {f'1, f'2, ...}. That is, if you sort the frequencies of characters from word1 and word2, they should be identical.
//
// Steps to implement:
// 1. Check if lengths are equal. If not, return false.
// 2. Count character frequencies for both `word1` and `word2` using two frequency arrays (size 26 for lowercase English letters).
// 3. Check Condition 2: Iterate through the frequency arrays. For each character 'a' through 'z', if one string has a count of 0 for that character and the other has a count greater than 0, then they don't have the same set of characters. Return false.
// 4. Check Condition 3: Sort both frequency arrays. If the sorted arrays are not equal, return false.
// 5. If all checks pass, return true.

// Time Complexity:
// O(N), where N is the length of the strings.
// - Checking length: O(1).
// - Counting frequencies: O(N) for each string, so O(N) total.
// - Checking unique characters: O(26) = O(1) for iterating through frequency arrays.
// - Sorting frequency arrays: O(26 log 26) = O(1) as the size is constant (26).
// Overall, the dominant factor is iterating through the strings to count frequencies.

// Space Complexity:
// O(1), as two fixed-size arrays (size 26) are used to store character frequencies, independent of the input string length.

// Optimal Solution:
import java.util.Arrays;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }
}