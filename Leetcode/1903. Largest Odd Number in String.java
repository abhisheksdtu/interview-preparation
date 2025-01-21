/*
    APPROACH
    1. Remove leading zeros from the input string using `replaceFirst("^0+", "")`.
    2. Traverse the string from the rightmost character (least significant digit) to the left:
        - If a character represents an odd digit, return the substring from the start to this position (inclusive).
    3. If no odd digit is found, return an empty string.

    TIME COMPLEXITY
    - O(n), where n is the length of the string. We traverse the string once.

    SPACE COMPLEXITY
    - O(1), as no additional data structures are used apart from variables.

*/

class Solution {
    public String largestOddNumber(String num) {
        // Remove leading zeros, as they do not affect the value of the number
        num = num.replaceFirst("^0+", "");

        // Traverse the string from right to left
        for (int i = num.length() - 1; i >= 0; i--) {
            // Check if the current character is an odd digit
            if ((num.charAt(i) - '0') % 2 != 0) {
                // Return the substring up to the current position (inclusive)
                return num.substring(0, i + 1);
            }
        }

        // If no odd digit is found, return an empty string
        return "";
    }
}
