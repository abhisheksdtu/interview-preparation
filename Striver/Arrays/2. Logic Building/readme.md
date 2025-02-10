# Java Code Revision

This document compiles various Java solutions for popular coding problems along with detailed comments on each approach. The problems covered are:

1. **Move Zeros to End**
2. **Remove Duplicates from Sorted Array**
3. **Find Missing Number**
4. **Union of Two Sorted Arrays**
5. **Intersection of Two Sorted Arrays**

---

## 1. Move Zeros to End

### 1.1 Brute Approach – Using Extra List (Inefficient)
```java
/*
    BRUTE APPROACH - USING EXTRA LIST (INEFFICIENT)
    1. Create a list to store non-zero elements from the array.
    2. Copy all non-zero elements from `nums` to the list.
    3. Overwrite `nums`:
       - Fill the first `list.size()` elements with non-zero values.
       - Fill the remaining elements with zeroes.

    TIME COMPLEXITY:
    - O(N), where N is the length of the array.
      (Traversing the array twice: one for collecting non-zero elements and another for overwriting.)

    SPACE COMPLEXITY:
    - O(N), due to the extra list storing non-zero elements.
*/
import java.util.ArrayList;
import java.util.List;

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
