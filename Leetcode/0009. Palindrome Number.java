/*
    APPROACH
    1. If the number is negative, it cannot be a palindrome, so return false immediately.
    2. Reverse the digits of the number by repeatedly taking the last digit and appending it to a new number (rev).
    3. Compare the original number with the reversed number. If they are the same, the number is a palindrome.
    4. Return true if the original number equals the reversed number, otherwise return false.

    TIME COMPLEXITY
    - O(d), where d is the number of digits in the input number. We traverse each digit once while reversing the number.

    SPACE COMPLEXITY
    - O(1), as we only use a constant amount of extra space for the reversed number and other variables.
*/

class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers cannot be palindromes
        if (x < 0) {
            return false;
        }

        int rev = 0;
        int n = x;

        // Reverse the number
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }

        // Check if the original number and reversed number are the same
        return x == rev;
    }
}
