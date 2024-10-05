/*
    APPROACH
    1. Use two pointers (lo starting from the beginning and hi from the end) to traverse the string.
    2. Skip non-alphanumeric characters for both pointers.
    3. Compare the characters at the lo and hi positions after converting them to lowercase.
    4. If the characters are not the same, return false (the string is not a palindrome).
    5. If all valid characters match, return true (the string is a palindrome).

    TIME COMPLEXITY
    - O(n), where n is the length of the string. We traverse the string once with two pointers.

    SPACE COMPLEXITY
    - O(1), as we only use a constant amount of extra space for the pointers.

*/

class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }

        int lo = 0;
        int hi = s.length() - 1;

        // Use two pointers to check for palindrome
        while (lo < hi) {
            char leftChar = s.charAt(lo);
            char rightChar = s.charAt(hi);

            // Skip non-alphanumeric characters from the left
            if (!isAlphaNumericCharacter(leftChar)) {
                lo++;
            }
            // Skip non-alphanumeric characters from the right
            else if (!isAlphaNumericCharacter(rightChar)) {
                hi--;
            }
            // Compare characters after converting to lowercase
            else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;  // Not a palindrome
                }
                lo++;
                hi--;
            }
        }

        return true;  // Palindrome
    }

    // Helper function to check if a character is alphanumeric
    public boolean isAlphaNumericCharacter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }
}

/*
    APPROACH
    1. Convert the string to lowercase to make the comparison case-insensitive.
    2. Remove all non-alphanumeric characters from the string using regex.
    3. Use two pointers, lo and hi, to check if the string is a palindrome:
       - Start with lo pointing to the beginning of the string and hi pointing to the end.
       - Compare the characters at lo and hi.
       - If the characters don't match, return false. If they match, move lo forward and hi backward.
    4. If all the characters match, the string is a palindrome, so return true.

    TIME COMPLEXITY
    - O(n), where n is the length of the string. We traverse the string once for conversion, removal of non-alphanumeric characters, and palindrome checking.

    SPACE COMPLEXITY
    - O(n), since we create a new string after removing non-alphanumeric characters.

*/

class Solution {
    public boolean isPalindrome(String s) {
        // Convert the string to lowercase and remove all non-alphanumeric characters
        s = s.toLowerCase();
        s = s.replaceAll("[^a-zA-Z0-9]", "");

        // Two pointers to check if the string is a palindrome
        int lo = 0;
        int hi = s.length() - 1;

        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;  // Characters don't match, not a palindrome
            }

            lo++;
            hi--;
        }

        return true;  // Palindrome
    }
}
