    /**
     * Problem: Given a string s, reverse only all the vowels in the string and return it.
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
     *
     * Approach:
     * 1. Convert the input string to a character array for easy manipulation.
     * 2. Initialize two pointers, `left` at the beginning of the array and `right` at the end.
     * 3. Iterate while `left` is less than `right`.
     * 4. Move the `left` pointer to the right until it points to a vowel.
     * 5. Move the `right` pointer to the left until it points to a vowel.
     * 6. If `left` is still less than `right`, swap the characters at the `left` and `right` pointers and then move both pointers one step closer to the center.
     * 7. Once the loop finishes, convert the character array back to a string and return it.
     *
     * Time Complexity: O(n), where n is the length of the string, as we traverse the string at most once.
     * Space Complexity: O(n), as we convert the string to a character array. However, if we consider the space used by the input string itself as constant, the auxiliary space complexity is O(1) as we are only using a few extra variables.
     *
     * Optimal Solution: The approach described above is optimal in terms of time complexity as we need to visit each character at most once. The space complexity of O(n) due to the character array is also reasonable for this problem.
     */

    class Solution {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        String vowels = "aeiouAEIOU";

        while (left < right) {
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                left++;
            }
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                right--;
            }
            if (left < right) {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
        }
        return new String(chars);
    }
}