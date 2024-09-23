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