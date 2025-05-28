// Problem Statement:
// Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

// Approach:
// This problem can be effectively solved using a monotonic stack. We need to find, for each day, the number of days until a warmer temperature occurs.
// We can iterate through the temperatures array from right to left (or left to right, but right to left is often more intuitive for "next greater element" type problems).
//
// We will use a stack to store the *indices* of the days. The stack will maintain a strictly decreasing sequence of temperatures.
//
// When we process a temperature `temperatures[i]`:
// 1. While the stack is not empty and `temperatures[stack.peek()] <= temperatures[i]`, pop elements from the stack. This is because any day `j` currently on the stack with `temperatures[j] <= temperatures[i]` cannot be the "next warmer day" for `i` or any day before `i` that was already processed. The current `temperatures[i]` is a better candidate for those days.
// 2. After popping, if the stack is empty, it means there is no warmer temperature to the right of `i`, so `answer[i]` is 0.
// 3. If the stack is not empty, `stack.peek()` now holds the index of the first day to the right of `i` that has a warmer temperature. So, `answer[i] = stack.peek() - i`.
// 4. Finally, push the current index `i` onto the stack.
//
// We will initialize an `answer` array of the same size as `temperatures` with all zeros.

// Time Complexity:
// The time complexity is O(N), where N is the number of days (length of `temperatures` array).
// Each temperature is pushed onto the stack exactly once and popped from the stack at most once.
// Therefore, the total number of operations (pushes and pops) is proportional to N.

// Space Complexity:
// The space complexity is O(N) in the worst case. This occurs when the temperatures are in strictly decreasing order (e.g., [100, 90, 80, ...]), in which case all indices will be pushed onto the stack and remain there until the end.
import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>(); 
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                answer[i] = 0;
            } else {
                answer[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return answer;
    }
}