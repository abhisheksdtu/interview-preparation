/*
    APPROACH - LINEAR SEARCH
    1. Iterate through the array and compare each element with the target.
    2. If the element matches the target, return its index.
    3. If no match is found, return -1.

    TIME COMPLEXITY
    - O(N), as we traverse the entire array in the worst case.

    SPACE COMPLEXITY
    - O(1), since no extra space is used.
*/

class Solution {
    public int search(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                return i;
            }
        }
        return -1;
    }
}

/*
    APPROACH - BINARY SEARCH IN ROTATED SORTED ARRAY
    1. Use binary search to determine the sorted half of the array.
    2. If the left half is sorted:
       - Check if the target lies within this range and adjust search boundaries accordingly.
    3. If the right half is sorted:
       - Check if the target lies within this range and adjust search boundaries accordingly.
    4. Repeat until the target is found or the search space is exhausted.

    TIME COMPLEXITY
    - O(log N), as we use binary search.

    SPACE COMPLEXITY
    - O(1), since only a few extra variables are used.
*/

class Solution {
    public int search(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == k)
                return mid;
            
            // Identify the sorted half
            if (nums[lo] <= nums[mid]) {
                // Left half is sorted
                if (k >= nums[lo] && k <= nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                // Right half is sorted
                if (k >= nums[mid] && k <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }
}
