// Problem Statement:
// Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

// Approach:
// We need to calculate the number of set bits (1's) for each integer from 0 to n.
// A naive approach would be to iterate from 0 to n and for each number, count its set bits by repeatedly checking the LSB and right-shifting. This would take O(N log N) time, as counting bits for each number takes O(log i) time.

// The follow-up asks for an O(N) solution, possibly in a single pass and without built-in functions. This suggests a dynamic programming approach or leveraging bit manipulation properties.

// Let's observe the pattern of set bits:
// 0: 0 (0)
// 1: 1 (1)
// 2: 10 (1) - count(2) = count(2/2) + (2%2) = count(1) + 0 = 1
// 3: 11 (2) - count(3) = count(3/2) + (3%2) = count(1) + 1 = 2
// 4: 100 (1) - count(4) = count(4/2) + (4%2) = count(2) + 0 = 1
// 5: 101 (2) - count(5) = count(5/2) + (5%2) = count(2) + 1 = 2
// 6: 110 (2) - count(6) = count(6/2) + (6%2) = count(3) + 0 = 2
// 7: 111 (3) - count(7) = count(7/2) + (7%2) = count(3) + 1 = 3
// 8: 1000 (1) - count(8) = count(8/2) + (8%2) = count(4) + 0 = 1

// Pattern 1: `ans[i] = ans[i / 2] + (i % 2)`
// This recurrence relation is based on the idea that the number of set bits in `i` is the number of set bits in `i` right-shifted by one (`i / 2`) plus 1 if `i` is odd (its LSB is 1), or plus 0 if `i` is even (its LSB is 0).
// This is an O(N) solution.

// Pattern 2: `ans[i] = ans[i & (i - 1)] + 1` (Brian Kernighan's algorithm idea applied to DP)
// `i & (i - 1)` effectively removes the least significant set bit from `i`.
// For example:
// i = 6 (110)
// i-1 = 5 (101)
// i & (i-1) = 4 (100)
// `ans[6] = ans[4] + 1 = 1 + 1 = 2`. This is correct.
// This also gives an O(N) solution.

// Pattern 3: Using the highest power of 2
// For any number `i`, it can be seen as `i = largest_power_of_2_less_than_i + remainder`.
// For example:
// 0: 0
// 1: 1
// 2: 10, count(2) = 1 + count(0)
// 3: 11, count(3) = 1 + count(1)
// 4: 100, count(4) = 1 + count(0)
// 5: 101, count(5) = 1 + count(1)
// 6: 110, count(6) = 1 + count(2)
// 7: 111, count(7) = 1 + count(3)
// 8: 1000, count(8) = 1 + count(0)

// This pattern can be summarized as `ans[i] = ans[i - offset] + 1`, where `offset` is the largest power of 2 less than or equal to `i`.
// Example:
// `offset = 1` for `i = 1` (`ans[1] = ans[1-1] + 1 = ans[0] + 1 = 0 + 1 = 1`)
// `offset = 2` for `i = 2, 3` (`ans[2] = ans[2-2] + 1 = ans[0] + 1 = 1`, `ans[3] = ans[3-2] + 1 = ans[1] + 1 = 1 + 1 = 2`)
// `offset = 4` for `i = 4, 5, 6, 7` (`ans[4] = ans[4-4] + 1 = ans[0] + 1 = 1`, ...)
// `offset = 8` for `i = 8, ..., 15`

// This is also an O(N) DP approach. We can maintain an `offset` variable which is always a power of 2. When `i` becomes equal to `2 * offset`, we update `offset = i`.

// Let's implement the `ans[i] = ans[i / 2] + (i % 2)` approach (Pattern 1), as it's quite intuitive and commonly used.

// Time Complexity:
// O(N) because we iterate from 1 to N, and each calculation `ans[i / 2] + (i % 2)` is an O(1) operation.

// Space Complexity:
// O(N) to store the `ans` array of size `n + 1`.

class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }

        return ans;
    }
}