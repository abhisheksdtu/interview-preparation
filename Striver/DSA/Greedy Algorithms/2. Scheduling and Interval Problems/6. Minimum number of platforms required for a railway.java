/*
    TWO POINTERS WITH SORTING
    - Sort arrival and departure times separately.
    - Use two pointers:
        - If the next train arrives before the earliest departure, need a new platform.
        - Else, a train departs, free up a platform.
    - Track the maximum platforms needed at any time.

    TIME: O(N log N) for sorting arrival + O(N log N) for sorting departure + O(N) for processing = O(N log N)
      - Sorting both arrays is the dominant step.
      - Linear scan through both arrays with two pointers.

    SPACE: O(1)
      - No extra space used except for variables and in-place sorting.
      - No extra data structures.

    Why?
    - Sorting is the slowest step, and dominates the complexity.
    - Only a few pointers and counters used.

    Note:
    - Efficient for large inputs as both arrays are sorted only once.
*/
class Solution {
    public int findPlatform(int[] arrival, int[] departure) {
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int n = arrival.length;
        int maxPlatforms = 1;
        int currentPlatforms = 1;
        int i = 1, j = 0;
        while(i < n && j < n){
            if(departure[j] >= arrival[i]){
                currentPlatforms++;
                i++;
            }else{
                currentPlatforms--;
                j++;
            }
            maxPlatforms = Math.max(maxPlatforms, currentPlatforms);
        }
        return maxPlatforms;
    }
}
