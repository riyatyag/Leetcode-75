/*
Problem Statement:
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Approach:
This problem can be solved using a sliding window approach.
First, we count the number of vowels in the initial window of length k. This will be our initial maximum.
Then, we slide the window one character at a time. When the window slides, we remove the leftmost character and add the new rightmost character.
If the character leaving the window was a vowel, we decrement the current vowel count.
If the character entering the window is a vowel, we increment the current vowel count.
We update the maximum vowel count at each step of the sliding window.

Time Complexity:
O(n), where n is the length of the string s. We iterate through the string once to initialize the first window and then slide the window across the rest of the string once.

Space Complexity:
O(1), as we are only using a few constant space variables.

Optimal Solution:
*/
class Solution {
    public int maxVowels(String s, int k) {
        int maxVowelCount = 0;
        int currentVowelCount = 0;
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                currentVowelCount++;
            }
        }
        maxVowelCount = currentVowelCount;
        for (int i = k; i < s.length(); i++) {
            char charToRemove = s.charAt(i - k);
            if (charToRemove == 'a' || charToRemove == 'e' || charToRemove == 'i' || charToRemove == 'o' || charToRemove == 'u') {
                currentVowelCount--;
            }
            char charToAdd = s.charAt(i);
            if (charToAdd == 'a' || charToAdd == 'e' || charToAdd == 'i' || charToAdd == 'o' || charToAdd == 'u') {
                currentVowelCount++;
            }

            maxVowelCount = Math.max(maxVowelCount, currentVowelCount);
        }
         return maxVowelCount;
    }
}