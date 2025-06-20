/*
    BRUTE FORCE APPROACH
    - For every starting index, check the longest substring with all unique characters.
    - Use a frequency array to track characters in the current substring.
    - Update max length found so far.

    TIME: O(N^2)      // Two nested loops over the string
    SPACE: O(1)       // Fixed-size array (256) for character frequency
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        for (int start = 0; start < n; start++) {
            int[] charCount = new int[256];
            for (int end = start; end < n; end++) {
                char ch = s.charAt(end);
                if (charCount[ch] != 0)
                    break;
                charCount[ch]++;
                maxLength = Math.max(maxLength, end - start + 1);
            }
        }
        return maxLength;
    }
}

/*
    SLIDING WINDOW OPTIMIZED
    - Use two pointers (left and right) to form a window with unique characters.
    - Track last index of each character using a fixed-size array.
    - If a character repeats within the current window, move left pointer after its last occurrence.
    - Update max length found so far.

    TIME: O(N)      // Each character processed at most twice
    SPACE: O(1)     // Fixed-size array (256) for last index
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] lastSeen = new int[256];
        Arrays.fill(lastSeen, -1);
        int left = 0;
        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            if (lastSeen[ch] >= left) {
                left = lastSeen[ch] + 1;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            lastSeen[ch] = right;
        }
        return maxLength;
    }
}
