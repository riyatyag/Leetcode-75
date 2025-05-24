/**
 * Problem: 933. Number of Recent Calls
 * Implement the RecentCounter class:
 * - RecentCounter() Initializes the counter with zero recent requests.
 * - int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 *
 * Approach:
 * This problem asks us to maintain a count of requests within a sliding window of 3000 milliseconds. Since `t` is strictly increasing, older requests will always fall out of the window from the left, and new requests are always added to the right. A Queue (specifically a `LinkedList` acting as a queue in Java) is an ideal data structure for this scenario.
 *
 * 1. `RecentCounter()`: Initialize a `Queue<Integer>` to store the timestamps of incoming requests.
 *
 * 2. `ping(int t)`:
 * a. Add the current timestamp `t` to the queue.
 * b. While the oldest timestamp in the queue (`queue.peek()`) is less than `t - 3000`, remove it from the queue (`queue.poll()`). This process effectively removes all requests that are no longer within the 3000ms window.
 * c. After removing all outdated requests, the size of the queue represents the number of requests within the `[t - 3000, t]` range. Return `queue.size()`.
 *
 * Time Complexity:
 * - `RecentCounter()`: O(1) for initialization.
 * - `ping(int t)`: In the worst case, all existing requests might be removed from the queue if they are all outside the window. However, each timestamp is added to the queue once and removed from the queue at most once. Therefore, over a sequence of `N` ping calls, the total number of enqueue and dequeue operations will be proportional to `N`. Thus, the amortized time complexity for `ping` is O(1).
 *
 * Space Complexity:
 * - O(W), where W is the maximum number of requests that can be within the 3000ms window at any given time. In the worst case, all `N` calls could be within the window if they are very close together, making it O(N).
 */

import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {

    private Queue<Integer> requests;

    public RecentCounter() {
        requests = new LinkedList<>();
    }
     public int ping(int t) {
        requests.add(t);

        while (!requests.isEmpty() && requests.peek() < t - 3000) {
            requests.poll();
        }
        return requests.size();
    }
}
