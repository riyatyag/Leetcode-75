/**
 * Problem: 649. Dota2 Senate
 * In the world of Dota2, there are two parties: the Radiant and the Dire.
 * The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
 * Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
 * Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
 * Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party.
 * The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
 * Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".
 *
 * Approach:
 * This problem can be modeled as a simulation using queues. Since senators vote in a round-based manner and maintain their relative order, a queue is suitable for representing the active senators.
 * Each senator's optimal strategy is to ban a senator from the opposing party. They will always ban the *next available* opposing senator in the voting order to maximize their party's advantage.
 *
 * We can use two queues, one for Radiant senators (queueR) and one for Dire senators (queueD). Instead of storing the senator's party, we store their original indices. This allows us to determine who gets to vote next based on their original position in the senate.
 *
 * 1. Initialize `queueR` and `queueD`.
 * 2. Iterate through the input `senate` string:
 * - If `senate.charAt(i)` is 'R', add `i` to `queueR`.
 * - If `senate.charAt(i)` is 'D', add `i` to `queueD`.
 *
 * 3. Simulate the rounds:
 * While both `queueR` and `queueD` are not empty:
 * - Dequeue an index from `queueR` (let's say `rIndex`).
 * - Dequeue an index from `queueD` (let's say `dIndex`).
 * - Compare `rIndex` and `dIndex`:
 * - If `rIndex < dIndex`: The Radiant senator `rIndex` gets to vote first. They ban the Dire senator `dIndex`. The Radiant senator `rIndex` remains active for the next round, but effectively at the "end" of the current set of active senators. So, `rIndex + n` (where `n` is the total number of senators, ensuring they vote in the next "cycle") is added back to `queueR`.
 * - If `dIndex < rIndex`: The Dire senator `dIndex` gets to vote first. They ban the Radiant senator `rIndex`. The Dire senator `dIndex` remains active. So, `dIndex + n` is added back to `queueD`.
 *
 * 4. After the loop, one of the queues will be empty. The party corresponding to the non-empty queue is the winner.
 * - If `queueR` is not empty, "Radiant" wins.
 * - If `queueD` is not empty, "Dire" wins.
 *
 * This approach works because by adding `n` to the index, we simulate that the senator gets to vote in the next round, maintaining their relative order among their own party, but after all currently active senators have had their turn.
 *
 * Time Complexity: O(N)
 * Each senator's index is added to a queue once. In the worst case, each senator's index is dequeued and enqueued multiple times, but the total number of enqueue/dequeue operations is proportional to N because each time a senator is added back, their effective index increases, ensuring termination. Each senator is eventually eliminated.
 *
 * Space Complexity: O(N)
 * In the worst case, both queues can store up to N/2 indices.
 */

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> queueR = new LinkedList<>();
        Queue<Integer> queueD = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                queueR.add(i);
            } else {
                queueD.add(i);
            }
        }

        while (!queueR.isEmpty() && !queueD.isEmpty()) {
            int rIndex = queueR.poll();
            int dIndex = queueD.poll();

            if (rIndex < dIndex) {
                queueR.add(rIndex + n);
            } else {
                queueD.add(dIndex + n);
            }
        }

        if (!queueR.isEmpty()) {
            return "Radiant";
        } else {
            return "Dire";
        }
    }
}