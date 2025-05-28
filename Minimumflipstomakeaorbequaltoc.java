// Problem Statement:
// Given 3 positive numbers a, b, and c. Return the minimum flips required in some bits of a and b to make (a OR b == c) (bitwise OR operation).
// Flip operation consists of changing any single bit 1 to 0 or changing the bit 0 to 1 in their binary representation.

// Approach:
// We need to achieve (a OR b == c) by flipping the minimum number of bits in 'a' and 'b'.
// This problem can be solved by iterating through the bits of 'a', 'b', and 'c' from the least significant bit (LSB) to the most significant bit (MSB).
// Since the maximum value for a, b, c is 10^9, which is less than 2^30, we only need to check up to 30 bits (or 31 bits if including the sign bit, but typically for positive numbers, 30 bits are sufficient, or we can simply loop until all numbers become 0).

// For each corresponding bit position:
// Let bit_a be the i-th bit of 'a', bit_b be the i-th bit of 'b', and bit_c be the i-th bit of 'c'.

// Case 1: If bit_c is 0
//    We want (bit_a OR bit_b) to be 0. This means both bit_a and bit_b must be 0.
//    - If bit_a is 1, we need to flip it to 0. Increment flip_count.
//    - If bit_b is 1, we need to flip it to 0. Increment flip_count.

// Case 2: If bit_c is 1
//    We want (bit_a OR bit_b) to be 1. This means at least one of bit_a or bit_b must be 1.
//    - If both bit_a is 0 AND bit_b is 0, then we need to flip one of them to 1. We only need one flip (either bit_a to 1 or bit_b to 1). Increment flip_count by 1.

// We can extract the LSB using the bitwise AND operator with 1 (`& 1`) and then right shift all numbers by 1 (`>>= 1`) to move to the next bit. We continue this process until all numbers become 0.

// Example Walkthrough (a=2, b=6, c=5):
// Binary: a=010, b=110, c=101

// Initial flips = 0

// Bit 0 (LSB):
// bit_a = 0 (2 & 1)
// bit_b = 0 (6 & 1)
// bit_c = 1 (5 & 1)
// Case 2: bit_c is 1. Both bit_a and bit_b are 0. Need 1 flip. flips = 1.
// a = 01 (2 >> 1)
// b = 11 (6 >> 1)
// c = 10 (5 >> 1)

// Bit 1:
// bit_a = 1 (1 & 1)
// bit_b = 1 (3 & 1)
// bit_c = 0 (2 & 1)
// Case 1: bit_c is 0.
// bit_a is 1. Need 1 flip. flips = 1 + 1 = 2.
// bit_b is 1. Need 1 flip. flips = 2 + 1 = 3.
// a = 0 (1 >> 1)
// b = 1 (3 >> 1)
// c = 1 (2 >> 1)

// Bit 2:
// bit_a = 0 (0 & 1)
// bit_b = 1 (1 & 1)
// bit_c = 1 (1 & 1)
// Case 2: bit_c is 1.
// bit_a is 0, bit_b is 1. (bit_a OR bit_b) is already 1. No flips needed for this bit. flips = 3.
// a = 0 (0 >> 1)
// b = 0 (1 >> 1)
// c = 0 (1 >> 1)

// All numbers are 0. Loop terminates.
// Total flips = 3.

// Time Complexity:
// O(log(max(a, b, c))) because we iterate through the bits, which is proportional to the number of bits in the largest number.
// Since numbers are up to 10^9, this is roughly O(30) or O(31) operations, which is constant time for practical purposes.

// Space Complexity:
// O(1) as we only use a few variables to store counts and bit values.

class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        while (a > 0 || b > 0 || c > 0) {
            int bitA = a & 1; 
            int bitB = b & 1; 
            int bitC = c & 1; 

            if (bitC == 0) {
                if (bitA == 1) {
                    flips++;
                }
                if (bitB == 1) {
                    flips++;
                }
            } else { 
                if (bitA == 0 && bitB == 0) {
                    flips++;
                }
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
          return flips;
    }
}