/*Definition for singly Linked List
class ListNode {
    int val;
    ListNode next;

    ListNode() {
        val = 0;
        next = null;
    }

    ListNode(int data1) {
        val = data1;
        next = null;
    }

    ListNode(int data1, ListNode next1) {
        val = data1;
        next = next1;
    }
}
*/

class Solution {
    public ListNode deleteTail(ListNode head) {
         if (head == null || head.next == null) {
            return null;
        }

        ListNode current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        current.next = null;
        return head;
    }
}