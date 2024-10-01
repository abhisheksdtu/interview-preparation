/*
    APPROACH
    1. Use two dummy nodes, one for nodes with values less than x (small list) and another for nodes with values greater than or equal to x (large list).
    2. Traverse the original list and partition nodes into the two lists based on their values.
    3. At the end of the traversal, connect the small list to the large list.
    4. Return the head of the new list, which starts from the small list's dummy node's next pointer.

    TIME COMPLEXITY
    - O(n), where n is the number of nodes in the linked list. We traverse the list once.

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space for the dummy nodes and pointers.
*/

class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        // Dummy nodes for the two partitions
        ListNode smallDummy = new ListNode(-1);
        ListNode largeDummy = new ListNode(-1);

        // Pointers for the two partitions
        ListNode small = smallDummy;
        ListNode large = largeDummy;
        ListNode curr = head;

        // Traverse and partition the list
        while (curr != null) {
            if (curr.val < x) {
                small.next = curr;
                small = small.next;
            } else {
                large.next = curr;
                large = large.next;
            }
            curr = curr.next;
        }

        // Terminate the large list
        large.next = null;

        // Connect the small list with the large list
        small.next = largeDummy.next;

        // Return the new head, which is the start of the small list
        return smallDummy.next;
    }
}
