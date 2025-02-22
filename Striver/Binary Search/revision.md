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
    - [7. Search in rotated sorted array-I](#search-in-rotated-sorted-array-1)
    - [8. Search in rotated sorted array-I](#search-in-rotated-sorted-array-1)
    
---

<a id="fundamentals"></a>
## 1. Fundamentals [[Back to Top](#top)]
- [1. Search X in sorted array](#search-x-in-sorted-array)
- [2. Lower Bound](#lower-bound)
- [3. Upper Bound](#upper-bound)

<a id="search-x-in-sorted-array"></a>
### 1. Search X in sorted array [[Back to Top](#top)]

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

<a id="search-insert-position"></a>

<a id="logic-building"></a>
## 2. Logic Building [[Back to Top](#top)]
- [1. Search insert position](#search-insert-position)
- [2. Floor and Ceil in Sorted Array](#floor-and-ceil)
- [3. First and last occurrence](#first-and-last-occurrence)
- [4. Search in rotated sorted array-I](#search-in-rotated-sorted-array-1)
- [5. Search in rotated sorted array-II](#search-in-rotated-sorted-array-2)
- [6. Find minimum in Rotated Sorted Array](#find-minimum-in-rotated-sorted-array)
- [7. Search in rotated sorted array-I](#search-in-rotated-sorted-array-1)
- [8. Search in rotated sorted array-I](#search-in-rotated-sorted-array-1)


### 1. Search Insert Position [[Back to Top](#top)]

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
