/**
 * Problem: 2130. Maximum Twin Sum of a Linked List
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 * The twin sum is defined as the sum of a node and its twin.
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 *
 * Approach:
 * The problem asks us to find the maximum twin sum in a singly linked list of even length. A twin pair consists of the i-th node and the (n-1-i)-th node.
 * This structure suggests that we need to pair elements from the beginning of the list with elements from the end of the list.
 *
 * One straightforward way to do this is to store all the node values in an array or a list, and then iterate from both ends to calculate the twin sums and find the maximum.
 *
 * Time Complexity: O(N)
 * We iterate through the linked list once to store its values in an ArrayList. This takes O(N) time, where N is the number of nodes.
 * Then, we iterate through the first half of the ArrayList to calculate twin sums, which takes O(N/2) or O(N) time.
 * Therefore, the total time complexity is O(N).
 *
 * Space Complexity: O(N)
 * We use an ArrayList to store all the node values, which takes O(N) space.
 *
 * Optimal Solution:
 * We can optimize the space complexity to O(1) by using the "fast and slow pointer" approach to find the middle of the linked list, and then reversing the second half of the linked list.
 *
 * Steps for O(1) space complexity:
 * 1. Find the middle of the linked list using fast and slow pointers. When the fast pointer reaches the end, the slow pointer will be at the middle.
 * 2. Reverse the second half of the linked list starting from the middle.
 * 3. Iterate through the first half of the original list and the reversed second half simultaneously, calculating the sum of corresponding nodes and updating the maximum twin sum.
 *
 * Example: head = [5,4,2,1]
 * 1. Find middle:
 * - Slow starts at 5, Fast starts at 5.
 * - Move: Slow to 4, Fast to 2.
 * - Move: Slow to 2, Fast to null (end).
 * Middle is node 2.
 * 2. Reverse second half (from node 2): [2,1] becomes [1,2].
 * Original list now conceptually: [5,4] and reversed second half: [1,2].
 * 3. Calculate twin sums:
 * - (5 + 1) = 6
 * - (4 + 2) = 6
 * Max twin sum = 6.
 *
 * The provided solution template only allows filling the `pairSum` method, so the O(N) space approach using an ArrayList is the most direct implementation given the constraints.
 */

class Solution {
    public int pairSum(ListNode head) {
        ArrayList<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int n = values.size();
        int maxSum = 0;
        for (int i = 0; i < n / 2; i++) {
            maxSum = Math.max(maxSum, values.get(i) + values.get(n - 1 - i));
        }

        return maxSum;
    }
}
