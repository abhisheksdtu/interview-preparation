/*
    APPROACH - BRUTE FORCE
    1. Iterate through the array and consider each element as a candidate.
    2. Count occurrences of each candidate in a nested loop.
    3. If the count exceeds ⌊N/2⌋, return the candidate.
    4. Return -1 (though per problem constraints, this case won’t occur).

    TIME COMPLEXITY
    - O(N^2), due to the nested loop iterating through the array.

    SPACE COMPLEXITY
    - O(1), as no extra space is used.
*/

class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int candidate = nums[i];
            int count = 0;

            // Count occurrences of candidate
            for (int j = 0; j < n; j++) {
                if (nums[j] == candidate) {
                    count++;
                }
            }

            if (count > n / 2) {
                return candidate;
            }
        }

        return -1;
    }
}

/*
    APPROACH - HASHMAP OPTIMIZATION
    1. Use a HashMap to store the frequency of each element while iterating through the array.
    2. If the frequency of an element exceeds ⌊N/2⌋, return it immediately.

    TIME COMPLEXITY
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY
    - O(N), as we store the frequency of each unique element in the HashMap.
*/

import java.util.*;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int threshold = nums.length / 2;

        for (int num : nums) {
            int count = frequencyMap.getOrDefault(num, 0) + 1;
            if (count > threshold) {
                return num;
            }
            frequencyMap.put(num, count);
        }

        return -1;
    }
}

/*
    APPROACH - BOYER-MOORE VOTING ALGORITHM
    1. Initialize a candidate and count variable.
    2. Traverse the array:
       - If count is zero, set the current number as the candidate.
       - If the current number matches the candidate, increase the count.
       - Otherwise, decrease the count.
    3. The candidate at the end is the majority element.

    TIME COMPLEXITY
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY
    - O(1), as only a few extra variables are used.
*/

class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0], count = 0;

        // Boyer-Moore Voting Algorithm to find majority candidate
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
