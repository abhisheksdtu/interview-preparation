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
    public ListNode deleteKthNode(ListNode head, int k) {
        if(head==null){
            return head;
        }

        if(k==1){
            ListNode newHead = head.next;
            head.next=null;
            return newHead;
        }

        ListNode node = head;
        ListNode prev = null;
        int c = 0;

        while(node!=null){
            if(c==k-1){
                prev.next = prev.next.next;
                break;
            }
            prev = node;
            node = node.next;
            c++;
        }
        return head;
    }
}
