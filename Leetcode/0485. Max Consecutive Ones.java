/*
    APPROACH
    - Traverse the array, count the number of consecutive 1's.
    - Reset the count to 0 whenever a 0 is found.
    - Keep track of the maximum consecutive count found.

    TIME: O(N)    // Single pass through the array
    SPACE: O(1)   // Only two variables used
*/
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int currentCount = 0;
        int maxCount = 0;
        for (int num : nums) {
            if (num == 1) {
                currentCount++;
            } else {
                currentCount = 0;
            }
            maxCount = Math.max(currentCount, maxCount);
        }
        return maxCount;
    }
}
