/*
    APPROACH
    1. Use a stack to track opening brackets.
    2. Traverse the string:
        - Push opening brackets ('(', '[', '{') onto the stack.
        - For closing brackets, check:
            a. If the stack is empty, return false (no matching opening bracket).
            b. If the top of the stack matches the closing bracket, pop it.
            c. If it doesn't match, return false.
    3. After traversal, if the stack is empty, the string is valid. Otherwise, it's invalid.

    TIME COMPLEXITY
    - O(n), where n is the length of the string. Each character is processed once.

    SPACE COMPLEXITY
    - O(n), in the worst case, all characters could be opening brackets and stored in the stack.
*/

class Solution {
    public boolean isValid(String s) {
        // Stack to store opening brackets
        Stack<Character> stack = new Stack<>();

        // Traverse each character in the string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Push opening brackets onto the stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                // If stack is empty, no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                // Check if the top of the stack matches the closing bracket
                if ((stack.peek() == '(' && ch == ')') ||
                    (stack.peek() == '[' && ch == ']') ||
                    (stack.peek() == '{' && ch == '}')) {
                    stack.pop(); // Valid match, pop from stack
                } else {
                    return false; // Mismatch, invalid
                }
            }
        }

        // If stack is empty, all brackets matched
        return stack.isEmpty();
    }
}
