/*
    APPROACH
    1. Use a dummy node to simplify the process of building the merged list.
    2. Compare the values of the two lists (l1 and l2) node by node.
    3. Attach the smaller node to the merged list and move the corresponding pointer.
    4. Once one list is exhausted, attach the remaining nodes of the other list to the merged list.
    5. Return the merged list starting from the dummy node's next pointer.

    TIME COMPLEXITY
    - O(n + m), where n and m are the lengths of l1 and l2. We traverse both lists once.

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space for the pointers.

*/

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        
        // Dummy node to build the result list
        ListNode dummy = new ListNode(-1);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode current = dummy;

        // Traverse both lists and merge them
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;
        }

        // Attach the remaining nodes
        if (p1 != null) {
            current.next = p1;
        }

        if (p2 != null) {
            current.next = p2;
        }

        return dummy.next;  // Return the merged list starting after the dummy node
    }
}
