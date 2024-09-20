/*
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */

/*
 * BRUTE -> USE HASHING
 * SAVE ALL NODES IN A SET
 * IF THE NODE EXISTS IN SET -> LINKED LIST IS CYCLIC
 * TC -> O(N)
 * SC -> O(N)
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        Set<ListNode> set = new HashSet();

        ListNode curr = head;

        while (curr != null) {
            if (set.contains(curr)) {
                return true;
            }
            set.add(curr);
            curr = curr.next;
        }
        return false;
    }
}

/*
 * OPTIMAL -> 2 POINTER APPROACH -> SLOW & FAST
 * MOVE SLOW BY 1 & FAST BY 2
 * IF CYCLE EXISTS THEY WILL MEET
 * TC -> O(N)
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = fast = head;
        // ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        
        return false;
    }
}