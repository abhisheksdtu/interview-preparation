/*
    APPROACH - BRUTE FORCE
    1. Use three nested loops to generate all possible triplets.
    2. Check if the sum of the three numbers equals zero.
    3. If yes, add the triplet to a Set to avoid duplicates.
    4. Convert the Set to a List and return it.

    TIME COMPLEXITY
    - O(N^3), since we use three nested loops to check all triplets.

    SPACE COMPLEXITY
    - O(N), for storing unique triplets in a Set.
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> uniqueTriplets = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                        Collections.sort(triplet);
                        uniqueTriplets.add(triplet);
                    }
                }       
            }
        }
        
        result.addAll(uniqueTriplets);
        return result;        
    }
}


/*
    APPROACH - HASHSET OPTIMIZATION
    1. Use a nested loop to fix the first two numbers.
    2. Compute the required third number to sum to zero.
    3. Use a HashSet to check if the third number exists.
    4. If found, add the sorted triplet to a Set to avoid duplicates.
    5. Convert the Set to a List and return it.

    TIME COMPLEXITY
    - O(N^2), since we use two loops and a HashSet lookup (O(1)).

    SPACE COMPLEXITY
    - O(N), for storing unique triplets in a Set.
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> uniqueTriplets = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> seenNumbers = new HashSet<>();
            
            for (int j = i + 1; j < nums.length; j++) {
                int requiredNumber = -1 * (nums[i] + nums[j]);
                
                if (seenNumbers.contains(requiredNumber)) {
                    List<Integer> triplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], requiredNumber));
                    Collections.sort(triplet);
                    uniqueTriplets.add(triplet);
                }
                
                seenNumbers.add(nums[j]);
            }
        }
        
        result.addAll(uniqueTriplets);
        return result;
    }
}

/*
    APPROACH - TWO POINTERS AFTER SORTING
    1. Sort the array to make it easier to handle duplicates and use two-pointer technique.
    2. Iterate through the array and fix the first number.
    3. Use two pointers (one starting after the fixed number and one at the end) to find pairs that sum to zero.
    4. If the sum is less than zero, move the left pointer right; if greater, move the right pointer left.
    5. If a triplet is found, add it to the result list and adjust pointers to skip duplicates.
    6. Continue until all unique triplets are found.

    TIME COMPLEXITY
    - O(N^2), since sorting takes O(N log N) and the two-pointer approach runs in O(N) for each element.

    SPACE COMPLEXITY
    - O(1), ignoring the output list.
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        
        return result;
    }
}