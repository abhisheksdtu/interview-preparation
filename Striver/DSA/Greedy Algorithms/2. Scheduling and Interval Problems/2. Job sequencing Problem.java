/*
    GREEDY APPROACH FOR JOB SEQUENCING
    - Sort jobs in decreasing order of profit.
    - For each job, try to schedule it on the latest possible free slot before its deadline.
    - Track the number of jobs done and the total profit.

    TIME:
      O(N log N) for sorting jobs by profit (N = number of jobs)
    + O(N * D) for scheduling jobs (each job may check up to D slots, D = max deadline)
    = O(N log N + N * D)

    SPACE:
      O(D) for the filled[] array to track scheduled slots (D = max deadline)
      O(1) extra (if you ignore result and constant variables)

    Why?
    - Sorting jobs is O(N log N)
    - For each job, you may have to look back through its deadline (up to D checks)
    - filled[] array is proportional to max deadline (not number of jobs)

    Note:
    - If max deadline D is small, this is efficient.
    - For large D, can optimize further with Disjoint Set (not shown here).
*/
class Solution {
  public int[] JobScheduling(int[][] jobs) {
    Arrays.sort(jobs, Comparator.comparingInt((int row[]) -> row[2]).reversed());

    int maxDeadline = 0;
    for (int[] job : jobs) {
      maxDeadline = Math.max(job[1], maxDeadline);
    }
    boolean filled[] = new boolean[maxDeadline + 1];
    int maxJobs = 0;
    int maxProfit = 0;

    for (int[] job : jobs) {
      int jobDeadline = job[1];
      int jobProfit = job[2];

      for (int i = jobDeadline; i > 0; i--) {
        if (!filled[i]) {
          filled[i] = true;
          maxJobs++;
          maxProfit += jobProfit;
          break;
        }
      }
    }

    int res[] = new int[] {maxJobs, maxProfit};
    return res;
  }
}
