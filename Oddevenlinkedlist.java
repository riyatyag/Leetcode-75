/**
 * Problem: 328. Odd Even Linked List
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Approach:
 * To solve this problem with O(1) extra space and O(n) time complexity, we can use two pointers, one for the odd-indexed nodes and one for the even-indexed nodes. We will essentially be creating two separate lists (odd list and even list) and then concatenating them.
 *
 * 1. Initialize `odd` pointer to `head` (first node, odd index).
 * 2. Initialize `even` pointer to `head.next` (second node, even index).
 * 3. Store the head of the even list in `evenHead` so we can append it later.
 * 4. Iterate through the list as long as `even` and `even.next` are not null.
 * - Connect the current `odd` node to the next odd node: `odd.next = even.next`.
 * - Move `odd` pointer to the next odd node: `odd = odd.next`.
 * - Connect the current `even` node to the next even node: `even.next = odd.next`. (This handles cases where odd.next is null, correctly setting even.next to null or the next even node).
 * - Move `even` pointer to the next even node: `even = even.next`.
 * 5. After the loop, the `odd` pointer will be at the last odd-indexed node. Connect its `next` to `evenHead`.
 * 6. Return the original `head` (which is the head of the odd list).
 *
 * This approach works by carefully re-linking the nodes without creating new nodes or using additional data structures that would consume O(N) space.
 *
 * Time Complexity: O(N)
 * We iterate through the linked list once, processing each node a constant number of times. N is the number of nodes in the linked list.
 *
 * Space Complexity: O(1)
 * We only use a few extra pointers (`odd`, `even`, `evenHead`), which consume constant space regardless of the input list size.
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even; 
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead; 
        return head;
    }
}
