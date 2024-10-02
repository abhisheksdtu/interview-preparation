/*
    APPROACH
    1. Initialize a variable rev to store the reversed digits.
    2. Extract the last digit from the input number x using modulus operation and append it to rev.
    3. Repeat the process by dividing x by 10 until x becomes 0.
    4. After reversing the number, check if the result exceeds the 32-bit integer range.
    5. If it exceeds the range, return 0; otherwise, return the reversed number.

    TIME COMPLEXITY
    - O(log(x)), where x is the input number. The number of digits in the number is proportional to log(x).

    SPACE COMPLEXITY
    - O(1), since we are using a constant amount of extra space.
*/

class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }

        long rev = 0;

        // Reverse the digits of the number
        while (x != 0) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }

        // Check if the reversed number is within the 32-bit integer range
        if (rev < Integer.MIN_VALUE || rev > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) rev;
    }
}
