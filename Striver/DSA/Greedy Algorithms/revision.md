# Greedy Algorithms Revision
<a id="top"></a>
This document compiles various Java solutions for popular greedy and scheduling problems, organized by difficulty. Click on a section to jump to its problems.

**Sections**
- [1. Easy](#easy)
    - [1. Assign Cookies](#assign-cookies)
    - [2. Lemonade Change](#lemonade-change)
    - [3. Jump Game I](#jump-game-i)
    - [4. Jump Game II](#jump-game-ii)
- [2. Scheduling and Interval Problems](#scheduling-and-interval-problems)
    - [1. Shortest Job First](#shortest-job-first)  
    - [2. Job Sequencing Problem](#job-sequencing-problem)  
    - [3. N Meetings in One Room](#n-meetings-in-one-room)  
    - [4. Non-overlapping Intervals](#non-overlapping-intervals)  
    - [5. Insert Interval](#insert-interval)  
    - [6. Minimum Platforms](#minimum-platforms)  
- [3. Hard](#hard)
    - [1. Valid Parenthesis Checker](#valid-parenthesis-checker)  
    - [2. Candy](#candy)  
    - [3. Fractional Knapsack](#fractional-knapsack)  

---

<a id="easy"></a>
## 1. Easy [[Back to Top](#top)]
- [1. Assign Cookies](#assign-cookies)  
- [2. Lemonade Change](#lemonade-change)  
- [3. Jump Game I](#jump-game-i)  
- [4. Jump Game II](#jump-game-ii)  

### 1.1 Assign Cookies [[Back to Easy](#easy)]
**Problem Statement**  
Assign cookies to children to maximize the number content. Each child has a greed factor `g[i]`, each cookie has size `s[j]`. A child is content if `s[j] >= g[i]`.  
_Link:_ [LeetCode 455](https://leetcode.com/problems/assign-cookies/)

**Examples**  
```
Input: g = [1,2,3], s = [1,1]  
Output: 1
```  
```
Input: g = [1,2], s = [1,2,3]  
Output: 2
```

**Constraints**  
- `1 <= g.length, s.length <= 3 * 10^4`  
- `1 <= g[i], s[j] <= 2^31 - 1`

```java
/*
    APPROACH
    - Sort both arrays: children's greed (g) and cookie sizes (s).
    - Try to satisfy each child with the smallest cookie that works:
        - If the current cookie can satisfy the current child, move to the next child.
        - Always move to the next cookie.
    - The answer is how many children were satisfied.

    TIME: O(M log M + N log N)   // Sorting both arrays (M = g.length, N = s.length)
    SPACE: O(1)                  // No extra space (sorting is in-place)
*/
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;

        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
```

### 1.2 Lemonade Change [[Back to Easy](#easy)]
**Problem Statement**  
Provide change for a \$5 lemonade: customers pay with 5, 10, or 20.  
_Link:_ [LeetCode 860](https://leetcode.com/problems/lemonade-change/)

**Examples**  
```
Input: bills = [5,5,5,10,20]  Output: true
```  
```
Input: bills = [5,5,10,10,20]  Output: false
```

**Constraints**  
- `1 <= bills.length <= 10^5`  
- `bills[i]` is 5, 10, or 20.

```java
/*
    APPROACH
    - Track count of $5 and $10 bills.
    - For each customer:
        - If they pay with $5: just take it.
        - If they pay with $10: give one $5 as change (if possible).
        - If they pay with $20: 
            - Prefer to give one $10 + one $5 as change (if possible).
            - Otherwise, give three $5 bills.
        - If not enough change at any point, return false.
    - If all customers get change, return true.

    TIME: O(N)   // Go through the bills once
    SPACE: O(1)  // Only a few counters used
*/
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                if (five >= 1) {
                    five--;
                    ten++;
                } else {
                    return false;
                }
            } else {
                if (ten >= 1 && five >= 1) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### 1.3 Jump Game I [[Back to Easy](#easy)]
**Problem Statement**  
Determine if you can reach the last index given max jump lengths at each position.  
_Link:_ [LeetCode 55](https://leetcode.com/problems/jump-game/)

**Examples**  
```
Input: nums = [2,3,1,1,4]  Output: true
```  
```
Input: nums = [3,2,1,0,4]  Output: false
```

**Constraints**  
- `1 <= nums.length <= 10^4`  
- `0 <= nums[i] <= 10^5`

```java
/*
    APPROACH
    - Keep track of the farthest index you can reach (`max`).
    - For each position, check:
        - If you can't reach this position, return false.
        - Update `max` to the farthest you can reach from here.
        - If `max` reaches or passes the last index, return true.
    - If you finish the loop, it means the end is reachable.

    TIME: O(N)  // Single pass through the array
    SPACE: O(1) // No extra space used
*/
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > max)
                return false;
            max = Math.max(max, i + nums[i]);
            if (max >= n - 1)
                return true;
        }
        return true;
    }
}
```

### 1.4 Jump Game II [[Back to Easy](#easy)]
**Problem Statement**  
Return minimum jumps to reach last index given max jump lengths.  
_Link:_ [LeetCode 45](https://leetcode.com/problems/jump-game-ii/)

**Examples**  
```
Input: nums = [2,3,1,1,4]  Output: 2
```  
```
Input: nums = [2,3,0,1,4]  Output: 2
```

**Constraints**  
- `1 <= nums.length <= 10^4`  
- `0 <= nums[i] <= 10^5`

```java
/*
    APPROACH
    - Track the farthest position you can reach at each step (farthest).
    - When you reach the end of the current jump (endOfJump), increment jumpCount and set the new endOfJump to farthest.
    - Continue until you can reach or pass the last index.
    - Always take the minimum number of jumps.

    TIME: O(N)   // Single pass through the array
    SPACE: O(1)  // No extra space
*/
class Solution {
    public int jump(int[] nums) {
        int jumpCount = 0;
        int endOfJump = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == endOfJump) {
                jumpCount++;
                endOfJump = farthest;
            }
        }
        return jumpCount;
    }
}
```

---

<a id="scheduling-and-interval-problems"></a>
## 2. Scheduling and Interval Problems [[Back to Top](#top)]
- [1. Shortest Job First](#shortest-job-first)  
- [2. Job Sequencing Problem](#job-sequencing-problem)  
- [3. N Meetings in One Room](#n-meetings-in-one-room)  
- [4. Non-overlapping Intervals](#non-overlapping-intervals)  
- [5. Insert Interval](#insert-interval)  
- [6. Minimum Platforms](#minimum-platforms)  

### 2.1 Shortest Job First [[Back to Scheduling and Interval Problems](#scheduling-and-interval-problems)]
**Problem Statement**  
Calculate average waiting time using shortest-job-first scheduling.  
_Link:_ [GFG SJF Scheduling](https://www.geeksforgeeks.org/shortest-job-first-or-sjf-cpu-scheduling/)

**Examples**  
```
Input: arr = [3,1,4,3]  Output: 3
```

```java
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
```

### 2.2 Job Sequencing Problem [[Back to Scheduling and Interval Problems](#scheduling-and-interval-problems)]
**Problem Statement**  
Maximize profit by scheduling jobs before deadlines.  
_Link:_ [GFG Job Sequencing](https://www.geeksforgeeks.org/job-sequencing-problem/)

**Examples**  
```
Input: jobs = {{1,4,20},{2,1,10},{3,1,40},{4,1,30}}  
Output: {2, 60}
```

```java
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
```

### 2.3 N Meetings in One Room [[Back to Scheduling and Interval Problems](#scheduling-and-interval-problems)]
**Problem Statement**  
Maximize number of non-overlapping meetings given start and end times.  
_Link:_ [GFG N Meetings](https://www.geeksforgeeks.org/n-meetings-in-one-room/)

**Examples**  
```
Input: start = [1,3,0,5,8,5], end = [2,4,6,7,9,9]  
Output: 4
```

```java
/*
    GREEDY APPROACH
    - Pair each meeting's start and end time as an object.
    - Sort all meetings by their end time.
    - Iterate through meetings, always select the next meeting that starts after the last selected meeting ends.
    - Count the number of non-overlapping meetings that can be attended.

    TIME: O(N) to build meeting objects
        + O(N log N) to sort by end time
        + O(N) to iterate and select meetings
        = O(N log N)

      - Building objects and counting is linear (O(N)).
      - Sorting the meetings dominates (O(N log N)).

    SPACE: O(N) for list of Meeting objects
      - Need space to store meeting details.

    Why?
    - Sorting is the slowest step.
    - Linear time to scan and select meetings.
*/
class Solution {
    public int maxMeetings(int[] start, int[] end) {
       int n = start.length;

       List<Meeting> list = new ArrayList<>();
       for (int i = 0; i < n; i++) {
           Meeting meeting = new Meeting(start[i], end[i], i+1);
           list.add(meeting);
       }

       list.sort(Comparator.comparing(Meeting::getEnd));

       int lastTime = 0;
       int count = 0;

       for (Meeting meeting : list) {
           if (meeting.getStart() > lastTime) {
               lastTime = meeting.getEnd();
               count++;
           }
       }

       return count;
    }
}

class Meeting {
    int start;
    int end;
    int position;

    public Meeting(int start, int end, int position) {
        this.start = start;
        this.end = end;
        this.position = position;
    }

    public int getEnd() {
        return this.end;
    }

    public int getStart() {
        return this.start;
    }
}
```

### 2.4 Non-overlapping Intervals [[Back to Scheduling and Interval Problems](#scheduling-and-interval-problems)]
**Problem Statement**  
Return minimum intervals to remove so remaining are non-overlapping.  
_Link:_ [LeetCode 435](https://leetcode.com/problems/non-overlapping-intervals/)

**Examples**  
```
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]  Output: 1
```

```java
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
```

### 2.5 Insert Interval [[Back to Scheduling and Interval Problems](#scheduling-and-interval-problems)]
**Problem Statement**  
Insert and merge a new interval into sorted list of non-overlapping intervals.  
_Link:_ [LeetCode 57](https://leetcode.com/problems/insert-interval/)

**Examples**  
```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]  
Output: [[1,5],[6,9]]
```

```java
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
```

### 2.6 Minimum Platforms [[Back to Scheduling and Interval Problems](#scheduling-and-interval-problems)]
**Problem Statement**  
Find minimum number of platforms needed so no train waits given arrival and departure arrays.  
_Link:_ [LeetCode 253](https://leetcode.com/problems/meeting-rooms-ii/)

**Examples**  
```
Input: arrival = [900,940,950,1100,1500,1800], departure = [910,1200,1120,1130,1900,2000]
Output: 3
```

```java
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
```

---

<a id="hard"></a>
## 3. Hard [[Back to Top](#top)]
- [1. Valid Parenthesis Checker](#valid-parenthesis-checker)  
- [2. Candy](#candy)  
- [3. Fractional Knapsack](#fractional-knapsack)  

### 3.1 Valid Parenthesis Checker [[Back to Hard](#hard)]
**Problem Statement**  
Check if string containing '(', ')', '*' is valid. '*' can be '(', ')' or empty.  
_Link:_ [LeetCode 678](https://leetcode.com/problems/valid-parenthesis-string/)

**Examples**  
```
Input: s = "()"
Output: true
```  
```
Input: s = "(*)"
Output: true
```

```java
/*
    RECURSIVE APPROACH (BRUTE FORCE)
    - For each character:
      - If it's '(', increment the count.
      - If it's ')', decrement the count.
      - If it's '*', try all three options: '(', ')', or skip.
    - At the end, the count must be zero for a valid string.

    TIME: O(3^N)
      - For every '*', the function branches into 3 choices (replace with '(', ')', or skip).
      - In the worst case (all '*' in the string), total recursive calls = 3^N.

    SPACE: O(N)
      - Maximum recursion stack depth can reach the length of the string (N).

    Why?
    - Brute-force explores all possibilities using recursion, leading to exponential time.
*/
class Solution {
    public boolean checkValidString(String s) {
        return checkValidString(s, 0, 0);
    }
    public boolean checkValidString(String s, int idx, int c) {
        if (c < 0) return false;
        if (idx == s.length()) return c == 0;

        if (s.charAt(idx) == '(')
            return checkValidString(s, idx + 1, c + 1);
        else if (s.charAt(idx) == ')')
            return checkValidString(s, idx + 1, c - 1);
        else
            return checkValidString(s, idx + 1, c + 1) ||
                    checkValidString(s, idx + 1, c - 1) ||
                    checkValidString(s, idx + 1, c);
    }
}

/*
    OPTIMAL GREEDY APPROACH
    - Use two counters (min and max) to track the possible range of open brackets.
    - For each character:
      - If '(', increase both min and max.
      - If ')', decrease both min and max.
      - If '*', treat it as '(', ')', or empty, so min-- and max++.
    - Clamp min to 0 (it can't be negative).
    - If max becomes negative, more ')' than '(' and '*', so return false.
    - String is valid if min is zero at the end.

    TIME: O(N)
      - Single pass over the string (each character checked once).

    SPACE: O(1)
      - Only a few integer variables used.

    Why?
    - Both counters move through the string only once, no extra memory or recursion.
*/
class Solution {
    public boolean checkValidString(String s) {
        int min = 0;
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                min += 1;
                max += 1;
            } else if(s.charAt(i) == ')'){
                min -= 1;
                max -= 1;
            } else { // '*'
                min -= 1;
                max += 1;
            }
            if(min < 0) min = 0;
            if(max < 0) return false;
        }
        return min == 0;
    }
}
```

### 3.2 Candy [[Back to Hard](#hard)]
**Problem Statement**  
Distribute candies such that children with higher rating get more candy than neighbor.  
_Link:_ [LeetCode 135](https://leetcode.com/problems/candy/)

**Examples**  
```
Input: ratings = [1,0,2]
Output: 5
```  
```
Input: ratings = [1,2,2]
Output: 4
```

```java
/*
    TWO PASS APPROACH
    - Do a left-to-right pass: each child with a higher rating than the left neighbor gets more candy.
    - Do a right-to-left pass: each child with a higher rating than the right neighbor gets more candy.
    - At the end, each child gets the max candy from both passes.

    TIME: O(N) first pass + O(N) second pass + O(N) sum = O(3N) = O(N)
      - One pass to fill left[], one pass to fill right[], one pass to sum up candies.
      - Each pass is linear in array size.

    SPACE: O(N) for left[] + O(N) for right[] = O(2N) = O(N)
      - Need two arrays to track minimum candies in both directions.
*/
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int left[] = new int[n];
        int right[] = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        right[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }

        return sum;
    }
}

/*
    TWO PASS OPTIMIZED SPACE APPROACH
    - Do a left-to-right pass and store result in left[].
    - Do a right-to-left pass with a single integer, combine result with left[].
    - Sum up maximum of left[i] and right count for each child.

    TIME: O(N) left pass + O(N) right pass + O(N) sum = O(3N) = O(N)
      - Each loop is a single pass.

    SPACE: O(N) for left[] + O(1) for right = O(N)
      - Only one extra array, right uses a single variable.
*/
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int left[] = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        int sum = left[n - 1];
        int right = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            sum += Math.max(left[i], right);
        }

        return sum;
    }
}

/*
    SINGLE PASS PEAK-VALLEY APPROACH
    - Use one loop to track increasing (uphill) and decreasing (downhill) sequences.
    - Handle equal ratings directly.
    - Adjust sum for overlap between peaks and valleys.

    TIME: O(N)
      - While loop runs through the array once, with some nested increments but total work is still linear.

    SPACE: O(1)
      - Only variables for counts and pointers.
      - No extra arrays used.
*/
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int sum = 1;
        int i = 1;
        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                sum++;
                i++;
                continue;
            }
            int peak = 1;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak++;
                sum += peak;
                i++;
            }
            int down = 1;
            while (i < n && ratings[i] < ratings[i - 1]) {
                sum += down;
                down++;
                i++;
            }
            if (down > peak) {
                sum += (down - peak);
            }
        }
        return sum;
    }
}
```

### 3.3 Fractional Knapsack [[Back to Hard](#hard)]
**Problem Statement**  
Maximize value with fractional items given capacity.  
_Link:_ [GFG Fractional Knapsack](https://www.geeksforgeeks.org/fractional-knapsack-problem/)

**Examples**  
```
Input: values = [60,100,120], weights = [10,20,30], W = 50
Output: 240.0
```

```java
/*
    GREEDY APPROACH (FRACTIONAL KNAPSACK)
    - For each item, compute value/weight ratio.
    - Sort items by ratio in descending order.
    - Pick as much as possible from the highest ratio item until the knapsack is full.
    - If you can't take the whole item, take the possible fraction and stop.

    TIME: O(N) to build items + O(N log N) for sorting + O(N) for picking = O(N log N)
      - O(N): Build the array of item objects.
      - O(N log N): Sort the items by value/weight ratio.
      - O(N): Loop through the sorted items and fill the knapsack.
      - Total: O(N + N log N + N) = O(N log N) (sorting dominates).

    SPACE: O(N)
      - Extra space for the items array (stores all items as objects).
      - No other significant data structures.

    Why?
    - Building and iterating: O(N)
    - Sorting: O(N log N) is the largest term, so it determines the final time complexity.
*/
class Solution {
    class Item{
        int value;
        int weight;
        double ratio;

        Item(int value,int weight){
            this.value = value;
            this.weight=weight;
            this.ratio = (double)value/weight;
        }

        int getWeight(){return this.weight;}
        int getValue(){return this.value;}
        double getRatio(){return this.ratio;}
    }
    double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n  = values.length;
        Item[] items= new Item[n];
        for(int i=0;i<n;i++){
            items[i] = new Item(values[i],weights[i]);
        }

        Arrays.sort(items, (a,b) -> Double.compare(b.ratio,a.ratio));

        double res = 0;
        for(int i=0;i<n;i++){
            if(items[i].getWeight() <= W){
                res += items[i].getValue();
                W-=items[i].getWeight();
            }else{
                res += W * items[i].getRatio();
                break;
            }
        }

        return res;
    }
}
```