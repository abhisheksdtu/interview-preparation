/*
    APPROACH
    1. First, check if the lengths of s1 and s2 are the same. If they are not, return false immediately.
    2. If s1 and s2 are already equal, return true, as no swaps are needed.
    3. Traverse both strings and record the indices where the characters differ.
    4. If there are no mismatches, the strings are already equal, so return true.
    5. If there are exactly two mismatches, check if swapping the characters at these indices in one string would make the strings equal. If yes, return true.
    6. If there are more than two mismatches or the swap does not fix the strings, return false.

    TIME COMPLEXITY
    - O(n), where n is the length of the strings. We traverse both strings once to find mismatches.

    SPACE COMPLEXITY
    - O(1), since we use a constant amount of extra space to store the mismatch indices (at most two mismatches).

*/

class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        List<Integer> mismatchIndex = new ArrayList<>();

        // Find all mismatched indices
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                mismatchIndex.add(i);
            }
        }

        // If there are no mismatches, return true
        if (mismatchIndex.size() == 0) {
            return true;
        }

        // If there are exactly two mismatches, check if swapping them makes the strings equal
        if (mismatchIndex.size() == 2) {
            int i = mismatchIndex.get(0);
            int j = mismatchIndex.get(1);
            return s1.charAt(i) == s2.charAt(j) && s1.charAt(j) == s2.charAt(i);
        }

        // If there are more than two mismatches, return false
        return false;
    }
}

/*
    LESS LINES CODE WITH SAME APPROACH
*/
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) l.add(i);
			if (l.size() > 2) return false; // added this line to short circuit the loop
        }
        return l.size() == 0 || 
            (l.size() == 2 && s1.charAt(l.get(0)) == s2.charAt(l.get(1)) && s1.charAt(l.get(1)) == s2.charAt(l.get(0)));
    }
}