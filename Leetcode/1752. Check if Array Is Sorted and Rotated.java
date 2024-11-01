/*
    APPROACH
    1. Traverse the array to count the number of "decreasing points" or "breaks" where an element is greater than the next element.
       - If the array is sorted and rotated, there should be at most one such point where `nums[i] > nums[(i + 1) % n]`.
       - This point is where the rotation occurs.
    2. If more than one break is found, the array is not sorted and rotated, so return false.
    3. If there is zero or one break, the array meets the sorted and rotated condition, so return true.

    TIME COMPLEXITY
    - O(n), where n is the length of the array. We traverse the array once to count the breaks.

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space for counting the breaks.
*/

class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int count = 0;  // Count the number of breaks

        // Traverse the array and count breaks
        for (int i = 0; i < n; i++) {
            // Check if the current element is greater than the next element
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
            // If more than one break is found, return false
            if (count > 1) {
                return false;
            }
        }

        // Return true if there is at most one break
        return true;
    }
}
