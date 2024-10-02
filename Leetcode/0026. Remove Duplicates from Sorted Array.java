/*
    APPROACH
    1. Use two pointers to keep track of the unique elements in the sorted array.
    2. Pointer `j` keeps track of the position where the next unique element should be placed.
    3. Traverse the array with pointer `i` starting from the second element.
    4. If the current element is different from the element at position `j`, move `j` forward and place the current element at `j`.
    5. Return `j + 1`, which represents the length of the array with no duplicates.

    TIME COMPLEXITY
    - O(n), where n is the number of elements in the array. We traverse the array once.

    SPACE COMPLEXITY
    - O(1), as we only use a constant amount of extra space for the two pointers.
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int j = 0;  // Pointer to keep track of the position for unique elements

        // Traverse the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // If current element is not equal to the element at pointer j
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];  // Place the unique element at the next position
            }
        }

        return j + 1;  // Return the length of the array without duplicates
    }
}
