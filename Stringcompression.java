/*
Problem Statement:
443. String Compression
Given an array of characters `chars`, compress it using the following algorithm:
Begin with an empty string `s`. For each group of consecutive repeating characters in `chars`:
If the group's length is 1, append the character to `s`.
Otherwise, append the character followed by the group's length.
The compressed string `s` should not be returned separately, but instead, be stored in the input character array `chars`. Note that group lengths that are 10 or longer will be split into multiple characters in `chars`.
After you are done modifying the input array, return the new length of the array.
You must write an algorithm that uses only constant extra space.

Example 1:
Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

Example 2:
Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.

Example 3:
Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

Constraints:
1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.

Approach:
This problem can be solved using a two-pointer approach. One pointer (`read`) iterates through the original array to identify groups of repeating characters, and another pointer (`write`) writes the compressed characters back into the same array.

Algorithm:
1. Initialize `write` pointer to 0, which will track the current position to write the compressed characters.
2. Initialize `read` pointer to 0, which will iterate through the input `chars` array.
3. Loop while `read` is less than the length of `chars`:
    a. Store the character at `chars[read]` as `currentChar`.
    b. Initialize a `count` for the current character to 0.
    c. Use an inner loop to count consecutive occurrences of `currentChar`:
        i. While `read` is less than `chars.length` and `chars[read]` is equal to `currentChar`:
            - Increment `count`.
            - Increment `read`.
    d. Write `currentChar` to `chars[write]`. Increment `write`.
    e. If `count` is greater than 1:
        i. Convert the `count` to a string (e.g., "12" for count 12).
        ii. Iterate through each character (digit) of this count string:
            - Write the digit character to `chars[write]`.
            - Increment `write`.
4. After the main loop finishes, the value of `write` will be the new length of the compressed array. Return `write`.

Time Complexity:
O(N), where N is the length of the input `chars` array. The `read` pointer traverses the array exactly once. The inner operations (counting, converting count to string, and writing digits) are performed for each group. In the worst case, each character is processed a constant number of times for reading and writing. The conversion of a count to a string takes logarithmic time with respect to the count's value (number of digits), but since the total number of digits written cannot exceed N, the overall complexity remains linear.

Space Complexity:
O(1). The algorithm uses a few constant extra variables (`read`, `write`, `currentChar`, `count`). The modification is done in-place on the input array.
*/
class Solution {
    public int compress(char[] chars) {
        int write = 0;
        int read = 0;

        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;

            while (read < chars.length && chars[read] == currentChar) {
                count++;
                read++;
            }

            chars[write] = currentChar;
            write++;

            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char digit : countStr.toCharArray()) {
                    chars[write] = digit;
                    write++;
                }
            }
        }
        return write;
    }
}