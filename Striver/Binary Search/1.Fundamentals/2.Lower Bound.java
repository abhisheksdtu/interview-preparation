/*
    APPROACH - LOWER BOUND USING BINARY SEARCH
    1. Initialize two pointers: low (lo) at the start and high (hi) at the end of the array.
    2. Set the result index to the array length (default when target is greater than all elements).
    3. Use binary search to find the first position where target or greater exists.
    4. If nums[mid] is greater than or equal to target, update result index and move left.
    5. If nums[mid] is less than target, move right.
    6. Return the result index.

    TIME COMPLEXITY
    - O(log N), since we are performing binary search.

    SPACE COMPLEXITY
    - O(1), as no extra space is used.
*/

class Solution {
    public int lowerBound(int[] nums, int target) {
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
