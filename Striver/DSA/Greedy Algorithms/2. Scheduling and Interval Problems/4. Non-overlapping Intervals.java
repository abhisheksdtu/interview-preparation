/*
    GREEDY APPROACH
    - Sort all intervals by their end time.
    - Iterate through intervals:
        - If current interval starts after or at the end of last selected, update end.
        - Else, overlap found, increment count (need to remove this interval).
    - Return the count of intervals to remove.

    TIME: O(N log N) for sorting intervals by end time
        + O(N) for iterating through intervals
        = O(N log N)
      - Sorting dominates the overall time.

    SPACE: O(1)
      - Just variables for tracking time and count.
      - Sorting is in-place, so no extra space for data structures.

    Why?
    - Sorting is the slowest step (O(N log N)).
    - One linear pass to count overlapping intervals.

    Note:
    - Problem can also be interpreted as maximum set of non-overlapping intervals, but this version counts removals.
*/
class Solution {
    public int MaximumNonOverlappingIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((int[] interval) -> interval[1]));

        int lastEndTime = 0;
        int count = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= lastEndTime) {
                lastEndTime = intervals[i][1];
            } else {
                count++;
            }
        }
        return count;
    }
}
