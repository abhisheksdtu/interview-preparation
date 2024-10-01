/*
    APPROACH
    1. Find the middle of the linked list using the slow and fast pointer technique.
    2. Split the list into two halves. The first half remains unchanged, and the second half is reversed.
    3. Merge the two halves by alternating nodes from each half.
    4. The list is reordered such that the nodes from the first half and the reversed second half are arranged in an alternating pattern.

    TIME COMPLEXITY
    - O(n), where n is the number of nodes in the linked list. We traverse the list multiple times (to find the middle, reverse, and merge).

    SPACE COMPLEXITY
    - O(1), as no extra space is used apart from a few pointers.
*/
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = middle(head);
        ListNode newHead = mid.next;
        mid.next = null;
        newHead = reverse(newHead);

        ListNode p1 = head;
        ListNode p2 = newHead;

        while (p2 != null) {
            ListNode n1 = p1.next;
            ListNode n2 = p2.next;

            p1.next = p2;
            p2.next = n1;

            p1 = n1;
            p2 = n2;
        }
    }

    public ListNode middle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

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