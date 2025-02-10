/*
    APPROACH - HASHMAP COUNTING
    1. If lengths of both strings are different, return false.
    2. Use a HashMap to store the frequency of each character in the first string.
    3. Traverse the second string:
       - If a character is missing in the map or its count is zero, return false.
       - Otherwise, decrement the count of that character.
    4. If all characters are accounted for, return true.

    TIME COMPLEXITY
    - O(N), where N is the length of the string. Traversing both strings takes O(N).

    SPACE COMPLEXITY
    - O(1), since the map stores at most 26 characters (constant space).
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths are different, not an anagram
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        // Count frequency of characters in s
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Check characters in t
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                return false;
            }
            map.put(ch, map.get(ch) - 1);
        }
        return true;
    }
}

/*
    APPROACH - ARRAY COUNTING
    1. If the lengths of both strings are different, return false.
    2. Use an integer array of size 26 to store character frequencies.
    3. Traverse both strings simultaneously:
       - Increment count for characters in `s`.
       - Decrement count for characters in `t`.
    4. After traversal, check if all values in the array are zero.
       - If any value is nonzero, return false.
       - Otherwise, return true.

    TIME COMPLEXITY
    - O(N), where N is the length of the string. We traverse both strings once.

    SPACE COMPLEXITY
    - O(1), since we use a fixed-size array of 26 characters (constant space).
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths differ, return false
        if (s.length() != t.length()) {
            return false;
        }

        int freq[] = new int[26];

        // Count character frequency in s and t simultaneously
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            freq[c - 'a']++; // Increment for s
            freq[d - 'a']--; // Decrement for t
        }

        // Check if all frequencies are zero
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
