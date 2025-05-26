/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if num is higher than the picked number
            1 if num is lower than the picked number
            0 if num is equal to the picked number
*/

// Problem Statement:
// We are playing the Guess Game. The game is as follows:
// I pick a number from 1 to n. You have to guess which number I picked.
// Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
// You call a pre-defined API int guess(int num), which returns three possible results:
// -1: Your guess is higher than the number I picked (i.e. num > pick).
// 1: Your guess is lower than the number I picked (i.e. num < pick).
// 0: your guess is equal to the number I picked (i.e. num == pick).
// Return the number that I picked.

// Approach:
// This is a classic binary search problem. We are searching for a target number within a known range [1, n].
// The `guess(num)` API provides feedback that allows us to eliminate half of the search space with each guess,
// which is the core principle of binary search.

// Steps:
// 1. Initialize `low = 1` and `high = n` to define the initial search range.
// 2. While `low <= high`:
//    a. Calculate `mid = low + (high - low) / 2`. This way of calculating `mid` prevents potential integer overflow that `(low + high) / 2` might cause if `low + high` exceeds `Integer.MAX_VALUE`.
//    b. Call the `guess(mid)` API.
//    c. Based on the API's return value:
//       - If `guess(mid) == 0`: We found the number. Return `mid`.
//       - If `guess(mid) == -1`: Our guess `mid` is higher than the picked number. This means the picked number must be in the lower half. Adjust the `high` pointer: `high = mid - 1`.
//       - If `guess(mid) == 1`: Our guess `mid` is lower than the picked number. This means the picked number must be in the upper half. Adjust the `low` pointer: `low = mid + 1`.
// 3. The loop will eventually terminate when `low` becomes greater than `high`, but this should not happen if a number is guaranteed to be picked within the range. The number will always be found and returned inside the loop.

// Time Complexity:
// In each step of the binary search, the search space is halved.
// The initial search space is `n`.
// The number of steps required to reduce the search space to 1 is `log2(n)`.
// Therefore, the time complexity is O(log n).

// Space Complexity:
// The algorithm uses a constant amount of extra space for variables like `low`, `high`, and `mid`.
// Thus, the space complexity is O(1).


public class Guessnumberhigherorlower extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int result = guess(mid);

            if (result == 0) {
                return mid;
            } else if (result == -1) {
                high = mid - 1; 
            } else { 
                low = mid + 1; 
            }
        }
        return -1; 
    }
}