/**
 * Problem: 2095. Delete the Middle Node of a Linked List
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing.
 *
 * Approach:
 * The problem requires deleting the middle node from a singly linked list.
 * The most efficient way to find the middle node is to use the "fast and slow pointer" approach.
 *
 * 1. Initialize two pointers, `slow` and `fast`, both starting at `head`.
 * 2. Move `fast` two steps and `slow` one step until `fast` reaches the end. When `fast` reaches the end, `slow` will be at the middle node.
 * 3. To delete the middle node, we need to keep track of the node before the middle node. So, we'll use a `prev` pointer.
 * 4. Handle edge cases:
 * - If the list is empty or has only one node, there's no middle node to delete, so return `null` or `head` respectively.
 * 5. While traversing, `prev` will point to the node just before `slow`.
 * 6. Once `slow` points to the middle node, `prev.next = slow.next` to remove `slow` from the list.
 *
 * Time Complexity: O(N)
 * We traverse the linked list once to find the middle node.
 *
 * Space Complexity: O(1)
 * We use a constant amount of extra space for the `slow`, `fast`, and `prev` pointers.
 */
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null; 
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
       while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        return head;
    }
}
