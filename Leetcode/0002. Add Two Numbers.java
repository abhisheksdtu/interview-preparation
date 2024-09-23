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
    1. Create a dummy node to simplify result list handling.
    2. Initialize pointers `p1` for `l1`, `p2` for `l2`, and `p3` for the dummy list.
    3. Initialize a variable `carry` to handle any values greater than 9.
    4. Traverse both lists using `p1` and `p2`, and calculate the sum at each step: 
       - Add the values of the current nodes from `l1` and `l2` (use 0 if one list is shorter).
       - Add the `carry` from the previous step.
       - Update the carry and the current node value.
    5. If there is a carry left after both lists are fully traversed, add a new node for the carry value.

    TIME COMPLEXITY
    - O(max(n, m)), where `n` is the length of `l1` and `m` is the length of `l2`.
     We process both lists until the longest list is fully traversed.

    SPACE COMPLEXITY
    - O(max(n, m)), since we create a new linked list that represents the sum of the input lists. 
    The length of this list will be the same as the longer of the two input lists plus an extra node if there is a carry at the end.
*/
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode dummy = new ListNode(-1);

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = dummy;

        int carry = 0;

        while (p1 != null || p2 != null || carry != 0) {
            int v1 = p1 != null ? p1.val : 0;
            int v2 = p2 != null ? p2.val : 0;

            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sum %= 10;

            ListNode node = new ListNode(sum);
            p3.next = node;

            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
            p3 = p3.next;
        }

        ListNode head = dummy.next;
        dummy.next = null;

        return head;
    }
}