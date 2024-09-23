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
    1. Create a dummy node to handle edge cases and point it to the head of the list.
    2. Use two pointers: `slow` starts at the dummy node and `fast` at the head of the list.
    3. Traverse the list with `fast`. If consecutive nodes have the same value, keep moving `fast` to skip over duplicates.
    4. After the inner loop, check if `slow.next == fast`. If true, no duplicates were found, and `slow` moves forward.
    5. If duplicates are found (`slow.next != fast`), skip the duplicates by setting `slow.next` to `fast.next`.
    6. Continue this process until the end of the list.
    7. Return the list starting from `dummy.next`.

    TIME COMPLEXITY
    - O(n), where `n` is the number of nodes in the linked list, as we traverse the entire list once.

    SPACE COMPLEXITY
    - O(1), since no extra space is used apart from a few pointers.
*/
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // use two pointers, slow - track the node before the dup nodes,
        // fast - to find the last node of dups.
        ListNode slow = dummy;
        ListNode fast = head;

        while (fast != null) {
            // while loop to find the last node of the dups.

            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }
            if (slow.next == fast) {
                // no duplicates
                slow = slow.next;
            } else {
                // duplicates found
                slow.next = fast.next;
            }
            fast = fast.next;
        }

        return dummy.next;
    }
}