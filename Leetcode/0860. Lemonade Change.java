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
