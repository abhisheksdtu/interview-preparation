/*
    APPROACH - BINARY SEARCH FOR FLOOR AND CEIL
    1. If the array is empty, return [-1, -1] as there is no floor or ceil.
    2. Use binary search to find the floor (largest element <= x):
       - If nums[mid] <= x, update floor and move right.
       - Otherwise, move left.
    3. Use binary search to find the ceil (smallest element >= x):
       - If nums[mid] >= x, update ceil and move left.
       - Otherwise, move right.
    4. Return both floor and ceil values.

    TIME COMPLEXITY
    - O(log N) for each binary search, making overall complexity O(log N).

    SPACE COMPLEXITY
    - O(1), as only a few extra variables are used.
*/

class Solution {
    public int[] getFloorAndCeil(int[] nums, int x) {
        if (nums.length == 0) return new int[]{-1, -1}; // Edge case: Empty array

        return new int[]{getFloor(nums, x), getCeil(nums, x)};
    }

    // Binary search for floor: largest element <= x
    private int getFloor(int[] nums, int x) {
        int floor = -1;
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] <= x) {
                floor = nums[mid]; // Potential floor found
                lo = mid + 1; // Search right for a closer floor
            } else {
                hi = mid - 1;
            }
        }
        return floor;
    }

    // Binary search for ceil: smallest element >= x
    private int getCeil(int[] nums, int x) {
        int ceil = -1;
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] >= x) {
                ceil = nums[mid]; // Potential ceil found
                hi = mid - 1; // Search left for a closer ceil
            } else {
                lo = mid + 1;
            }
        }
        return ceil;
    }
}
