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
    public ListNode deleteNodeWithValueX(ListNode head, int x) {
        if(head==null){
            return head;
        }

        if(head.val==x){
            ListNode newHead = head.next;
            head.next=null;
            return newHead;
        }

        ListNode node = head;
        ListNode prev = null;

        while(node!=null){
            if(node.val==x){
                prev.next = prev.next.next;
                break;
            }
            prev = node;
            node = node.next;
        }
        return head;
    }
    
}