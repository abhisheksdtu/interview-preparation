/*
    APPROACH
    1. First, find the size of the linked list by traversing it.
    2. Make the linked list circular by connecting the last node to the head.
    3. Calculate the actual number of rotations needed, as rotating by the length of the list or its multiples does not change the list (k % size).
    4. Find the new tail by moving `size - k` steps from the current head.
    5. The new head is the node next to the new tail, and the circular link is broken by setting the new tail's next pointer to null.
    6. Return the new head of the list.

    TIME COMPLEXITY
    - O(n), where n is the number of nodes in the linked list. We traverse the list twice (once to find the length and once to move to the new tail).

    SPACE COMPLEXITY
    - O(1), as no extra space is used apart from a few pointers.

*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Calculate the size of the list
        ListNode curr = head;
        int size = 1;
        while (curr.next != null) {
            curr = curr.next;
            size++;
        }

        // Make the list circular by connecting the last node to the head
        curr.next = head;

        // Calculate the number of rotations needed
        k = k % size;
        k = size - k;

        // Find the new tail, which is (k - 1) steps from the current head
        while (k-- > 0) {
            curr = curr.next;
        }

        // The new head is the next node after the new tail
        head = curr.next;

        // Break the circular link to form the new list
        curr.next = null;

        return head;
    }
}
