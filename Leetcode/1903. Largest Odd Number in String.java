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

// -------------------- IF WE HAVE TO REMOVE STARTING ZEROS -------------------- 

/*
    APPROACH
    1. Traverse the string from right to left to find the index of the last odd digit.
    2. If no odd digit is found, return an empty string.
    3. Remove leading zeros from the substring up to the last odd digit.
    4. Return the resulting substring as the largest odd number.

    TIME COMPLEXITY
    - O(n), where n is the length of the string. Each character is processed at most once.

    SPACE COMPLEXITY
    - O(1), as no additional data structures are used.
*/

class Solution {
    public String largestOddNumber(String num) {
        int idx = -1;

        // Traverse from right to find the last odd digit
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 != 0) {
                idx = i;
                break;
            }
        }

        // If no odd digit is found, return empty string
        if (idx == -1) {
            return "";
        }

        // Remove leading zeros up to the index of the last odd digit
        int i = 0;
        while (i <= idx && num.charAt(i) == '0') {
            i++;
        }

        // Return the substring from the first non-zero to the last odd digit
        return num.substring(i, idx + 1);
    }
}
