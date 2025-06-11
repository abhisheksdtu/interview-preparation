/*
    APPROACH - BRUTE FORCE
    1. Use four nested loops to check all possible quadruplets.
    2. If the sum of four numbers equals the target, store the quadruplet.
    3. Use a Set to avoid duplicate quadruplets by sorting and adding to the Set.
    4. Convert the Set to a List and return it.

    TIME COMPLEXITY
    - O(N^4), since there are four nested loops.

    SPACE COMPLEXITY
    - O(N), for storing unique quadruplets in a Set.
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> uniqueQuadruplets = new HashSet<>();
        int n = nums.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n - 1; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> quadruplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                            Collections.sort(quadruplet);
                            uniqueQuadruplets.add(quadruplet);
                        }
                    }
                }
            }
        }
        
        result.addAll(uniqueQuadruplets);
        return result;
    }
}

/*
    APPROACH - HASHSET TO AVOID DUPLICATES
    1. Use two nested loops to fix the first two numbers.
    2. Use a HashSet to store numbers seen so far for the third and fourth numbers.
    3. Calculate the required fourth number and check if it exists in the HashSet.
    4. If found, add the quadruplet to the Set to avoid duplicates.
    5. Convert the Set to a List and return it.

    TIME COMPLEXITY
    - O(N^3), since there are three nested loops and a HashSet lookup (O(1)).

    SPACE COMPLEXITY
    - O(N), for storing unique quadruplets in a Set.
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> uniqueQuadruplets = new HashSet<>();
        int n = nums.length;
        
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                Set<Integer> seenNumbers = new HashSet<>();
                
                for (int k = j + 1; k < n; k++) {
                    int requiredNumber = target - (nums[i] + nums[j] + nums[k]);
                    
                    if (seenNumbers.contains(requiredNumber)) {
                        List<Integer> quadruplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], requiredNumber));
                        Collections.sort(quadruplet);
                        uniqueQuadruplets.add(quadruplet);
                    }
                    
                    seenNumbers.add(nums[k]);
                }
            }
        }
        
        return new ArrayList<>(uniqueQuadruplets);
    }
}

/*
    APPROACH - OPTIMIZED (Two Pointers)
    1. Sort the array to make it easier to avoid duplicates and use two-pointer technique.
    2. Use a nested loop to fix the first two numbers and find the other two using two-pointer approach.
    3. If the sum of four numbers matches the target, add them to the result list.
    4. Move pointers accordingly and skip duplicate values to avoid duplicate quadruplets.
    5. Return the list of unique quadruplets.

    TIME COMPLEXITY
    - O(N^3), since we use two loops and a two-pointer approach inside.

    SPACE COMPLEXITY
    - O(1), ignoring the space required for the output list.
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        // Sort the array to handle duplicates and use two-pointer efficiently
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates
            
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates
                
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Move both pointers and skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // Increase sum
                    } else {
                        right--; // Decrease sum
                    }
                }
            }
        }
        return result;
    }
}
