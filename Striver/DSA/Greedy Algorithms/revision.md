# Java Code Revision
<a id="top"></a>
This document compiles various Java solutions for popular coding problems along with detailed commentary. Problems covered:

- [1. Assign Cookies](#assign-cookies)
- [2. Lemonade Change](#lemonade-change)
- [3. Jump Game I](#jump-game-i)
- [4. Jump Game II](#jump-game-ii)

---

<a id="assign-cookies"></a>
## 1. Assign Cookies [[Back to Top](#top)]

### Problem Statement  
Given two integer arrays `g` (children's greed factors) and `s` (cookie sizes), assign cookies to children such that the maximum number of children are content. A child `i` is content if they receive a cookie of size >= `g[i]`. Each child can receive at most one cookie, and each cookie can be assigned to at most one child.  
_Link:_ https://leetcode.com/problems/assign-cookies/

### Examples
```txt
Example 1:
Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: Only the first child can be content with size 1.
```
```txt
Example 2:
Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: Both children can be content.
```

### Constraints
- `1 <= g.length, s.length <= 3 * 10^4`
- `1 <= g[i], s[j] <= 2^31 - 1`

---

### 1.1 Greedy Approach – Sorting (Optimal)
```java
/*
    APPROACH
    - Sort both arrays: children's greed (g) and cookie sizes (s).
    - Try to satisfy each child with the smallest cookie that works:
        - If s[j] >= g[i], assign and move to next child.
        - Always move to the next cookie.
    - The number of content children is i after traversal.

    TIME COMPLEXITY: O(M log M + N log N), where M = g.length and N = s.length
    SPACE COMPLEXITY: O(1) (in-place sorting)
*/
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
```

---

<a id="lemonade-change"></a>
## 2. Lemonade Change [[Back to Top](#top)]

### Problem Statement  
At a lemonade stand, each lemonade costs \$5. Customers come in order, paying with a \$5, \$10, or \$20 bill. You must provide correct change:  
- \$5 bill: no change needed.  
- \$10 bill: change \$5.  
- \$20 bill: change \$15 (prefer one \$10 + one \$5, otherwise three \$5).  

Return `true` if you can provide change to every customer, or `false` otherwise.  
_Link:_ https://leetcode.com/problems/lemonade-change/

### Examples
```txt
Example 1:
Input: bills = [5,5,5,10,20]
Output: true
```
```txt
Example 2:
Input: bills = [5,5,10,10,20]
Output: false
```

### Constraints
- `1 <= bills.length <= 10^5`
- `bills[i]` is 5, 10, or 20.

---

### 2.1 Greedy Bill-Tracking Approach (Optimal)
```java
/*
    APPROACH
    - Track count of $5 and $10 bills.
    - Iterate through each bill:
        - If $5: increment five.
        - If $10: decrement five, increment ten.
        - If $20: prefer decrement ten & five; else decrement five by 3.
    - If change cannot be made at any step, return false.
    - Otherwise, return true.

    TIME COMPLEXITY: O(N), one pass through bills
    SPACE COMPLEXITY: O(1), constant extra variables
*/
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (ten > 0 && five > 0) {
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

---

<a id="jump-game-i"></a>
## 3. Jump Game I [[Back to Top](#top)]

### Problem Statement  
Given an array of non-negative integers `nums`, you are initially positioned at the first index. Each element `nums[i]` represents the maximum jump length from that position. Determine if you can reach the last index.  
_Link:_ https://leetcode.com/problems/jump-game/

### Examples
```txt
Example 1:
Input: nums = [2,3,1,1,4]
Output: true
```
```txt
Example 2:
Input: nums = [3,2,1,0,4]
Output: false
```

### Constraints
- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 10^5`

---

### 3.1 Greedy Reachability Approach (Optimal)
```java
/*
    APPROACH
    - Maintain `maxReach`, the farthest index reachable so far.
    - Iterate i from 0 to n-1:
        - If i > maxReach, return false.
        - Update maxReach = max(maxReach, i + nums[i]).
        - If maxReach >= lastIndex, return true.
    - Return true at end if reachable.

    TIME COMPLEXITY: O(N), single pass
    SPACE COMPLEXITY: O(1)
*/
class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) return true;
        }
        return true;
    }
}
```

---

<a id="jump-game-ii"></a>
## 4. Jump Game II [[Back to Top](#top)]

### Problem Statement  
Given an array of non-negative integers `nums`, each element represents your maximum jump length at that position. Return the minimum number of jumps required to reach the last index.  
_Link:_ https://leetcode.com/problems/jump-game-ii/

### Examples
```txt
Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: Jump from index 0 to 1, then to last index.
```
```txt
Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
```

### Constraints
- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 10^5`

---

### 4.1 Greedy Level-by-Level Jump (Optimal)
```java
/*
    APPROACH
    - Track `currentEnd` (end of current jump range) and `farthest` reachable.
    - Iterate i from 0 to n-2:
        - Update farthest = max(farthest, i + nums[i]).
        - If i == currentEnd: increment jumps, set currentEnd = farthest.
    - Return jump count.

    TIME COMPLEXITY: O(N), single pass
    SPACE COMPLEXITY: O(1)
*/
class Solution {
    public int jump(int[] nums) {
        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
}
```