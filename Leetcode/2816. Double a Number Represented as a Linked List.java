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
    1. Reverse the linked list to make it easier to double the number starting from the least significant digit.
    2. Traverse the reversed list and multiply each node's value by 2, accounting for any carry.
    3. Create a new linked list to store the result, with the digits calculated from the multiplication.
    4. After traversing the entire list and handling the carry, reverse the result list to restore the original order.
    5. Return the head of the resulting list.

    TIME COMPLEXITY
    - O(n), where n is the number of nodes in the linked list, as we traverse the list a few times (once to reverse, once to calculate the result, and once to reverse again).

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space for pointers and carry, aside from the result list.
*/

class Solution {
    public ListNode doubleIt(ListNode head) {
        if (head == null) {
            return head;
        }

        // Reverse the list to process from least significant digit
        head = reverse(head);

        // Dummy node to build the result list
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        ListNode curr = head;

        int carry = 0;

        // Traverse the list and double each digit
        while (curr != null || carry != 0) {
            int val = curr != null ? curr.val : 0;
            int doubleVal = val * 2 + carry;

            carry = doubleVal / 10;
            doubleVal %= 10;

            // Create a new node for the result and add it to the result list
            ListNode newNode = new ListNode(doubleVal);
            res.next = newNode;
            res = res.next;

            curr = curr != null ? curr.next : null;
        }

        // Reverse the result list to restore the original order
        return reverse(dummy.next);
    }

    // Helper function to reverse a linked list
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode previousNode = null;
        ListNode curr = head;

        // Reversing the list
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = previousNode;
            previousNode = curr;
            curr = nextNode;
        }

        return previousNode;
    }
}
