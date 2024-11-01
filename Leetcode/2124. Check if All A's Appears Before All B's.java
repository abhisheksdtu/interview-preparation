/*
    APPROACH
    1. Traverse the string `s` from the second character to the end.
    2. If at any position the character 'a' appears after a 'b', the string does not meet the condition, so return false.
    3. If we reach the end without finding 'a' after 'b', then all 'a's appear before all 'b's, so return true.

    TIME COMPLEXITY
    - O(n), where n is the length of the string. We traverse the string once.

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space for the loop.
*/

class Solution {
    public boolean checkString(String s) {
        // Traverse the string to check if any 'a' appears after 'b'
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 'a' && s.charAt(i - 1) == 'b') {
                return false;  // Found an 'a' after a 'b', return false
            }
        }
        return true;  // All 'a's appear before all 'b's
    }
}
