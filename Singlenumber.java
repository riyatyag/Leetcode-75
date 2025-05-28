// Problem Statement:
// Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
// You must implement a solution with a linear runtime complexity and use only constant extra space.

// Approach:
// The problem requires us to find a single number that appears only once, while all other numbers appear exactly twice. We also have constraints on time (linear) and space (constant extra space).
// The XOR bitwise operation is perfect for this problem due to its properties:
// 1. XORing a number with 0 gives the number itself: x ^ 0 = x
// 2. XORing a number with itself gives 0: x ^ x = 0
// 3. XOR is associative and commutative: a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c

// If we XOR all the numbers in the array, the numbers that appear twice will cancel each other out (x ^ x = 0), and the single number that appears only once will remain.

// Example: nums = [2, 2, 1]
// Initial result = 0
// result = 0 ^ 2 = 2
// result = 2 ^ 2 = 0
// result = 0 ^ 1 = 1
// The final result is 1, which is the single number.

// Example: nums = [4, 1, 2, 1, 2]
// Initial result = 0
// result = 0 ^ 4 = 4
// result = 4 ^ 1 = 5
// result = 5 ^ 2 = 7
// result = 7 ^ 1 = 6
// result = 6 ^ 2 = 4
// The final result is 4, which is the single number.

// Time Complexity:
// O(N), where N is the number of elements in the array. We iterate through the array once to perform XOR operations. This satisfies the linear runtime complexity requirement.

// Space Complexity:
// O(1), as we only use a single variable to store the XOR result. This satisfies the constant extra space requirement.

class Solution {
    public int singleNumber(int[] nums) {
        int single = 0; 
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}