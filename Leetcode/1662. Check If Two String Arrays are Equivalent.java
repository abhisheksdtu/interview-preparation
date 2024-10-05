/*
    APPROACH
    1. Concatenate all the strings in the arrays `word1` and `word2` using a StringBuilder.
    2. Compare the resulting concatenated strings from both arrays.
    3. If the concatenated strings are equal, return true; otherwise, return false.

    TIME COMPLEXITY
    - O(n + m), where n is the total number of characters in `word1` and m is the total number of characters in `word2`.
      We traverse each string in both arrays once to build the final concatenated strings.

    SPACE COMPLEXITY
    - O(n + m), as we store the concatenated result of both string arrays.
*/

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // Compare the concatenated results of both string arrays
        return concatenate(word1).equals(concatenate(word2));
    }

    // Helper function to concatenate an array of strings
    public String concatenate(String[] words) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(word);  // Append each word to the StringBuilder
        }

        return sb.toString();
    }
}
