/*
    BRUTE APPROACH - USING EXTRA LIST (INEFFICIENT)
    1. Create a list to store non-zero elements from the array.
    2. Copy all non-zero elements from `nums` to the list.
    3. Overwrite `nums`:
       - Fill the first `list.size()` elements with non-zero values.
       - Fill the remaining elements with zeroes.

    TIME COMPLEXITY
    - O(N), where N is the length of the array.
      - Traversing the array twice (one for collecting non-zero elements and another for overwriting).

    SPACE COMPLEXITY
    - O(N), due to the extra list storing non-zero elements.

    IMPROVEMENT
    - This solution is inefficient in terms of space.
    - A **better approach** is to move non-zero elements in-place.
*/

class Solution {
  public void moveZeroes(int[] nums) {
    List<Integer> list = new ArrayList<>();

    // Collect non-zero elements
    for (int num : nums) {
      if (num != 0) {
        list.add(num);
      }
    }

    // Overwrite nums: non-zero elements first, then zeroes
    for (int i = 0; i < nums.length; i++) {
      if (i < list.size()) {
        nums[i] = list.get(i);
      } else {
        nums[i] = 0;
      }
    }
  }
}


/*
    BETTER APPROACH - TWO POINTERS (IN-PLACE)
    1. Find the first zero in the array and store its index in `i`.
       - If no zero is found, return as the array is already valid.
    2. Use `j` to traverse the array from `i+1` onwards.
       - If `nums[j]` is non-zero, swap `nums[i]` and `nums[j]`, then increment `i`.
    3. This moves all non-zero elements forward while shifting zeroes to the end.

    TIME COMPLEXITY
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY
    - O(1), as we modify the array in-place.
*/

class Solution {
  public void moveZeroes(int[] nums) {
    int i = -1;

    // Find the first zero
    for (int j = 0; j < nums.length; j++) {
      if (nums[j] == 0) {
        i = j;
        break;
      }
    }

    // If there are no zeroes, return
    if (i == -1) {
      return;
    }

    // Move non-zero elements forward
    for (int j = i + 1; j < nums.length; j++) {
      if (nums[j] != 0) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
        i++;
      }
    }
  }
}

/*
    OPTIMAL APPROACH - IN-PLACE SWAPPING (OPTIMAL)
    1. Use a pointer `j` to track the position where the next non-zero element should be placed.
    2. Traverse `nums`:
       - If `nums[i]` is non-zero, swap it with `nums[j]` and increment `j`.
    3. This ensures all non-zero elements are moved forward, and zeroes are pushed to the end.

    TIME COMPLEXITY
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY
    - O(1), as we modify the array in-place.
*/

class Solution {
  public void moveZeroes(int[] nums) {
    int j = 0; // Pointer for non-zero elements

    // Move non-zero elements to the front
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        j++;
      }
    }
  }
}
