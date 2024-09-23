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

/*
    APPROACH
    1. Create a Set to store nodes from the first linked list (l1).
    2. Traverse through l1, adding each node to the Set.
    3. Then, traverse through the second linked list (l2).
    4. For each node in l2, check if it exists in the Set (indicating an intersection).
    5. Return the node if found, otherwise return null after the traversal.

    TIME COMPLEXITY
    - O(n + m), where 'n' is the length of l1 and 'm' is the length of l2.

    SPACE COMPLEXITY
    - O(n), since we store all nodes of the first list in a Set.
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        // HashSet to store the nodes of list l1
        Set<ListNode> set = new HashSet<>();
        ListNode p1 = l1;

        // Traverse the first list and add all nodes to the set
        while (p1 != null) {
            set.add(p1);
            p1 = p1.next;
        }

        // Traverse the second list and check for intersections
        ListNode p2 = l2;
        while (p2 != null) {
            if (set.contains(p2)) {
                return p2; // Return the intersection node if found
            }
            p2 = p2.next;
        }

        return null; // No intersection found
    }
}

/*
    APPROACH
    1. Use two pointers, one starting at the head of each linked list (l1 and l2).
    2. Traverse both lists simultaneously. When a pointer reaches the end of its list, reset it to the head of the other list.
    3. Continue this process until the two pointers either meet at the intersection or both become null.
    4. If the pointers meet, return the intersection node; otherwise, return null.

    MATHEMATICAL PROOF
    - Let A be the number of nodes unique to list `l1` (before the intersection).
    - Let B be the number of nodes unique to list `l2` (before the intersection).
    - Let C be the number of nodes after the intersection (common to both lists).
    - Both `p1` and `p2` will traverse exactly A + B + C nodes in total.
    
    Here's why:
    1. `p1` initially traverses A + C nodes (entire `l1`).
    2. `p1` then switches to `l2`, traversing B + C nodes.
    3. Similarly, `p2` initially traverses B + C nodes (entire `l2`).
    4. `p2` then switches to `l1`, traversing A + C nodes.
    5. Both pointers now traverse A + B + C nodes, which guarantees they meet at the intersection or reach null simultaneously if no intersection exists.

    TIME COMPLEXITY
    - O(n + m), where 'n' is the length of list l1 and 'm' is the length of list l2.

    SPACE COMPLEXITY
    - O(1), since we only use two pointers.
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;

        // Traverse both lists
        while (p1 != p2) {
            p1 = p1 == null ? l2 : p1.next;
            p2 = p2 == null ? l1 : p2.next;
        }

        return p1;  // Return the intersection node
    }
}
