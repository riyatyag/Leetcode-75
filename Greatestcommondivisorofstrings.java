    /**
     * Problem: For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
     * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
     *
     * Approach:
     * 1. Check if a common divisor exists. If a string `x` divides both `str1` and `str2`, then `str1 + str2` must be equal to `str2 + str1`. If they are not equal, no common divisor exists, and we can return an empty string.
     * 2. If `str1 + str2` equals `str2 + str1`, a common divisor exists. The length of the greatest common divisor (GCD) of the strings must be the greatest common divisor of the lengths of `str1` and `str2`.
     * 3. Calculate the GCD of the lengths of `str1` and `str2` using the Euclidean algorithm.
     * 4. The greatest common divisor of the strings will be the prefix of `str1` (or `str2`) with the length equal to the GCD of their lengths.
     * 5. Extract this prefix and return it.
     *
     * Time Complexity: O(m + n), where m is the length of `str1` and n is the length of `str2`. The string concatenation takes O(m + n), and calculating the GCD using the Euclidean algorithm takes logarithmic time with respect to the lengths, which is dominated by the string concatenation.
     * Space Complexity: O(m + n) in the worst case due to the creation of the concatenated strings.
     *
     * Optimal Solution: The described approach is efficient. The initial check for `str1 + str2 == str2 + str1` is crucial. The use of the GCD of lengths to determine the length of the GCD string is also optimal.
     */

     class Solution {
       public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int len1 = str1.length();
        int len2 = str2.length();

        int gcdLen = gcd(len1, len2);

        return str1.substring(0, gcdLen);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}