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
