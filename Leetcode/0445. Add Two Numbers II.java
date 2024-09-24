/*
    APPROACH
    1. Reverse both linked lists (l1 and l2) to align the least significant digits first.
    2. Use a dummy node to store the result and traverse both reversed lists (l1 and l2) using two pointers (p1 and p2).
    3. For each pair of nodes, sum their values along with any carry from the previous sum.
    4. Calculate the new sum and update the carry. Create a new node with the resulting value and attach it to the result list.
    5. Continue until both lists are fully traversed and there is no carry.
    6. Reverse the result list to restore the proper order of digits.
    7. Reverse l1 and l2 again to restore their original order if needed.
    8. Return the reversed result list as the final answer.

    EDGE CASES
    - One or both lists are empty: return the non-empty list.
    - Carry exists after processing both lists, add a new node to the result.

    TIME COMPLEXITY
    - O(n + m), where n and m are the lengths of l1 and l2, respectively. Each list is traversed twice (once to reverse and once to sum the values).

    SPACE COMPLEXITY
    - O(max(n, m)), for storing the result linked list.
*/
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        // Reverse both linked lists
        l1 = reverse(l1);
        l2 = reverse(l2);

        // Create a dummy node for result storage
        ListNode dummy = new ListNode(-1);
        int carry = 0;

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = dummy;

        // Traverse both lists and add values
        while (p1 != null || p2 != null || carry != 0) {
            int v1 = p1 != null ? p1.val : 0;
            int v2 = p2 != null ? p2.val : 0;

            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sum %= 10;

            // Add the sum as a new node to the result
            ListNode node = new ListNode(sum);
            p3.next = node;

            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
            p3 = p3.next;
        }

        // Reverse the result list
        ListNode head = dummy.next;
        dummy.next = null;
        head = reverse(head);

        // Reverse l1 and l2 back to their original form (optional)
        l1 = reverse(l1);
        l2 = reverse(l2);

        return head;
    }

    // Helper function to reverse a linked list
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
