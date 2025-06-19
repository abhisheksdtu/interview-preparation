/*
    GREEDY APPROACH
    - Track the farthest position you can reach at each step (farthest).
    - When you reach the end of the current jump (endOfJump), increment jumpCount and set the new endOfJump to farthest.
    - Continue until you can reach or pass the last index.
    - Always take the minimum number of jumps.

    TIME: O(N)   // Single pass through the array
    SPACE: O(1)  // No extra space
*/
class Solution {
    public int jump(int[] nums) {
        int jumpCount = 0;
        int endOfJump = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == endOfJump) {
                jumpCount++;
                endOfJump = farthest;
            }
        }
        return jumpCount;
    }
}
