/**
 * Problem: 735. Asteroid Collision
 * We are given an array asteroids of integers representing asteroids in a row. The indices of the asteroid in the array represent their relative position in space.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Approach:
 * This problem involves simulating collisions between asteroids, where only right-moving asteroids colliding with left-moving asteroids will result in interaction. The last right-moving asteroid in the stack will be the first to encounter a new left-moving asteroid. This LIFO (Last-In, First-Out) behavior is a strong indicator for using a stack.
 *
 * We iterate through the given `asteroids` array. For each asteroid:
 *
 * 1. If the stack is empty, or the current asteroid is moving right (positive value), or the top of the stack is moving left (negative value), or the current asteroid is moving left and the top of the stack is also moving left (no collision possible), then simply push the current asteroid onto the stack.
 * - This covers cases like: `[] + [5] -> [5]`, `[5] + [10] -> [5, 10]`, `[-5] + [-10] -> [-5, -10]`, `[5] + [-10]` where 5 is exploded by -10.
 *
 * 2. If the current asteroid is moving left (negative value) and the top of the stack is moving right (positive value), a collision is imminent. We enter a collision resolution loop:
 * - While the stack is not empty and the top of the stack is positive (moving right) and the current asteroid is negative (moving left):
 * - Get the size of the top asteroid on the stack (`stackTopSize = stack.peek()`).
 * - Get the size of the current asteroid (`currentAsteroidSize = Math.abs(asteroid)`).
 *
 * - If `stackTopSize < currentAsteroidSize`: The asteroid on the stack explodes. Pop it from the stack. Continue the loop to check for further collisions with the current left-moving asteroid.
 * - If `stackTopSize == currentAsteroidSize`: Both asteroids explode. Pop the asteroid from the stack. The current asteroid also explodes, so we set a flag (or break the inner loop) to indicate it's destroyed and should not be pushed onto the stack. Break the loop as the current asteroid is also gone.
 * - If `stackTopSize > currentAsteroidSize`: The current asteroid explodes. No change to the stack. Break the loop as the current asteroid is destroyed.
 *
 * 3. After the collision resolution loop, if the current asteroid was not destroyed (i.e., it survived all collisions or didn't collide), push it onto the stack.
 *
 * After iterating through all asteroids, the elements remaining in the stack represent the final state of the asteroids. Convert the stack to an array.
 *
 * Time Complexity: O(N)
 * Each asteroid is pushed onto the stack at most once and popped from the stack at most once. Therefore, the total number of operations is proportional to N, where N is the number of asteroids.
 *
 * Space Complexity: O(N)
 * In the worst case (e.g., all asteroids moving right), the stack will store all N asteroids.
 */

import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
         for (int asteroid : asteroids) {
            boolean currentAsteroidDestroyed = false; 
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                int stackTop = stack.peek(); 

                if (stackTop < Math.abs(asteroid)) {
                    stack.pop();
                } else if (stackTop == Math.abs(asteroid)) {
                    stack.pop();
                    currentAsteroidDestroyed = true; 
                    break;
                } else {
                    currentAsteroidDestroyed = true; 
                    break;
                }
            }
            if (!currentAsteroidDestroyed) {
                stack.push(asteroid);
            }
        }
        int[] remainingAsteroids = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            remainingAsteroids[i] = stack.pop();
        }
        return remainingAsteroids;
    }
}