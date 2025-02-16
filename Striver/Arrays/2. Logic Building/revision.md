# Java Code Revision
<a id="top"></a>
This document compiles various Java solutions for popular coding problems along with detailed comments on each approach. The problems covered are:

- [1. Move Zeros to End](#move-zeros-to-end)
- [2. Remove Duplicates from Sorted Array](#remove-duplicates-from-sorted-array)
- [3. Find Missing Number](#find-missing-number)
- [4. Union of Two Sorted Arrays](#union-of-two-sorted-arrays)
- [5. Intersection of Two Sorted Arrays](#intersection-of-two-sorted-arrays)

---

<a id="move-zeros-to-end"></a>
## 1. Move Zeros to End [[Back to Top](#top)]

<h3>
  Problem Statement
  <a href="https://leetcode.com/problems/move-zeroes/description/" target="_blank" rel="noopener noreferrer" style="margin-left: 8px;">
    <svg
      width="20" 
      height="20"
      xmlns="http://www.w3.org/2000/svg" 
      viewBox="0 0 24 24" 
      id="leetcode"
      style="vertical-align: middle;"
    >
      <path fill="#B3B1B0" d="M22 14.355c0-.742-.564-1.346-1.26-1.346H10.676c-.696 0-1.26.604-1.26 1.346s.563 1.346 1.26 1.346H20.74c.696.001 1.26-.603 1.26-1.346z"></path>
      <path fill="#E7A41F" d="m3.482 18.187 4.313 4.361c.973.979 2.318 1.452 3.803 1.452 1.485 0 2.83-.512 3.805-1.494l2.588-2.637c.51-.514.492-1.365-.039-1.9-.531-.535-1.375-.553-1.884-.039l-2.676 2.607c-.462.467-1.102.662-1.809.662s-1.346-.195-1.81-.662l-4.298-4.363c-.463-.467-.696-1.15-.696-1.863 0-.713.233-1.357.696-1.824l4.285-4.38c.463-.467 1.116-.645 1.822-.645s1.346.195 1.809.662l2.676 2.606c.51.515 1.354.497 1.885-.038.531-.536.549-1.387.039-1.901l-2.588-2.636a4.994 4.994 0 0 0-2.392-1.33l-.034-.007 2.447-2.503c.512-.514.494-1.366-.037-1.901-.531-.535-1.376-.552-1.887-.038l-10.018 10.1C2.509 11.458 2 12.813 2 14.311c0 1.498.509 2.896 1.482 3.876z"></path>
      <path fill="#070706" d="M8.115 22.814a2.109 2.109 0 0 1-.474-.361c-1.327-1.333-2.66-2.66-3.984-3.997-1.989-2.008-2.302-4.937-.786-7.32a6 6 0 0 1 .839-1.004L13.333.489c.625-.626 1.498-.652 2.079-.067.56.563.527 1.455-.078 2.066-.769.776-1.539 1.55-2.309 2.325-.041.122-.14.2-.225.287-.863.876-1.75 1.729-2.601 2.618-.111.116-.262.186-.372.305-1.423 1.423-2.863 2.83-4.266 4.272-1.135 1.167-1.097 2.938.068 4.127 1.308 1.336 2.639 2.65 3.961 3.974.067.067.136.132.204.198.468.303.474 1.25.183 1.671-.321.465-.74.75-1.333.728-.199-.006-.363-.086-.529-.179z"></path>
    </svg>
  </a>
</h3>


Given an integer array `nums`, move all `0`s to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this **in-place** without making a copy of the array.

### Examples

```
Example 1:
Input: nums = [0,1,0,3,12] 
Output: [1,3,12,0,0]
```
```
Example 2:
Input: nums = [0] 
Output: [0]
```

### Constraints

- `1 <= nums.length <= 10^4`
- `-2^31 <= nums[i] <= 2^31 - 1`

---

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
```

### 1.2 Better Approach – Two Pointers (In-Place)
```java
/*
    BETTER APPROACH - TWO POINTERS (IN-PLACE)
    1. Find the first zero in the array and store its index in `i`.
       - If no zero is found, return as the array is already valid.
    2. Use `j` to traverse the array from `i+1` onwards.
       - If `nums[j]` is non-zero, swap `nums[i]` and `nums[j]`, then increment `i`.
    3. This moves all non-zero elements forward while shifting zeroes to the end.

    TIME COMPLEXITY:
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY:
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
```

### 1.3 Optimal Approach – In-Place Swapping (Optimal)
```java
/*
    OPTIMAL APPROACH - IN-PLACE SWAPPING (OPTIMAL)
    1. Use a pointer `j` to track the position where the next non-zero element should be placed.
    2. Traverse `nums`:
       - If `nums[i]` is non-zero, swap it with `nums[j]` and increment `j`.
    3. This ensures all non-zero elements are moved forward, and zeroes are pushed to the end.

    TIME COMPLEXITY:
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY:
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
```

---
<a id="remove-duplicates-from-sorted-array"></a>

## 2. Remove Duplicates from Sorted Array [[Back to Top](#top)]

### 2.1 Approach – Using TreeSet (Inefficient)
```java
/*
    APPROACH - USING TREESET (INEFFICIENT)
    1. Use a TreeSet to store unique elements from `nums` (TreeSet automatically removes duplicates and maintains sorted order).
    2. Iterate through `nums` and add each element to the TreeSet.
    3. Copy the unique elements back into `nums` (only the first `set.size()` positions).
    4. Return the size of the unique elements.

    TIME COMPLEXITY:
    - O(N log N), since inserting each element into the TreeSet takes O(log N) for N elements.

    SPACE COMPLEXITY:
    - O(N), as we store unique elements in the TreeSet.
*/
import java.util.Set;
import java.util.TreeSet;

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
```

### 2.2 Approach – Two Pointers (In-Place)
```java
/*
    APPROACH - TWO POINTERS (IN-PLACE)
    1. Use two pointers: 
       - `i` tracks the position of unique elements.
       - `j` scans through the array.
    2. If `nums[j]` is different from `nums[i]`, move `i` forward and copy `nums[j]` to `nums[i+1]`.
    3. Return `i + 1`, which represents the number of unique elements.

    TIME COMPLEXITY:
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY:
    - O(1), as we modify the array in-place.
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
```

---

<a id="find-missing-number"></a>
## 3. Find Missing Number [[Back to Top](#top)]

### 3.1 Brute Approach – Brute Force (Inefficient)
```java
/*
    BRUTE APPROACH - BRUTE FORCE (INEFFICIENT)
    1. Iterate from 0 to `n` and check if the number exists in `nums`.
    2. Use a nested loop:
       - Outer loop iterates over numbers from 0 to `n`.
       - Inner loop checks if the number is present in `nums`.
    3. If a number is missing, return it.
    4. Return -1 if no number is missing (should never happen).

    TIME COMPLEXITY:
    - O(N²), since for each number from 0 to `n`, we scan the array.

    SPACE COMPLEXITY:
    - O(1), as we use only constant extra space.
*/
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        // Check for each number from 0 to n
        for (int i = 0; i <= n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (nums[j] == i) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return i;
            }
        }
        return -1;
    }
}
```

### 3.2 Better Approach – Using Boolean Array (Extra Space)
```java
/*
    BETTER APPROACH - USING BOOLEAN ARRAY (EXTRA SPACE)
    1. Create a boolean array `vis` of size `n + 1` to track numbers present in `nums`.
    2. Mark `vis[nums[i]] = true` for each number in `nums`.
    3. Iterate from `0` to `n` to find the first `false` value in `vis`, which is the missing number.
    4. Return the missing number.

    TIME COMPLEXITY:
    - O(N), since we traverse the array twice.

    SPACE COMPLEXITY:
    - O(N), due to the extra boolean array.
*/
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        boolean vis[] = new boolean[n + 1];

        // Mark the numbers present in nums
        for (int i = 0; i < n; i++) {
            vis[nums[i]] = true;
        }

        // Find the missing number
        for (int i = 0; i <= n; i++) {
            if (!vis[i]) {
                return i;
            }
        }
        return -1;
    }
}
```

### 3.3 Optimal Approach – Sum Formula (Optimal)
```java
/*
    OPTIMAL APPROACH - SUM FORMULA (OPTIMAL)
    1. Compute the expected sum using the formula: `sum = n * (n + 1) / 2`.
    2. Compute the actual sum of elements in `nums`.
    3. The missing number is `expectedSum - actualSum`.

    TIME COMPLEXITY:
    - O(N), as we compute the sum in a single pass.

    SPACE COMPLEXITY:
    - O(1), since only a few integer variables are used.
*/
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
}
```

### 3.4 Optimal Approach – XOR Method (Optimal)
```java
/*
    OPTIMAL APPROACH - XOR METHOD (OPTIMAL)
    1. XOR all numbers from `0` to `n` with elements in `nums`.
    2. Since `x ^ x = 0`, paired numbers cancel out, leaving the missing number.

    TIME COMPLEXITY:
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY:
    - O(1), as we use only one integer variable.
*/
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        int n = nums.length;

        // XOR all numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }

        // XOR with array elements
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}
```

---

<a id="union-of-two-sorted-arrays"></a>
## 4. Union of Two Sorted Arrays [[Back to Top](#top)]

### 4.1 Brute Approach – Using TreeSet
```java
/*
    BRUTE APPROACH - USING TREESET
    1. Use a TreeSet to store unique elements from both arrays.
       (TreeSet removes duplicates and keeps elements sorted.)
    2. Add all elements from array `a` and `b` to the set.
    3. Convert the set into an integer array and return it.

    TIME COMPLEXITY:
    - O(N log N + M log M), where N and M are the lengths of the arrays.

    SPACE COMPLEXITY:
    - O(N + M), for storing unique elements in the TreeSet.
*/
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int[] unionArray(int[] a, int[] b) {
        Set<Integer> set = new TreeSet<>();

        // Add all elements from a
        for (int num : a) {
            set.add(num);
        }

        // Add all elements from b
        for (int num : b) {
            set.add(num);
        }

        // Convert set to an array
        int res[] = new int[set.size()];
        int i = 0;
        for (int num : set) {
            res[i++] = num;
        }

        return res;
    }
}
```

### 4.2 Optimal Approach – Merging Two Sorted Arrays
```java
/*
    OPTIMAL APPROACH - MERGING TWO SORTED ARRAYS
    1. Use a List to store unique elements from both sorted arrays.
    2. Use two pointers (`i` and `j`) to traverse both arrays.
    3. Compare elements:
       - If `a[i] < b[j]`, add `a[i]` (if not duplicate) and move `i`.
       - If `a[i] > b[j]`, add `b[j]` (if not duplicate) and move `j`.
       - If equal, add one instance and move both pointers.
    4. Add any remaining elements from either array (if not duplicate).
    5. Convert the list to an integer array and return it.

    TIME COMPLEXITY:
    - O(N + M), where N and M are the lengths of the arrays.

    SPACE COMPLEXITY:
    - O(N + M), for storing the union in a list.
*/
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] unionArray(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                    list.add(a[i]);
                }
                i++;
            } else if (a[i] > b[j]) {
                if (list.isEmpty() || list.get(list.size() - 1) != b[j]) {
                    list.add(b[j]);
                }
                j++;
            } else {
                if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                    list.add(a[i]);
                }
                i++;
                j++;
            }
        }

        // Add remaining elements from a
        while (i < a.length) {
            if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                list.add(a[i]);
            }
            i++;
        }

        // Add remaining elements from b
        while (j < b.length) {
            if (list.isEmpty() || list.get(list.size() - 1) != b[j]) {
                list.add(b[j]);
            }
            j++;
        }

        // Convert list to array
        return list.stream().mapToInt(num -> num).toArray();
    }
}
```

---

<a id="intersection-of-two-sorted-arrays"></a>
## 5. Intersection of Two Sorted Arrays [[Back to Top](#top)]

### 5.1 Brute Approach – Two Pointers (with Boolean Array)
```java
/*
    BRUTE APPROACH - TWO POINTERS
    1. Use a boolean array `vis` to track visited elements in `b` to avoid duplicates.
    2. For each element in `a`, check against elements in `b`.
       - If `a[i] == b[j]` and `b[j]` is not visited, add it to the list and mark it as visited.
       - If `b[j] > a[i]`, break early as further elements in `b` are larger.
    3. Convert the list to an integer array and return it.

    TIME COMPLEXITY:
    - O(N * M), where N and M are the lengths of the arrays.

    SPACE COMPLEXITY:
    - O(M), due to the `vis` array and output list.
*/
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] intersectionArray(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        boolean vis[] = new boolean[b.length];

        // Brute force search with visited tracking
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j] && !vis[j]) {
                    list.add(a[i]);
                    vis[j] = true;
                    break;
                }
                if (b[j] > a[i]) {
                    break;
                }
            }
        }

        return list.stream().mapToInt(num -> num).toArray();
    }
}
```

### 5.2 Approach – Two Pointers (Optimal for Sorted Arrays)
```java
/*
    APPROACH - TWO POINTERS (OPTIMAL FOR SORTED ARRAYS)
    1. Use two pointers `i` and `j` to traverse both sorted arrays.
    2. If `a[i] < b[j]`, increment `i`.
    3. If `b[j] < a[i]`, increment `j`.
    4. If `a[i] == b[j]`, add the element (if not a duplicate) and move both pointers.
    5. Convert the list to an integer array and return it.

    TIME COMPLEXITY:
    - O(N + M), where N and M are the lengths of the arrays.

    SPACE COMPLEXITY:
    - O(min(N, M)), for storing the intersection.
*/
import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] intersectionArray(int[] a, int[] b) {
    List<Integer> list = new ArrayList<>();

    int i = 0, j = 0;
    while (i < a.length && j < b.length) {
      if (a[i] < b[j]) {
        i++;
      } else if (b[j] < a[i]) {
        j++;
      } else {
        if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
          list.add(a[i]);
        }
        i++;
        j++;
      }
    }
    return list.stream().mapToInt(num -> num).toArray();
  }
}
```

