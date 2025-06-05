/*
    APPROACH - BRUTE FORCE
    1. Iterate through the array with two nested loops.
    2. For each element, check if there exists another element that sums up to the target.
    3. If found, return their indices as an array.
    4. If no such pair is found, return an empty array.

    TIME COMPLEXITY
    - O(N^2), since we use two nested loops to check all pairs.

    SPACE COMPLEXITY
    - O(1), since we only use a constant amount of extra space.
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int requiredNumber = target - nums[i];
                
                if (nums[j] == requiredNumber) {
                    return new int[]{i, j};
                }
            }
        }
        
        return new int[0];
    }
}


/*
    APPROACH - HASHMAP OPTIMIZATION
    1. Use a HashMap to store the elements and their indices.
    2. Iterate through the array and compute the required number for each element.
    3. Check if the required number exists in the HashMap and ensure it's not the same index.
    4. If found, return their indices.
    5. If no pair is found, return an empty array.

    TIME COMPLEXITY
    - O(N), since we traverse the array twice (once for storing and once for searching).

    SPACE COMPLEXITY
    - O(N), since we store elements in a HashMap.
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        // Store elements in HashMap with their indices
        for (int i = 0; i < nums.length; i++) {
            numToIndex.put(nums[i], i);
        }
        
        // Find the required pair
        for (int i = 0; i < nums.length; i++) {
            int requiredNumber = target - nums[i];
            
            if (numToIndex.containsKey(requiredNumber) && numToIndex.get(requiredNumber) != i) {
                return new int[]{i, numToIndex.get(requiredNumber)};
            }
        }
        
        return new int[0];
    }
}
