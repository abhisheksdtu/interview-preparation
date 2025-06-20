/*
    BRUTE FORCE APPROACH
    - For each element, check every following element to see if it matches.
    - If a duplicate is found and their indices are at most k apart, return true.
    - If no such pair is found after checking all, return false.

    TIME: O(N^2)  // Two nested loops for all pairs
    SPACE: O(1)   // No extra space used
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[i] && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }
}


/*
    OPTIMAL APPROACH
    - Use a map to store each number and its latest index.
    - For each element:
        - If the number is already in the map and the distance between indices is ≤ k, return true.
        - Otherwise, update the index of the number in the map.
    - Return false if no such pair is found.

    TIME: O(N)   // Scan through the array once
    SPACE: O(N)  // Map can store up to N elements
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> numToLastIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToLastIndex.containsKey(nums[i]) && Math.abs(numToLastIndex.get(nums[i]) - i) <= k) {
                return true;
            } else {
                numToLastIndex.put(nums[i], i);
            }
        }
        return false;
    }
}
