# Binary Search Revision
<a id="top"></a>

- [1. Fundamentals](#fundamentals)
    - [1. Search X in sorted array](#search-x-in-sorted-array)
    - [2. Lower Bound](#lower-bound)
    - [3. Upper Bound](#upper-bound)
- [2. Logic Building](#logic-building)
    - [1. Search insert position](#search-insert-position)
    - [2. Floor and Ceil in Sorted Array](#floor-and-ceil)
    - [3. First and last occurrence](#first-and-last-occurrence)
    - [4. Search in rotated sorted array-I](#search-in-rotated-sorted-array-1)
    - [5. Search in rotated sorted array-II](#search-in-rotated-sorted-array-2)
    - [6. Find minimum in Rotated Sorted Array](#find-minimum-in-rotated-sorted-array)
    - [7. Find Out How Many Times the Array is Rotated](#find-out-how-many-times-the-array-is-rotated)
    - [8. Single Element in Sorted Array](#single-element-in-sorted-array)
- [3. On Answers](#on-answers)
    - [1. Find square root of a number](#find-square-root-of-a-number)
    - [2. Find Nth root of a number](#find-nth-root-of-a-number)
    - [3. Find the smallest divisor](#find-the-smallest-divisor)
    - [4. Koko eating bananas](#koko-eating-bananas)
    - [5. Minimum days to make M bouquets](#minimum-days-to-make-m-bouquets)
- [4. FAQs](#faqs)
    - [1. Aggressive Cows](#aggressive-cows)
    - [2. Book Allocation Problem](#book-allocation-problem)
    - [3. Find peak element](#find-peak-element)
    - [4. Median of 2 sorted arrays](#median-of-2-sorted-arrays)
    - [5. Minimise max distance to gas stations](#minimise-max-distance-to-gas-stations)
    - [6. Kth element of 2 sorted arrays](#kth-element-of-2-sorted-arrays)
    - [7. Split array - largest sum](#split-array-largest-sum)
- [5. 2D Arrays](#2d-arrays)
    - [1. Find row with maximum 1's](#find-row-with-maximum-1s)
    - [2. Search in a 2D matrix](#search-in-a-2d-matrix)
    - [3. Search in 2D matrix - II](#search-in-2d-matrix-ii)
    - [4. Find Peak Element](#find-peak-element-2)
    - [5. Matrix Median](#matrix-median)

---

<a id="fundamentals"></a>
## 1. Fundamentals [[Back to Top](#top)]
- [1. Search X in sorted array](#search-x-in-sorted-array)
- [2. Lower Bound](#lower-bound)
- [3. Upper Bound](#upper-bound)

<a id="search-x-in-sorted-array"></a>
### 1. Search X in sorted array [[Back to Top](#top)]

Given a sorted array of integers `nums` with 0-based indexing, find the index of a specified target integer. If the target is found in the array, return its index. If the target is not found, return `-1`.

#### Examples

```plaintext
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: The target integer 9 exists in nums and its index is 4
```

```plaintext
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: The target integer 2 does not exist in nums so return -1
```

```plaintext
Input: nums = [-1,0,3,5,9,12], target = -1
Output: 0
```

#### Constraints:
  - 1 <= nums.length <= 10^5  
  - -10^5 < nums[i], target < 10^5  
  - nums is sorted in ascending order.

#### 1.1 APPROACH - ITERATIVE BINARY SEARCH
```java
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
```

#### 1.2 APPROACH - RECURSIVE BINARY SEARCH
```java
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
```

<a id="lower-bound"></a>
### 2. Lower Bound [[Back to Top](#top)]

Given a sorted array of `nums` and an integer `x`, write a program to find the lower bound of `x`. The lower bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than or equal to a given key i.e. `x`.

If no such index is found, return the size of the array.

#### Examples

```plaintext
Input : nums= [1,2,2,3], x = 2
Output: 1
Explanation: Index 1 is the smallest index such that arr[1] >= x.
```

```plaintext
Input : nums= [3,5,8,15,19], x = 9
Output: 3
Explanation: Index 3 is the smallest index such that arr[3] >= x.
```

```plaintext
Input : nums= [3,5,8,15,19], x = 3
Output: 0
```

#### Constraints:
  - 1 <= nums.length <= 10^5  
  - -10^5 < nums[i], x < 10^5  
  - nums is sorted in ascending order.

#### 2.1 APPROACH - LOWER BOUND USING BINARY SEARCH
```java
/*
    APPROACH - LOWER BOUND USING BINARY SEARCH
    1. Initialize two pointers: low (lo) at the start and high (hi) at the end of the array.
    2. Set the result index to the array length (default when target is greater than all elements).
    3. Use binary search to find the first position where target or greater exists.
    4. If nums[mid] is greater than or equal to target, update result index and move left.
    5. If nums[mid] is less than target, move right.
    6. Return the result index.

    TIME COMPLEXITY
    - O(log N), since we are performing binary search.

    SPACE COMPLEXITY
    - O(1), as no extra space is used.
*/

class Solution {
    public int lowerBound(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int low = 0, high = nums.length - 1;
        int resultIndex = nums.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                resultIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return resultIndex;
    }
}
```

<a id="upper-bound"></a>
### 3. Upper Bound [[Back to Top](#top)]

Given a sorted array of `nums` and an integer `x`, write a program to find the upper bound of `x`. The upper bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than a given key i.e. `x`.

If no such index is found, return the size of the array.

#### Examples

```plaintext
Input : n= 4, nums = [1,2,2,3], x = 2
Output: 3
Explanation: Index 3 is the smallest index such that arr[3] > x.
```

```plaintext
Input : n = 5, nums = [3,5,8,15,19], x = 9
Output: 3
Explanation: Index 3 is the smallest index such that arr[3] > x.
```

```plaintext
Input : n = 5, nums = [3,5,8,15,19], x = 3
Output: 1
```

#### Constraints:
  - 1 <= nums.length <= 10^5  
  - -10^5 < nums[i], x < 10^5  
  - nums is sorted in ascending order.


#### 3.1 APPROACH - UPPER BOUND USING BINARY SEARCH
```java
/*
    APPROACH - UPPER BOUND USING BINARY SEARCH
    1. Initialize two pointers: low (lo) at the start and high (hi) at the end of the array.
    2. Set the result index to the array length (default when target is greater than all elements).
    3. Use binary search to find the first position where target + 1 exists.
    4. If nums[mid] is greater than target, update result index and move left.
    5. If nums[mid] is less than or equal to target, move right.
    6. Return the result index.

    TIME COMPLEXITY
    - O(log N), since we are performing binary search.

    SPACE COMPLEXITY
    - O(1), as no extra space is used.
*/

class Solution {
    public int upperBound(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int low = 0, high = nums.length - 1;
        int resultIndex = nums.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > target) {
                resultIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return resultIndex;
    }
}
```


<a id="logic-building"></a>
## 2. Logic Building [[Back to Top](#top)]
- [1. Search insert position](#search-insert-position)
- [2. Floor and Ceil in Sorted Array](#floor-and-ceil)
- [3. First and last occurrence](#first-and-last-occurrence)
- [4. Search in rotated sorted array-I](#search-in-rotated-sorted-array-1)
- [5. Search in rotated sorted array-II](#search-in-rotated-sorted-array-2)
- [6. Find minimum in Rotated Sorted Array](#find-minimum-in-rotated-sorted-array)
- [7. Find Out How Many Times the Array is Rotated](#find-out-how-many-times-the-array-is-rotated)
- [8. Single Element in Sorted Array](#single-element-in-sorted-array)


### 1. Search Insert Position [[Back to Top](#top)]

Given a sorted array of `nums` consisting of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

#### Examples

```plaintext
Input: nums = [1, 3, 5, 6], target = 5
Output: 2
Explanation: The target value 5 is found at index 2 in the sorted array. Hence, the function returns 2.
```

```plaintext
Input: nums = [1, 3, 5, 6], target = 2
Output: 1
Explanation: The target value 2 is not found in the array. However, it should be inserted at index 1 to maintain the sorted order of the array.
```

```plaintext
Input: nums = [1, 3, 5, 6], target = 7
Output: 4
```

---


#### Constraints:
  - 1 <= nums.length <= 10^5  
  - -10^5 <= nums[i] <= 10^5  
  - nums contains distinct values sorted in ascending order.  
  - -10^5 <= target <= 10^5  

#### 1.1 APPROACH - ITERATIVE BINARY SEARCH
```java
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
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int low = 0, high = nums.length - 1;
        int resultIndex = nums.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                resultIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return resultIndex;
    }
}
```

<a id="floor-and-ceil"></a>
### 2. Floor and Ceil in Sorted Array [[Back to Top](#top)]

Given a sorted array `nums` and an integer `x`. Find the floor and ceil of `x` in `nums`. The floor of `x` is the largest element in the array which is smaller than or equal to `x`. The ceiling of `x` is the smallest element in the array greater than or equal to `x`. If no floor or ceil exists, output `-1`.

#### Examples

```plaintext
Input : nums =[3, 4, 4, 7, 8, 10], x= 5
Output: 4 7
Explanation: The floor of 5 in the array is 4, and the ceiling of 5 in the array is 7.
```

```plaintext
Input : nums =[3, 4, 4, 7, 8, 10], x= 8
Output: 8 8
Explanation: The floor of 8 in the array is 8, and the ceiling of 8 in the array is also 8.
```

```plaintext
Input : nums =[3, 4, 4, 7, 8, 10], x= 12
Output: 10 -1
```

#### Constraints:
  - 1 <= nums.length <= 10^5  
  - 0 < nums[i], x < 10^5  
  - nums is sorted in ascending order.  

---


#### 2.1 APPROACH - BINARY SEARCH FOR FLOOR AND CEIL
```java
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

    private int getFloor(int[] nums, int x) {
        int floor = -1;
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] <= x) {
                floor = nums[mid];
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return floor;
    }

    private int getCeil(int[] nums, int x) {
        int ceil = -1;
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] >= x) {
                ceil = nums[mid];
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ceil;
    }
}
```

<a id="first-and-last-occurrence"></a>
### 3. First and Last Occurrence [[Back to Top](#top)]

Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending position of a given `target` value. If the `target` is not found in the array, return `[-1, -1]`.

#### Examples

```plaintext
Input: nums = [5, 7, 7, 8, 8, 10], target = 8
Output: [3, 4]
Explanation:The target is 8, and it appears in the array at indices 3 and 4, so the output is [3,4].
```

```plaintext
Input: nums = [5, 7, 7, 8, 8, 10], target = 6
Output: [-1, -1]
Explanation: The target is 6, which is not present in the array. Therefore, the output is [-1, -1].
```

```plaintext
Input: nums = [5, 7, 7, 8, 8, 10], target = 5
Output: [0, 0]
```

#### Constraints:
  - 0 <= nums.length <= 10^5  
  - -10^9 <= nums[i] <= 10^9  
  - nums is a non-decreasing array.  
  - -10^9 <= target <= 10^9  


#### 3.1 APPROACH - LINEAR SEARCH
```java
/*
    APPROACH - LINEAR SEARCH
    1. Iterate through the array to find the first and last occurrence of the target.
    2. If the target is found, update first occurrence if it is unset and last occurrence each time it is encountered.
    3. Return an array containing the first and last occurrence indices.

    TIME COMPLEXITY
    - O(N), as we traverse the array once.

    SPACE COMPLEXITY
    - O(1), since we use only a few extra variables.
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1}; // Handle edge case

        int first = -1, last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) first = i; // Set first occurrence
                last = i; // Update last occurrence
            }
        }
        return new int[]{first, last};
    }
}
```

#### 3.2 APPROACH - BINARY SEARCH (LOWER AND UPPER BOUND)
```java
/*
    APPROACH - BINARY SEARCH (LOWER AND UPPER BOUND)
    1. Use binary search to find the lower bound (first occurrence) of the target.
    2. If the lower bound index is out of bounds or does not match the target, return [-1, -1].
    3. Use binary search to find the upper bound (last occurrence) of the target.
    4. Return an array with the first and last occurrence indices.

    TIME COMPLEXITY
    - O(log N), as we use binary search twice.

    SPACE COMPLEXITY
    - O(1), since we use only a few extra variables.
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lb = lowerBound(nums, target);
        if (lb == nums.length || nums[lb] != target) {
            return new int[]{-1, -1}; // Target not found
        }
        int ub = upperBound(nums, target) - 1;
        return new int[]{lb, ub};
    }

    private int lowerBound(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int result = nums.length;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                result = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return result;
    }

    private int upperBound(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int result = nums.length;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {
                result = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return result;
    }
}
```

#### 3.3 APPROACH - BINARY SEARCH (FIND FIRST AND LAST OCCURRENCE SEPARATELY)
```java
/*
    APPROACH - BINARY SEARCH (FIND FIRST AND LAST OCCURRENCE SEPARATELY)
    1. Use binary search to find the first occurrence of the target.
    2. If the target is not found, return [-1, -1].
    3. Use binary search to find the last occurrence of the target.
    4. Return an array with the first and last occurrence indices.

    TIME COMPLEXITY
    - O(log N), as we use binary search twice.

    SPACE COMPLEXITY
    - O(1), since we use only a few extra variables.
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1}; // Handle edge case

        int first = findFirstOccurrence(nums, target);
        if (first == -1) return new int[]{-1, -1}; // Target not found
        int last = findLastOccurrence(nums, target);
        
        return new int[]{first, last};
    }

    private int findFirstOccurrence(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int first = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                first = mid;
                hi = mid - 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return first;
    }

    private int findLastOccurrence(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int last = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                last = mid;
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return last;
    }
}
```

<a id="search-in-rotated-sorted-array-1"></a>
### 4. Search in rotated sorted array-I [[Back to Top](#top)]

Given an integer array `nums`, sorted in ascending order (with distinct values) and a target value `k`. The array is rotated at some pivot point that is unknown. Find the index at which `k` is present and if `k` is not present return `-1`.

#### Examples

```plaintext
Input : nums = [4, 5, 6, 7, 0, 1, 2], k = 0
Output: 4
Explanation: Here, the target is 0. We can see that 0 is present in the given rotated sorted array, nums. Thus, we get output as 4, which is the index at which 0 is present in the array.
```

```plaintext
Input: nums = [4, 5, 6, 7, 0, 1, 2], k = 3
Output: -1
Explanation: Here, the target is 3. Since 3 is not present in the given rotated sorted array. Thus, we get the output as -1.
```

```plaintext
Input: nums = [4, 5, 6, 7, 0, 1, 2], k = 5
Output: 1
```

#### Constraints:
  - 1 <= nums.length <= 10^4  
  - -10^4 <= nums[i] <= 10^4  
  - All values of nums are unique.  
  - nums is an ascending array that is possibly rotated.  
  - -10^4 <= k <= 10^4  

---


#### 4.1 APPROACH - LINEAR SEARCH

```java
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
```

#### 4.2 APPROACH - BINARY SEARCH IN ROTATED SORTED ARRAY

```java
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
```


<a id="search-in-rotated-sorted-array-2"></a>
### 5. Search in rotated sorted array-II [[Back to Top](#top)]

Given an integer array `nums`, sorted in ascending order (may contain duplicate values) and a target value `k`. Now the array is rotated at some pivot point unknown to you. Return `True` if `k` is present and otherwise, return `False`.

#### Examples

```plaintext
Input : nums = [7, 8, 1, 2, 3, 3, 3, 4, 5, 6], k = 3
Output: True
Explanation: The element 3 is present in the array. So, the answer is True.
```

```plaintext
Input : nums = [7, 8, 1, 2, 3, 3, 3, 4, 5, 6], k = 10
Output: False
Explanation:The element 10 is not present in the array. So, the answer is False.
```

```plaintext
Input : nums = [7, 8, 1, 2, 3, 3, 3, 4, 5, 6], k = 7
Output: True
```

#### Constraints:
  - 1 <= nums.length <= 10^4  
  - -10^4 <= nums[i] <= 10^4  
  - nums is guaranteed to be rotated at some pivot.  
  - -10^4 <= k <= 10^4  

---

#### 5.1 APPROACH - LINEAR SEARCH

```java
/*
    APPROACH - LINEAR SEARCH
    1. Iterate through the list and compare each element with the target.
    2. If the element matches the target, return true.
    3. If no match is found, return false.

    TIME COMPLEXITY
    - O(N), as we traverse the entire list in the worst case.

    SPACE COMPLEXITY
    - O(1), since no extra space is used.
*/

class Solution {
    public boolean searchInARotatedSortedArrayII(ArrayList<Integer> nums, int k) {
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == k) {
                return true;
            }
        }
        return false;
    }
}
```

#### 5.2 APPROACH - BINARY SEARCH IN A ROTATED SORTED ARRAY WITH DUPLICATES

```java
/*
    APPROACH - BINARY SEARCH IN A ROTATED SORTED ARRAY WITH DUPLICATES
    1. Use binary search to determine the sorted half of the list.
    2. If we can't determine the sorted half (duplicate elements at lo, mid, and hi), shrink the search space.
    3. If the left half is sorted:
       - Check if the target lies within this range and adjust search boundaries accordingly.
    4. If the right half is sorted:
       - Check if the target lies within this range and adjust search boundaries accordingly.
    5. Repeat until the target is found or the search space is exhausted.

    TIME COMPLEXITY
    - O(N) in the worst case due to duplicates causing unnecessary shifts.
    - O(log N) in the average case when binary search works efficiently.

    SPACE COMPLEXITY
    - O(1), since only a few extra variables are used.
*/

class Solution {
    public boolean searchInARotatedSortedArrayII(ArrayList<Integer> nums, int k) {
        int lo = 0;
        int hi = nums.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums.get(mid) == k) return true;
            
            // If we can't determine the sorted half, shrink the search space
            if (nums.get(lo) == nums.get(mid) && nums.get(mid) == nums.get(hi)) {
                lo++;
                hi--;
                continue;
            }
            
            // Identify the sorted half
            if (nums.get(lo) <= nums.get(mid)) {
                // Left half is sorted
                if (k >= nums.get(lo) && k <= nums.get(mid)) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                // Right half is sorted
                if (k >= nums.get(mid) && k <= nums.get(hi)) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return false;
    }
}
```

<a id="find-minimum-in-rotated-sorted-array"></a>
### 6. Find minimum in Rotated Sorted Array [[Back to Top](#top)]

Given an integer array `nums` of size `N`, sorted in ascending order with distinct values, and then rotated an unknown number of times (between `1` and `N`), find the minimum element in the array.

#### Examples

```plaintext
Input : nums = [4, 5, 6, 7, 0, 1, 2, 3]
Output: 0
Explanation: Here, the element 0 is the minimum element in the array.
```

```plaintext
Input : nums = [3, 4, 5, 1, 2]
Output: 1
Explanation: Here, the element 1 is the minimum element in the array.
```

```plaintext
Input : nums = [4, 5, 6, 7, -7, 1, 2, 3]
Output: -7
```

#### Constraints:
  - `n == nums.length`
  - `1 <= n <= 10^4`
  - `-10^4 <= nums[i] <= 10^4`
  - All the integers of `nums` are unique.
  - `nums` is sorted and rotated between `1` and `n` times.

---


#### 6.1 APPROACH - LINEAR SEARCH

```java
/*
    APPROACH - LINEAR SEARCH
    1. Initialize a variable to store the minimum value.
    2. Iterate through the list and update the minimum value.
    3. Return the minimum value after the iteration.

    TIME COMPLEXITY
    - O(N), as we traverse the entire list.

    SPACE COMPLEXITY
    - O(1), since no extra space is used.
*/

class Solution {
    public int findMin(ArrayList<Integer> arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.size(); i++) {
            min = Math.min(min, arr.get(i));
        }
        return min;
    }
}
```

#### 6.2 APPROACH - BINARY SEARCH IN ROTATED SORTED ARRAY

```java
/*
    APPROACH - BINARY SEARCH IN ROTATED SORTED ARRAY
    1. Use binary search to determine the minimum element.
    2. If the search space is already sorted, return the first element.
    3. If the left half is sorted, update the minimum and search the right half.
    4. If the right half is sorted, update the minimum and search the left half.
    5. Repeat until the minimum element is found.

    TIME COMPLEXITY
    - O(log N), as we use binary search.

    SPACE COMPLEXITY
    - O(1), since only a few extra variables are used.
*/

class Solution {
    public int findMin(ArrayList<Integer> arr) {
        int min = Integer.MAX_VALUE;
        int lo = 0;
        int hi = arr.size() - 1;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            // If the entire search space is sorted, return the first element
            if (arr.get(lo) <= arr.get(mid) && arr.get(mid) <= arr.get(hi)) {
                min = Math.min(min, arr.get(lo));
                break;
            }
            
            if (arr.get(lo) <= arr.get(mid)) {
                // Left half is sorted
                min = Math.min(min, arr.get(lo));
                lo = mid + 1;
            } else {
                // Right half is sorted
                min = Math.min(min, arr.get(mid));
                hi = mid - 1;
            }
        }
        return min;
    }
}
```

<a id="find-out-how-many-times-the-array-is-rotated"></a>
### 7. Find Out How Many Times the Array is Rotated [[Back to Top](#top)]

Given an integer array `nums` of size `n`, sorted in ascending order with distinct values. The array has been right rotated an unknown number of times, between `1` and `n`. Determine the number of rotations performed on the array.

#### Examples

```plaintext
Input : nums = [4, 5, 6, 7, 0, 1, 2, 3]
Output: 4
Explanation: The original array should be [0, 1, 2, 3, 4, 5, 6, 7]. So, we can notice that the array has been rotated 4 times.
```

```plaintext
Input: nums = [3, 4, 5, 1, 2]
Output: 3
Explanation: The original array should be [1, 2, 3, 4, 5]. So, we can notice that the array has been rotated 3 times.
```

```plaintext
Input: nums = [4, 5, 1, 2]
Output: 2
```

#### Constraints:
  - `n == nums.length`
  - `1 <= n <= 10^4`
  - `-10^4 <= nums[i] <= 10^4`
  - All the integers of `nums` are unique.

---

<a id="single-element-in-sorted-array"></a>
### 8. Single Element in Sorted Array [[Back to Top](#top)]

Given an array `nums` sorted in non-decreasing order. Every number in the array except one appears twice. Find the single number in the array.

#### Examples

```plaintext
Input : nums = [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
Output: 4
Explanation: Only the number 4 appears once in the array.
```

```plaintext
Input : nums = [1, 1, 3, 5, 5]
Output: 3
Explanation: Only the number 3 appears once in the array.
```

```plaintext
Input : nums = [1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7]
Output: 7
```

#### Constraints:
  - `n == nums.length`
  - `1 <= n <= 10^4`
  - `-10^4 <= nums[i] <= 10^4`
  
---

<a id="on-answers"></a>
## 3. On Answers [[Back to Top](#top)]
- [1. Find square root of a number](#find-square-root-of-a-number)
- [2. Find Nth root of a number](#find-nth-root-of-a-number)
- [3. Find the smallest divisor](#find-the-smallest-divisor)
- [4. Koko eating bananas](#koko-eating-bananas)
- [5. Minimum days to make M bouquets](#minimum-days-to-make-m-bouquets)

---

<a id="find-square-root-of-a-number"></a>
### 1. Find Square Root of a Number [[Back to Top](#top)] [[Back to Section](#on-answers)]

Given a positive integer `n`. Find and return its square root. If `n` is not a perfect square, then return the floor value of `sqrt(n)`.

#### Examples

```plaintext
Input: n = 36
Output: 6
Explanation: 6 is the square root of 36.
```

```plaintext
Input: n = 28
Output: 5
Explanation: The square root of 28 is approximately 5.292. 
So, the floor value will be 5.
```

```plaintext
Input: n=50
Output: 7
```

#### Constraints:
  - `0 <= n <= 2^{31} - 1`

---

<a id="find-nth-root-of-a-number"></a>
### 2. Find Nth Root of a Number [[Back to Top](#top)] [[Back to Section](#on-answers)]

Given two numbers `N` and `M`, find the Nth root of `M`. The Nth root of a number `M` is defined as a number `X` such that when `X` is raised to the power of `N`, it equals `M`. If the Nth root is not an integer, return `-1`.

#### Examples

```plaintext
Input: N = 3, M = 27
Output: 3
Explanation: The cube root of 27 is equal to 3.
```

```plaintext
Input: N = 4, M = 69
Output: -1
Explanation: The 4th root of 69 does not exist. 
So, the answer is -1.
```

```plaintext
Input: N = 4, M = 81
Output: 3
```

#### Constraints:
  - `1 <= N <= 30`
  - `1 <= M <= 10^9`

---

<a id="find-the-smallest-divisor"></a>
### 3. Find the Smallest Divisor [[Back to Top](#top)] [[Back to Section](#on-answers)]

Given an array of integers `nums` and an integer `limit` as the threshold value, find the smallest positive integer divisor such that upon dividing all the elements of the array by this divisor, the sum of the division results is less than or equal to the threshold value.

Each result of the division is rounded up to the nearest integer greater than or equal to that element.

#### Examples

```plaintext
Input: nums = [1, 2, 3, 4, 5], limit = 8
Output: 3
Explanation: We can get a sum of 15 (1 + 2 + 3 + 4 + 5) if we choose 1 as a divisor. 
The sum is 9 (1 + 1 + 2 + 2 + 3) if we choose 2 as a divisor. 
Upon dividing all the elements of the array by 3, we get 1,1,1,2,2 respectively. 
Now, their sum is equal to 7 <= 8 i.e. the threshold value. 
So, 3 is the minimum possible answer.
```

```plaintext
Input: nums = [8,4,2,3], limit = 10
Output: 2
Explanation: If we choose 1, we get 17 as the sum. 
If we choose 2, we get 9 (4+2+1+2) <= 10 as the answer. 
So, 2 is the answer.
```

```plaintext
Input: nums = [8,4,2,3], limit = 4
Output: 8
```

#### Constraints:
  - `1 <= nums.length <= 5 * 10^4`
  - `1 <= nums[i] <= 10^6`
  - `nums.length <= limit <= 10^6`

---

<a id="koko-eating-bananas"></a>
### 4. Koko Eating Bananas [[Back to Top](#top)] [[Back to Section](#on-answers)]

A monkey is given `n` piles of bananas, where the `i-th` pile has `nums[i]` bananas. An integer `h` represents the total time in hours to eat all the bananas.

Each hour, the monkey chooses a non-empty pile of bananas and eats `k` bananas. If the pile contains fewer than `k` bananas, the monkey eats all the bananas in that pile and does not consume any more bananas in that hour.

Determine the minimum number of bananas the monkey must eat per hour to finish all the bananas within `h` hours.

#### Examples

```plaintext
Input: n = 4, nums = [7, 15, 6, 3], h = 8
Output: 5
Explanation: If Koko eats 5 bananas/hr, he will take 2, 3, 2, and 1 hour to eat the piles accordingly. 
So, he will take 8 hours to complete all the piles.
```

```plaintext
Input: n = 5, nums = [25, 12, 8, 14, 19], h = 5
Output: 25
Explanation: If Koko eats 25 bananas/hr, he will take 1, 1, 1, 1, and 1 hour to eat the piles accordingly. 
So, he will take 5 hours to complete all the piles.
```

```plaintext
Input: n = 4, nums = [3, 7, 6, 11], h = 8
Output: 4
```

#### Constraints:
  - `1 <= n <= 10^4`
  - `n <= h <= 10^9`
  - `1 <= nums[i] <= 10^9`

---

<a id="minimum-days-to-make-m-bouquets"></a>
### 5. Minimum Days to Make M Bouquets [[Back to Top](#top)] [[Back to Section](#on-answers)]

Given `n` roses and an array `nums` where `nums[i]` denotes that the `i`th rose will bloom on the `nums[i]`th day, only adjacent bloomed roses can be picked to make a bouquet. Exactly `k` adjacent bloomed roses are required to make a single bouquet. Find the minimum number of days required to make at least `m` bouquets, each containing `k` roses. Return `-1` if it is not possible.

#### Examples

```plaintext
Input: n = 8, nums = [7, 7, 7, 7, 13, 11, 12, 7], m = 2, k = 3
Output: 12
Explanation: On the 12th the first 4 flowers and the last 3 flowers would have already bloomed. So, we can easily make 2 bouquets, one with the first 3 and another with the last 3 flowers.
```

```plaintext
Input: n = 5, nums = [1, 10, 3, 10, 2], m = 3, k = 2
Output: -1
Explanation: If we want to make 3 bouquets of 2 flowers each, we need at least 6 flowers. But we are given only 5 flowers, so, we cannot make the bouquets.
```

```plaintext
Input: n = 5, nums = [1, 10, 3, 10, 2], m = 3, k = 1
Output: 3
```

#### Constraints:
  - `1 <= n <= 10^5`
  - `1 <= nums[i] <= 10^9`
  - `1 <= m <= 10^6`
  - `1 <= k <= n`


<a id="faqs"></a>
## 4. FAQs [[Back to Top](#top)]
- [1. Aggressive Cows](#aggressive-cows)
- [2. Book Allocation Problem](#book-allocation-problem)
- [3. Find peak element](#find-peak-element)
- [4. Median of 2 sorted arrays](#median-of-2-sorted-arrays)
- [5. Minimise max distance to gas stations](#minimise-max-distance-to-gas-stations)
- [6. Kth element of 2 sorted arrays](#kth-element-of-2-sorted-arrays)
- [7. Split array - largest sum](#split-array-largest-sum)

---

<a id="2d-arrays"></a>
## 5. 2D Arrays [[Back to Top](#top)]
- [1. Find row with maximum 1's](#find-row-with-maximum-1s)
- [2. Search in a 2D matrix](#search-in-a-2d-matrix)
- [3. Search in 2D matrix - II](#search-in-2d-matrix-ii)
- [4. Find Peak Element](#find-peak-element-2)
- [5. Matrix Median](#matrix-median)

---

