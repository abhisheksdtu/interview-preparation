# Problem Solutions

## Table of Contents
1. [Assign Cookies](#assign-cookies)
2. [Lemonade Change](#lemonade-change)
3. [Jump Game I](#jump-game-i)

---

## Assign Cookies
**Problem:**  
You are given two integer arrays `g` (children's greed factors) and `s` (cookie sizes). Each child `i` is content if assigned a cookie of size ≥ `g[i]`. A cookie can be given to at most one child. Maximize the number of content children.

**Constraints:**  
- 1 ≤ g.length, s.length ≤ 10⁴  
- 1 ≤ g[i], s[j] ≤ 10⁵  

**Approach (Greedy Two-Pointer):**
```java
import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        // Sort both greed and cookie arrays for optimal matching
        Arrays.sort(g);
        Arrays.sort(s);
        // child: pointer for g, cookie: pointer for s
        int child = 0, cookie = 0;
        // Assign smallest sufficient cookie to each child
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                child++;
                cookie++;
            } else {
                cookie++;
            }
        }
        return child;
    }
}
```

---

## Lemonade Change
**Problem:**  
At a lemonade stand, each glass costs \$5. Customers pay with \$5, \$10, or \$20 bills in sequence given by array `bills`. You must provide exact change to each customer. Return `true` if you can serve all customers, otherwise `false`.

**Constraints:**  
- 0 ≤ bills.length ≤ 10⁵  
- bills[i] ∈ {5, 10, 20}  

**Approach (Simulation with Counters):**
```java
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        // Track counts of $5 and $10 bills in hand
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                // Need one $5 for change
                if (five == 0) return false;
                five--;
                ten++;
            } else { // bill == 20
                // Prefer to give one $10 and one $5
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    // Otherwise give three $5 bills
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

## Jump Game I
**Problem:**  
Given an array `nums` where `nums[i]` represents the maximum jump length from index `i`, determine if you can reach the last index starting from index `0`.

**Constraints:**  
- 1 ≤ nums.length ≤ 10⁴  
- 0 ≤ nums[i] ≤ 10⁵  

**Approach (Greedy Max Reach):**
```java
public class JumpGameI {
    public boolean canJump(int[] nums) {
        // Track the furthest index reachable so far
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            // If current index is beyond reachable, you fail
            if (i > reachable) {
                return false;
            }
            // Update max reachable index
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
}
```
