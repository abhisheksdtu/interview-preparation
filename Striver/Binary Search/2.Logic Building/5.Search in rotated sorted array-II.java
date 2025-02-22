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
