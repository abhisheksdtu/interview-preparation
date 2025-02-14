/*
    APPROACH - BRUTE FORCE
    1. Iterate through the array, treating each element as the start of a sequence.
    2. Use a helper function (linear search) to check for consecutive elements.
    3. Count the length of the sequence and update the longest streak.

    TIME COMPLEXITY
    - O(N^2), due to the nested loop caused by linear search for each element.

    SPACE COMPLEXITY
    - O(1), since no extra space is used.
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        int longestStreak = 0;
        for (int num : nums) {
            int count = 1;
            int currentNum = num;
            while (linearSearch(nums, currentNum + 1)) {
                currentNum++;
                count++;
            }
            longestStreak = Math.max(count, longestStreak);
        }
        return longestStreak;
    }

    public boolean linearSearch(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}

/*
    APPROACH - SORTING BASED SOLUTION
    1. Sort the array to bring consecutive elements together.
    2. Iterate through the sorted array while tracking the longest sequence.
    3. Ignore duplicate elements and reset the count when a break is found.

    TIME COMPLEXITY
    - O(N log N), due to sorting.

    SPACE COMPLEXITY
    - O(1), since sorting is done in-place.
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int longestStreak = 0;
        int previousNum = Integer.MIN_VALUE;
        int count = 0;

        for (int num : nums) {
            if (num == previousNum) continue;
            if (num - 1 == previousNum) {
                count++;                
            } else {
                count = 1;
            }
            previousNum = num;
            longestStreak = Math.max(count, longestStreak);
        }
        return longestStreak;
    }
}

/*
    APPROACH - HASHSET OPTIMIZATION
    1. Store all numbers in a HashSet for O(1) lookups.
    2. Iterate through the HashSet and start counting sequences only from numbers that don’t have a predecessor.
    3. Count the length of the sequence and update the longest streak.

    TIME COMPLEXITY
    - O(N), since each element is processed only once.

    SPACE COMPLEXITY
    - O(N), since we store elements in a HashSet.
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        int longestStreak = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int count = 1;
                int currentNum = num;
                
                while (numSet.contains(currentNum + 1)) {
                    count++;
                    currentNum++;
                }
                longestStreak = Math.max(count, longestStreak);
            }
        }
        return longestStreak;
    }
}
