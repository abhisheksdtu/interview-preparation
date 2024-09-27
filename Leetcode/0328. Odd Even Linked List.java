/*
    APPROACH
    1. Use two pointers, odd and even, to separate the odd-indexed and even-indexed nodes in the linked list.
    2. Initialize odd to the head of the list and even to the second node.
    3. Traverse the list, moving odd-indexed nodes to one list and even-indexed nodes to another.
    4. After processing all nodes, connect the odd list to the even list by pointing the last odd node to the head of the even list.
    5. Return the head of the modified list, which starts with the odd-indexed nodes followed by the even-indexed nodes.

    TIME COMPLEXITY
    - O(n), where n is the number of nodes in the linked list, as we traverse the list once.

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space for the pointers odd, even, oddHead, and evenHead.
*/

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;

        ListNode oddHead = odd;
        ListNode evenHead = even;

        // Traverse the list, separating odd and even nodes
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }

        // Connect the odd list to the even list
        odd.next = evenHead;

        return oddHead;  // Return the modified list starting with odd-indexed nodes
    }
}
