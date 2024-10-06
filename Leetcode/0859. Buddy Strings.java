/*
    APPROACH
    1. First, check if the lengths of s and goal are the same. If they are not, return false immediately.
    2. If s and goal are already equal, check if there are at least two duplicate characters in s. If so, return true, as we can swap the duplicate characters to make the strings "buddy strings."
    3. If s and goal are not equal, find the indices where the characters in s and goal differ.
    4. If there are exactly two mismatched indices, check if swapping the characters at these indices in s would make the strings equal. If yes, return true.
    5. If there are more than two mismatches or the swap does not fix the strings, return false.

    TIME COMPLEXITY
    - O(n), where n is the length of the strings. We traverse both strings once to find mismatches or check for duplicates.

    SPACE COMPLEXITY
    - O(n), for mismatch indices.

*/

class Solution {
    public boolean buddyStrings(String s, String goal) {
        // If the lengths are not the same, return false
        if (s.length() != goal.length()) {
            return false;
        }

        // If strings are equal, check for duplicate characters
        if (s.equals(goal)) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                freq[ch - 'a']++;
                // If any character appears twice, we can swap them to make the strings equal
                if (freq[ch - 'a'] == 2) {
                    return true;
                }
            }
            return false;
        }

        // List to store the indices where the characters differ
        List<Integer> mismatchIndices = new ArrayList<>();

        // Find mismatches between s and goal
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                mismatchIndices.add(i);
            }
        }

        // If there are exactly two mismatches, check if swapping makes the strings equal
        if (mismatchIndices.size() == 2) {
            int i = mismatchIndices.get(0);
            int j = mismatchIndices.get(1);
            return s.charAt(i) == goal.charAt(j) && s.charAt(j) == goal.charAt(i);
        }

        // If no valid swap can make the strings equal, return false
        return false;
    }
}


/*
    APPROACH - BETTER CODE
    1. If the lengths of s and goal are not the same, return false as swapping won't make them equal.
    2. If s equals goal, check if there is any character that appears at least twice in s. If so, a swap of the two identical characters can make them "buddy strings," so return true.
    3. If s does not equal goal, find all mismatched indices between the two strings.
    4. If there are exactly two mismatches, check if swapping the characters at these indices in s will make the strings equal.
    5. Return true if swapping makes the strings equal; otherwise, return false.

    TIME COMPLEXITY
    - O(n), where n is the length of the strings. We traverse the string to count character frequencies and find mismatches.

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space for storing frequencies and mismatch indices.

*/

class Solution {
    public boolean buddyStrings(String s, String goal) {
        // Step 1: Check if lengths are different
        if (s.length() != goal.length()) {
            return false;
        }

        // Step 2: If s equals goal, check for any duplicate character
        if (s.equals(goal)) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                freq[ch - 'a']++;
                if (freq[ch - 'a'] == 2) {
                    return true;  // Found a character that occurs at least twice
                }
            }
            return false;  // No duplicate characters found
        }

        // Step 3: Find mismatches between s and goal
        int x = -1, y = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (x == -1) {
                    x = i;
                } else if (y == -1) {
                    y = i;
                } else {
                    return false;  // More than two mismatches
                }
            }
        }

        // Step 4: Check if swapping the mismatched characters makes s equal to goal
        return x != -1 && y != -1 &&
               s.charAt(x) == goal.charAt(y) &&
               s.charAt(y) == goal.charAt(x);
    }
}
