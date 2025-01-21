/*
    APPROACH
    1. Assume the first string in the array as the potential longest common prefix.
    2. Compare this prefix with each string in the array:
        - While the current string does not start with the prefix, reduce the prefix by removing its last character.
        - If the prefix becomes empty, return an empty string as there is no common prefix.
    3. Return the remaining prefix after all comparisons.

    TIME COMPLEXITY
    - O(n * m), where n is the number of strings and m is the length of the shortest string.
      - Each comparison of `startsWith` takes O(m) in the worst case.
      - In the worst case, the prefix is reduced character by character.

    SPACE COMPLEXITY
    - O(1), as no additional space is used apart from variables.
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Initialize the prefix with the first string
        String prefix = strs[0];

        // Compare the prefix with each string in the array
        for (int i = 1; i < strs.length; i++) {
            // While the current string does not start with the prefix
            while (!strs[i].startsWith(prefix)) {
                // Reduce the prefix by removing the last character
                prefix = prefix.substring(0, prefix.length() - 1);

                // If the prefix becomes empty, return an empty string
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        // Return the final prefix
        return prefix;
    }
}
