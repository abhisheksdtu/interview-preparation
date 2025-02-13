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
    public ListNode deleteHead(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = null;

        return newHead;
    }
}