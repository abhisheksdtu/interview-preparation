class Solution {
    public int jump(int[] nums) {
        int c = 0;
        int currEnd = 0;
        int currFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            currFarthest = Math.max(currFarthest, i + nums[i]);
            if (i == currEnd) {
                c++;
                currEnd = currFarthest;
            }
        }
        return c;
    }
}