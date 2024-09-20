/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */

// TC - O(N + M)
// SC - O(N)
public class Solution {
    public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode p1 = l1;
        ListNode p2 = l2;

        while (p1 != null) {
            set.add(p1);
            p1 = p1.next;
        }

        while (p2 != null) {
            if (set.contains(p2)) {
                return p2;
            }
            p2 = p2.next;
        }

        return null;
    }
}

// TC - O(N + M)
// SC - O(N)
public class Solution {
    public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != p2) {
            p1 = p1 == null ? l2 : p1.next;
            p2 = p2 == null ? l1 : p2.next;
        }

        return p1;
    }
}