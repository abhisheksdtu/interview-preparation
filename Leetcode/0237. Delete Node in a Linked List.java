/*
    APPROACH
    1. Given access only to the node to be deleted (not the head of the list), we can't delete the node directly in the traditional way.
    2. Instead, copy the value of the next node into the current node.
    3. Then, update the `next` pointer of the current node to skip over the next node, effectively deleting it.

    TIME COMPLEXITY
    - O(1), since we are only performing a few operations (copying values and updating pointers).

    SPACE COMPLEXITY
    - O(1), as no additional space is required.
*/

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;  // Copy the value from the next node
        node.next = node.next.next;  // Skip the next node by updating the pointer
    }
}
