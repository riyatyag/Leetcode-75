/**
 * Problem: 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Approach:
 * This problem can be solved using either an iterative or a recursive approach.
 *
 * Iterative Approach:
 * We maintain three pointers: `prev`, `current`, and `nextTemp`.
 * - `prev` points to the previously reversed node (initially null).
 * - `current` points to the node currently being processed (initially head).
 * - `nextTemp` temporarily stores the `next` node of `current` before `current.next` is changed.
 *
 * We iterate while `current` is not null. In each iteration:
 * 1. Store `current.next` in `nextTemp`.
 * 2. Set `current.next` to `prev` (reversing the link).
 * 3. Move `prev` to `current`.
 * 4. Move `current` to `nextTemp`.
 *
 * Finally, `prev` will point to the new head of the reversed list.
 *
 * Time Complexity (Iterative): O(N)
 * We traverse the linked list once, where N is the number of nodes. Each node is visited and processed exactly once.
 *
 * Space Complexity (Iterative): O(1)
 * We only use a few extra pointers, regardless of the list size.
 *
 * Recursive Approach:
 * The recursive approach works by reversing the sublist starting from `head.next` and then attaching `head` to the end of the reversed sublist.
 *
 * Base Case: If `head` is null or `head.next` is null, the list is already reversed, so return `head`.
 *
 * Recursive Step:
 * 1. Recursively call `reverseList` on `head.next`. Let the result be `reversedHead`. This `reversedHead` will be the new head of the entire reversed list.
 * 2. The node `head.next` (which is now part of the `reversedHead`'s tail) should point back to `head`. So, `head.next.next = head`.
 * 3. Set `head.next` to null, as `head` will become the last node in the reversed list.
 * 4. Return `reversedHead`.
 *
 * Time Complexity (Recursive): O(N)
 * Each node is visited once during the recursive calls. The depth of the recursion stack is N.
 *
 * Space Complexity (Recursive): O(N)
 * Due to the recursive call stack, the space complexity is proportional to the number of nodes in the list.
 */

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }
}

