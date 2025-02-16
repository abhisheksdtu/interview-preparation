/*
    APPROACH - ITERATIVE BINARY SEARCH
    1. Initialize two pointers: low (lo) at the start and high (hi) at the end of the array.
    2. Use a while loop to repeatedly check the middle element.
    3. If the middle element matches the target, return its index.
    4. If the middle element is less than the target, move the low pointer to mid + 1.
    5. If the middle element is greater than the target, move the high pointer to mid - 1.
    6. If the target is not found, return -1.

    TIME COMPLEXITY
    - O(log N), since we are dividing the search space in half each iteration.

    SPACE COMPLEXITY
    - O(1), as no extra space is used.
*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int low = 0, high = nums.length - 1;
        int resultIndex = nums.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                resultIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return resultIndex;
    }
}