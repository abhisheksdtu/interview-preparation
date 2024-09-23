/*
    APPROACH
    1. Create a dummy node to handle edge cases and point it to the head of the list. The dummy node is required for edge cases like:
       - The list has only one node, and that node needs to be removed.
       - The node to be removed is the head of the list (i.e., removing the first node).
    2. Use two pointers, `fast` and `slow`, both starting at the dummy node.
    3. Move the `fast` pointer `n` steps ahead of `slow`, creating a gap of `n` nodes between the two pointers.
    4. Move both `fast` and `slow` pointers together until `fast` reaches the end of the list.
    5. At this point, `slow.next` will be the node to be removed. Adjust the `next` pointer of `slow` to skip this node.
    6. Return the list starting from `dummy.next`, which excludes the dummy node.

    TIME COMPLEXITY
    - O(n), where `n` is the number of nodes in the linked list, since we traverse the list once.

    SPACE COMPLEXITY
    - O(1), as we only use a few extra pointers (`fast`, `slow`, and `dummy`).
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        // Create a dummy node
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move the `fast` pointer `n` steps ahead
        while (n-- > 0) {
            fast = fast.next;
        }

        // Move both pointers until `fast` reaches the end
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;  // Return the modified list
    }
}
