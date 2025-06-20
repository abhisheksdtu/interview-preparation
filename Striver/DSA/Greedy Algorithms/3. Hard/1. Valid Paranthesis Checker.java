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
