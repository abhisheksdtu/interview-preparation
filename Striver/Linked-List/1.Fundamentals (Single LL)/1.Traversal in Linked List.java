/*Definition of singly linked list:
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
    public List<Integer> LLTraversal(ListNode head) {
        List<Integer> res = new ArrayList<>();

        ListNode node = head;

        while(node!=null){
            res.add(node.val);
            node=node.next;
        }

        return res;
    }
}