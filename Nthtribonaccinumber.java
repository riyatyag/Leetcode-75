// Problem Statement:
// The Tribonacci sequence Tn is defined as follows:
// T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
// Given n, return the value of Tn.

// Approach:
// The Tribonacci sequence is a linear recurrence relation where each term is the sum of the three preceding terms.
// This can be efficiently solved using dynamic programming. Since Tn only depends on Tn-1, Tn-2, and Tn-3,
// we only need to keep track of the last three computed Tribonacci numbers to calculate the next one.

// We handle the base cases for n=0, 1, and 2 directly.
// For n >= 3, we iteratively calculate Tn by maintaining three variables representing T(i-3), T(i-2), and T(i-1).
// In each step, we calculate T(i) = T(i-1) + T(i-2) + T(i-3), then update our variables to prepare for the next iteration.

// Time Complexity:
// O(N) because we iterate from 3 up to N, performing a constant number of operations in each step.

// Space Complexity:
// O(1) because we only use a fixed number of variables (three to store previous Tribonacci numbers) regardless of the input n.

class Solution {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int t0 = 0;
        int t1 = 1; 
        int t2 = 1; 
        for (int i = 3; i <= n; i++) {
            int nextT = t0 + t1 + t2;
            t0 = t1;                   
            t1 = t2;                  
            t2 = nextT;                
        }
       return t2; 
    }
}