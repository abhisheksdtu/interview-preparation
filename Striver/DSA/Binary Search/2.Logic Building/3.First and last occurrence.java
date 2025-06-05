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

    /*
        Find First Occurrence
        1. Use binary search to locate the first occurrence of the target.
        2. If nums[mid] is equal to target, store mid as a potential first occurrence and move left.
        3. If nums[mid] is greater than target, move left.
        4. If nums[mid] is smaller than target, move right.
        5. Return the first occurrence index or -1 if not found.
    */
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

    /*
        Find Last Occurrence
        1. Use binary search to locate the last occurrence of the target.
        2. If nums[mid] is equal to target, store mid as a potential last occurrence and move right.
        3. If nums[mid] is greater than target, move left.
        4. If nums[mid] is smaller than target, move right.
        5. Return the last occurrence index or -1 if not found.
    */
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
