/*
    APPROACH - CHARACTER MAPPING USING ARRAYS
    1. If lengths of `s` and `t` are different, return false.
    2. Use two integer arrays (`ss` and `tt`) of size 256 to track character mappings.
    3. Traverse the strings:
       - If the last seen positions of `s[i]` and `t[i]` don't match, return false.
       - Update the last seen position of both characters.
    4. If we complete the traversal without issues, return true.

    TIME COMPLEXITY
    - O(N), where N is the length of the strings. We traverse the strings once.

    SPACE COMPLEXITY
    - O(1), since we use two fixed-size arrays of 256 characters (constant space).
*/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        // If lengths differ, return false
        if (s.length() != t.length()) {
            return false;
        }

        int ss[] = new int[256]; // Stores last seen index for characters in s
        int tt[] = new int[256]; // Stores last seen index for characters in t

        // Traverse both strings
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            // If characters have different mappings, return false
            if (ss[a] != tt[b]) {
                return false;
            }

            // Store the last seen index (+1 to avoid default 0 conflict)
            ss[a] = i + 1;
            tt[b] = i + 1;
        }
        return true;
    }
}

/*
    APPROACH - HASHMAP CHARACTER MAPPING
    1. If the lengths of `s` and `t` are different, return false.
    2. Use two HashMaps:
       - One to store `s[i] → t[i]` mapping.
       - Another to store `t[i] → s[i]` mapping.
    3. Traverse the strings:
       - If `s[i]` is mapped to a different character, return false.
       - If `t[i]` is mapped to a different character, return false.
       - Otherwise, update both mappings.
    4. If traversal completes, return true.

    TIME COMPLEXITY
    - O(N), where N is the length of the strings.

    SPACE COMPLEXITY
    - O(1), since at most 26 character mappings are stored (constant space).
*/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            // If mapping already exists, check if it's valid
            if (mapST.containsKey(a) && mapST.get(a) != b) {
                return false;
            }
            if (mapTS.containsKey(b) && mapTS.get(b) != a) {
                return false;
            }

            // Store the mapping
            mapST.put(a, b);
            mapTS.put(b, a);
        }
        return true;
    }
}
