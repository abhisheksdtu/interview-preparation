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
    public ListNode insertAtTail(ListNode head, int x) {
        if(head==null){
            return new ListNode(x);
        }
        ListNode node = head;
        while(node.next!=null){
            node=node.next;
        }
        ListNode  temp = new ListNode(x);
        node.next=temp;

        return head;
    }
}
