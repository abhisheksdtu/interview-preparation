/*
    BRUTE APPROACH - BRUTE FORCE
    1. Traverse each element in the array.
    2. Check if it is greater than or equal to all elements to its right.
    3. If true, add it to the result list.
    4. Return the list of leaders.

    TIME COMPLEXITY
    - O(N²), since for each element, we compare it with all elements to its right.

    SPACE COMPLEXITY
    - O(N), for storing the result list.

    IMPROVEMENT
    - This approach is inefficient for large arrays.
    - A **better approach** is using a **reverse traversal** to find leaders in O(N) time.
*/

class Solution {
    public ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] >= nums[i]) {
                    flag = false;
                    break; // Break early to optimize
                }
            }

            if (flag) {
                res.add(nums[i]);
            }
        }

        return res;
    }
}

/*
    OPTIMAL APPROACH - REVERSE TRAVERSAL
    1. Start from the rightmost element (it is always a leader).
    2. Keep track of the maximum element encountered (`max`).
    3. If the current element is greater than `max`, add it to the result list and update `max`.
    4. Reverse the result list before returning to maintain order.

    TIME COMPLEXITY
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY
    - O(N), for storing the result list.
*/

class Solution {
    public ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        // Traverse from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > max) {
                res.add(nums[i]);
                max = nums[i];
            }
        }

        // Reverse result list to maintain original order
        Collections.reverse(res);
        return res;
    }
}
