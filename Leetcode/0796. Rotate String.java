/*
    APPROACH - BRUTE FORCE
    1. Check if the lengths of both strings are different. If yes, return false.
    2. Iterate through the string and generate all possible rotations.
    3. Compare each rotated string with the goal string.
    4. If a match is found, return true.
    5. If no match is found after all rotations, return false.

    TIME COMPLEXITY
    - O(N^2), where N is the length of the string. Creating substrings takes O(N), and we do this N times.

    SPACE COMPLEXITY
    - O(N), due to creating new substring copies in each iteration.
*/

class Solution {
    public boolean rotateString(String s, String goal) {
        // If lengths don't match, rotation is not possible
        if (s.length() != goal.length()) {
            return false;
        }

        // Generate all possible rotations and compare with goal
        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i + 1) + s.substring(0, i + 1);
            if (rotated.equals(goal)) {
                return true;
            }
        }
        return false;
    }
}


/*
    APPROACH - OPTIMIZED
    1. Check if lengths are different; if yes, return false.
    2. Concatenate s with itself.
    3. If goal is a substring of this concatenated string, return true.
    4. Otherwise, return false.

    TIME COMPLEXITY
    - O(N), since checking for a substring takes O(N).

    SPACE COMPLEXITY
    - O(N), for storing the concatenated string.
*/

class Solution {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
