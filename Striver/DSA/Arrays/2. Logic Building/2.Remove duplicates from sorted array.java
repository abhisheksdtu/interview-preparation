/*
    APPROACH - USING TREESET (INEFFICIENT)
    1. Use a TreeSet to store unique elements from `nums` (TreeSet automatically removes duplicates and maintains sorted order).
    2. Iterate through `nums` and add each element to the TreeSet.
    3. Copy the unique elements back into `nums` (only the first `set.size()` positions).
    4. Return the size of the unique elements.

    TIME COMPLEXITY
    - O(N log N), since inserting each element into the TreeSet takes O(log N), and we do this for N elements.

    SPACE COMPLEXITY
    - O(N), as we store unique elements in the TreeSet.

    IMPROVEMENT
    - This solution is inefficient because it uses extra space.
    - A **better approach** is using the **two-pointer technique** to remove duplicates in-place.
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        
        // Store unique elements in TreeSet
        for (int num : nums) {
            set.add(num);
        }

        // Copy unique elements back to nums
        int i = 0;
        for (int num : set) {
            nums[i] = num;
            i++;
        }

        return set.size();
    }
}

/*
    APPROACH - TWO POINTERS (IN-PLACE)
    1. Use two pointers: 
       - `i` tracks the position of unique elements.
       - `j` scans through the array.
    2. If `nums[j]` is different from `nums[i]`, move `i` forward and copy `nums[j]` to `nums[i+1]`.
    3. Return `i + 1`, which represents the number of unique elements.

    TIME COMPLEXITY
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY
    - O(1), as we modify the array in-place without extra storage.
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0; // Pointer for unique elements

        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j]; // Place the unique element at the next position
                i++;
            }
        }
        return i + 1; // Number of unique elements
    }
}