/*
    APPROACH
    - Keep track of the farthest index you can reach (`max`).
    - For each position, check:
        - If you can't reach this position, return false.
        - Update `max` to the farthest you can reach from here.
        - If `max` reaches or passes the last index, return true.
    - If you finish the loop, it means the end is reachable.

    TIME: O(N)  // Single pass through the array
    SPACE: O(1) // No extra space used
*/
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > max)
                return false;
            max = Math.max(max, i + nums[i]);
            if (max >= n - 1)
                return true;
        }
        return true;
    }
}
