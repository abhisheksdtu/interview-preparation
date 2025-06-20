/*
    SHORTEST JOB FIRST (NON-PREEMPTIVE)
    - Sort all jobs by their duration (shortest first).
    - For each job, add its waiting time (time taken so far) to total waiting time.
    - Update total time after completing each job.
    - Return average waiting time.

    TIME: O(N log N) for sorting + O(N) for calculating waiting times = O(N log N)
      - Sorting the array dominates: O(N log N)
      - The loop to sum waiting times is O(N)
      - Total: O(N log N) + O(N) = O(N log N)

    SPACE: O(1)
      - Sorting is in-place and only a few variables are used.

    Why?
    - Sorting is the slowest step.
    - Summing waiting times is a single linear scan.
*/
class Solution {
    public long solve(int[] arr) {
        Arrays.sort(arr);
        long currentTime = 0;
        long waitingTime = 0;
        for(int i = 0; i < arr.length; i++) {
            waitingTime += currentTime;
            currentTime += arr[i];
        }
        return waitingTime / arr.length;
    }
}
