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
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;

        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;
            if (nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }
}

/*
    APPROACH - RECURSIVE BINARY SEARCH
    1. Base Case: If low pointer exceeds high, return -1 (not found).
    2. Calculate the middle index.
    3. If the middle element is the target, return its index.
    4. If the middle element is greater than the target, search in the left half.
    5. Otherwise, search in the right half.

    TIME COMPLEXITY
    - O(log N), since we are dividing the search space in half each recursion.

    SPACE COMPLEXITY
    - O(log N), due to recursive function calls.
*/

class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
        if (low > high) return -1;

        int mid = low + (high - low) / 2;

        if (nums[mid] == target) return mid;
        if (nums[mid] > target) return binarySearch(nums, low, mid - 1, target);
        return binarySearch(nums, mid + 1, high, target);
    }
}
