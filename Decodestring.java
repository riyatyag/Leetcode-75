/**
 * Problem: 394. Decode String
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 *
 * Approach:
 * This problem involves nested structures (brackets and repetitions), which often suggests using a stack. We can use two stacks: one for numbers (repetition counts) and one for strings (decoded substrings).
 *
 * We iterate through the input string character by character.
 *
 * 1. If the character is a digit:
 * - We need to parse the full number (which can be multi-digit).
 * - Push this number onto the `countStack`.
 *
 * 2. If the character is an opening bracket '[':
 * - This signifies the start of a new `encoded_string`.
 * - Push the current `result` string onto the `stringStack`. This `result` string represents the decoded part *before* the current bracketed segment.
 * - Reset `result` to an empty string to start building the new `encoded_string`.
 *
 * 3. If the character is a closing bracket ']':
 * - This signifies the end of an `encoded_string`.
 * - Pop the `count` from `countStack`.
 * - Pop the `prevString` from `stringStack`.
 * - Append the current `result` string `count` times to `prevString`.
 * - Update `result` to this newly formed string.
 *
 * 4. If the character is a letter:
 * - Append it directly to the current `result` string.
 *
 * After iterating through the entire string, the `result` string will contain the fully decoded string.
 *
 * Example: s = "3[a2[c]]"
 * - `result` = "", `countStack` = [], `stringStack` = []
 *
 * 1. '3': `num` = 3. `countStack.push(3)`. `countStack` = [3]
 * 2. '[': `stringStack.push("")`. `stringStack` = [""]`. `result` = ""
 * 3. 'a': `result` = "a"
 * 4. '2': `num` = 2. `countStack.push(2)`. `countStack` = [3, 2]
 * 5. '[': `stringStack.push("a")`. `stringStack` = ["", "a"]`. `result` = ""
 * 6. 'c': `result` = "c"
 * 7. ']':
 * - `count` = `countStack.pop()` (2).
 * - `prevString` = `stringStack.pop()` ("a").
 * - `temp` = "c" repeated 2 times = "cc".
 * - `result` = `prevString` + `temp` = "a" + "cc" = "acc".
 * 8. ']':
 * - `count` = `countStack.pop()` (3).
 * - `prevString` = `stringStack.pop()` ("").
 * - `temp` = "acc" repeated 3 times = "accaccacc".
 * - `result` = `prevString` + `temp` = "" + "accaccacc" = "accaccacc".
 *
 * End of string. Return `result` = "accaccacc".
 *
 * Time Complexity: O(N)
 * Where N is the length of the string. Each character is processed once. String concatenations might take more time, but the total length of the decoded string is bounded (10^5), so the total work for appending characters is proportional to the final decoded string length.
 *
 * Space Complexity: O(D + MaxK)
 * Where D is the maximum depth of nesting (number of nested brackets), and MaxK is the maximum length of a string segment stored on the stack. In the worst case, the stack can hold up to D numbers and D strings. The strings stored on the stack can be up to the length of the decoded string.
 * Given constraints: `s.length <= 30`, output length `10^5`. Max nesting `D` could be `30/2 = 15`. Max `k` is 300. The dominant factor is the total length of strings stored on the stack.
 */

import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0; 

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                countStack.push(k);
                stringStack.push(currentString);
                k = 0;
                currentString = new StringBuilder();
            } else if (ch == ']') {
                int repeatTimes = countStack.pop();
                StringBuilder prevString = stringStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    prevString.append(currentString);
                }
                currentString = prevString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}