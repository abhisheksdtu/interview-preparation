/*
    APPROACH
    1. Convert each word (firstWord, secondWord, targetWord) into its corresponding numerical value:
       - Each character a to j is mapped to a digit from 0 to 9.
       - For example, the word "acb" would be converted to "021".
    2. Convert the string representations of the numerical values to integers.
    3. Check if the sum of the numerical values of the first two words equals the numerical value of the target word.

    TIME COMPLEXITY
    - O(n), where n is the total number of characters in all three words combined. We process each character once.

    SPACE COMPLEXITY
    - O(1), apart from storing intermediate strings, since we use constant space for computations.

*/

class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        // Convert words to their numerical values and compare their sums
        int firstNumericalValue = Integer.parseInt(convertToNumericalValue(firstWord));
        int secondNumericalValue = Integer.parseInt(convertToNumericalValue(secondWord));
        int targetNumericalValue = Integer.parseInt(convertToNumericalValue(targetWord));

        return firstNumericalValue + secondNumericalValue == targetNumericalValue;
    }

    // Function to convert a word to its numerical value based on character positions
    public String convertToNumericalValue(String word) {
        StringBuilder numericalValue = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // Convert character to its corresponding value
            numericalValue.append(ch - 'a');
        }
        return numericalValue.toString();
    }
}

/*
    APPROACH
    1. Convert the firstWord, secondWord, and targetWord to their respective integer values.
    2. Return true if the sum of firstWord and secondWord's numerical values equals the targetWord's numerical value, otherwise return false.

    TIME COMPLEXITY
    - O(n), where n is the total number of characters in the three words. Each word is processed once.

    SPACE COMPLEXITY
    - O(1), since only a constant amount of extra space is used.

*/

class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        // Convert words to their numerical values and compare their sums
        return getIntValue(firstWord) + getIntValue(secondWord) == getIntValue(targetWord);
    }

    // Function to convert a word to its numerical value based on character positions
    public int getIntValue(String word) {
        int value = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            value = value * 10 + (ch - 'a');  // Convert character to its corresponding value
        }
        return value;
    }
}
