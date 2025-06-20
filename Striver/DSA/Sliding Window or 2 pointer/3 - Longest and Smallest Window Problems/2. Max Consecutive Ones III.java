/*
    BRUTE APPROACH
    - For every starting index, count the longest subarray with at most k zeros.
    - Stop extending the subarray if you exceed k zeros.
    - Track and return the maximum length found.

    TIME: O(N) outer loop * O(N) inner loop = O(N^2)
      - For each starting index (N), check up to N subarray endings.
      - This results in O(N * N) = O(N^2) time.

    SPACE: O(1)
      - Only a few variables for counts and pointers.
*/
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxLength = 0;
        for (int start = 0; start < n; start++) {
            int zeros = 0;
            int currLength = 0;
            for (int end = start; end < n; end++) {
                if (nums[end] == 0) {
                    zeros++;
                    if (zeros > k) break;
                }
                currLength++;
            }
            maxLength = Math.max(maxLength, currLength);
        }
        return maxLength;
    }
}

/*
    OPTIMAL - SLIDING WINDOW
    - Use two pointers to mark the start and end of a window (left and right).
    - Move the right pointer to expand the window and count zeros inside the window.
    - If the window has more than k zeros, move the left pointer forward to remove zeros until you have at most k zeros.
    - Always keep track of the biggest window you see during this process.
    - The answer is the size of the largest window with at most k zeros.

    TIME: O(N) for right pointer + O(N) for left pointer = O(2N) = O(N)
      - Each element is processed at most twice (once by right, once by left).
      - Total operations are linear.

    SPACE: O(1)
      - Just a few variables used, no extra data structures.
*/
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int zeros = 0, maxLength = 0;
        while (right < n) {
            if (nums[right] == 0) zeros++;
            while (zeros > k) {
                if (nums[left] == 0) zeros--;
                left++;
            }
            int currLength = right - left + 1;
            maxLength = Math.max(maxLength, currLength);
            right++;
        }
        return maxLength;
    }
}

/*
    SLIDING WINDOW (EASY EXPLANATION)
    - Use two pointers to keep a window (left and right) over the array.
    - As you move the right pointer, count the number of zeros in the window.
    - If there are more than k zeros, move the left pointer right to remove zeros from the window until you have at most k zeros.
    - Only update the maximum length if the window has k or fewer zeros.
    - The answer is the largest window you can get with at most k zeros.

    TIME: O(N)   // Go through the array once
    SPACE: O(1)  // Uses only a few variables
*/
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int zeros = 0, maxLength = 0;
        while (right < n) {
            if (nums[right] == 0) zeros++;
            if (zeros > k) {
                if (nums[left] == 0) zeros--;
                left++;
            }
            if (zeros <= k) {
                int currLength = right - left + 1;
                maxLength = Math.max(maxLength, currLength);
            }
            right++;
        }
        return maxLength;
    }
}
