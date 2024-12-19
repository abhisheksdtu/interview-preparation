/*
    APPROACH
    1. Start with a pointer curr at the head of the linked list.
    2. Traverse the list while curr.next is not null.
    3. If the current node's value is the same as the next node's value, skip the next node by adjusting the pointers (curr.next = curr.next.next).
    4. If the values are different, move curr to the next node.
    5. Continue this process until the end of the list.
    6. Return the modified list, starting from the head.

    TIME COMPLEXITY
    - O(n), where n is the number of nodes in the linked list, as we traverse the entire list once.

    SPACE COMPLEXITY
    - O(1), since no additional space is used, except for the pointer curr.
*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode curr = head;

        // Traverse the list and remove duplicates
        while (curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;  // Skip the duplicate node
            } else {
                curr = curr.next;  // Move to the next node
            }
        }

        return head;  // Return the modified list
    }
}
