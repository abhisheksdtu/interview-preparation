/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/*
    APPROACH
    1. Create a dummy node for handling edge cases where the head node might need to be removed and point it to head.
    2. Initialize a pointer starting at dummy node to iterate over the list.
    3. If the next node's value matches 'val', skip the node by adjusting pointers.
    4. If the value does not match, move the pointer to the next node.
    5. Return the list starting from 'dummy.next', skipping the dummy node.

    TIME COMPLEXITY
    - O(N), as each node is visited once.

    SPACE COMPLEXITY
    - O(1), using only a dummy node and constant space.
*/
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        head = dummy.next;
        dummy.next = null;

        return head;
    }
}