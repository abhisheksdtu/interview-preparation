/*
    INSERT INTERVAL (MERGE IF NEEDED)
    - Go through all intervals:
        - Add intervals ending before the new interval (no overlap).
        - Merge all overlapping intervals into the new interval.
        - Add remaining intervals after the new interval.
    - Return the merged intervals as a new array.

    TIME: O(N)
      - Each interval is visited exactly once in the while loops.
      - All merging and additions are constant time per interval.

    SPACE: O(N)
      - The result list stores up to all original intervals plus one new (at most N+1 intervals).
      - Conversion to array is also linear.

    Why?
    - Linear scan through intervals.
    - No extra sorting, just a single pass and a result list.

    Note:
    - Efficient because intervals are already sorted by start time.
*/
class Solution {
    public int[][] insertNewInterval(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int n = intervals.length;
        int i = 0;
        // Add intervals before newInterval
        while(i < n && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        // Merge overlapping intervals
        while(i < n && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        // Add remaining intervals
        while(i < n){
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }
}
