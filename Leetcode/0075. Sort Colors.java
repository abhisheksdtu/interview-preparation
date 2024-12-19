/*
    APPROACH
    1. Use the built-in sorting method to sort the array.
    2. The method rearranges the elements in ascending order.

    TIME COMPLEXITY
    - O(N * LOG N): Sorting the array takes O(N * LOG N), where N is the number of elements in the array.

    SPACE COMPLEXITY
    - O(LOG N): This is the space complexity of the sorting algorithm, which uses stack space for recursion.
*/

class Solution {
    public void sortColors(int[] nums) {
        // Sort the array in ascending order
        Arrays.sort(nums);
    }
}

/*
    APPROACH
    1. Count the occurrences of each color (0, 1, 2) using a single pass through the array.
    2. Use these counts to overwrite the input array in sorted order:
        - Fill the first zeroCount positions with 0.
        - Fill the next oneCount positions with 1.
        - Fill the remaining positions with 2.
    
    TIME COMPLEXITY
        - O(N): The array is traversed twice, once for counting and once for overwriting.
    
    SPACE COMPLEXITY
    - O(1): No additional space is used apart from variables for counting.    
 */

class Solution {
    public void sortColors(int[] nums) {
        if (nums.length == 0) {
            return; // Handle edge case for empty array
        }

        // Step 1: Count occurrences of 0, 1, and 2
        int zeroCount = 0, oneCount = 0, twoCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else if (num == 1) {
                oneCount++;
            } else {
                twoCount++;
            }
        }

        // Step 2: Overwrite array based on counts
        for (int i = 0; i < nums.length; i++) {
            if (i < zeroCount) {
                nums[i] = 0; // Fill 0s
            } else if (i < zeroCount + oneCount) {
                nums[i] = 1; // Fill 1s
            } else {
                nums[i] = 2; // Fill 2s
            }
        }
    }
}

/*
    APPROACH - DUTCH NATIONAL FLAG ALGORITHM
    1. Use three pointers:
        - lo to place 0s.
        - mid to scan elements.
        - hi to place 2s.
    2. Traverse the array with mid:
        - If nums[mid] is 0, swap it with nums[lo] and move both lo and mid forward.
        - If nums[mid] is 1, just move mid forward.
        - If nums[mid] is 2, swap it with nums[hi] and move hi backward.
    3. Repeat until mid surpasses hi.

    TIME COMPLEXITY
    - O(N): Each element is visited at most once.

    SPACE COMPLEXITY
    - O(1): Sorting is done in-place, using no extra space.
 */

class Solution {
    public void sortColors(int[] nums) {
        int lo = 0; // Pointer for placing 0s
        int mid = 0; // Pointer for scanning
        int hi = nums.length - 1; // Pointer for placing 2s

        // Traverse the array
        while (mid <= hi) {
            if (nums[mid] == 0) {
                // Swap nums[lo] and nums[mid]
                int temp = nums[lo];
                nums[lo] = nums[mid];
                nums[mid] = temp;

                lo++; // Move lo forward
                mid++; // Move mid forward
            } else if (nums[mid] == 1) {
                mid++; // Skip 1
            } else {
                // Swap nums[mid] and nums[hi]
                int temp = nums[hi];
                nums[hi] = nums[mid];
                nums[mid] = temp;

                hi--; // Move hi backward
            }
        }
    }
}
