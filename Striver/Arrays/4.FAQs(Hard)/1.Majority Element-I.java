/*
    APPROACH - BRUTE FORCE
    1. Iterate through the array and pick each element as a candidate majority element.
    2. Count its occurrences by iterating through the array again.
    3. If the count exceeds half of the array length, return the element.
    4. If no majority element is found, return -1.

    TIME COMPLEXITY
    - O(N^2), due to the nested loop iterating through the array.

    SPACE COMPLEXITY
    - O(1), since no extra space is used.
*/

class Solution {
    public int majorityElement(int[] nums) {
        for (int candidate : nums) {
            int count = 0;
            
            for (int num : nums) {
                if (num == candidate) {
                    count++;
                }
            }
            
            if (count > nums.length / 2) {
                return candidate;
            }
        }
        
        return -1;
    }
}

/*
    APPROACH - HASHMAP OPTIMIZATION
    1. Use a HashMap to store the frequency of each element while iterating through the array.
    2. If the frequency of an element exceeds half of the array length, return that element immediately.
    3. If no majority element is found (though in valid cases one always exists), return -1.

    TIME COMPLEXITY
    - O(N), since we traverse the array once and perform O(1) operations for each element.

    SPACE COMPLEXITY
    - O(N), since we store the frequency of each unique element in the HashMap.
*/

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Count occurrences and check majority condition on the fly
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            if (frequencyMap.get(num) > nums.length / 2) {
                return num;
            }
        }
        
        return -1;
    }
}

/*
    APPROACH - BOYER-MOORE VOTING ALGORITHM
    1. Initialize a candidate element and a count variable.
    2. Traverse the array:
       - If the count is zero, set the current element as the candidate.
       - If the current element matches the candidate, increase the count.
       - Otherwise, decrease the count.
    3. Validate the candidate by counting its occurrences in a second pass.
    4. If the count exceeds half of the array length, return the candidate.
    5. Otherwise, return -1 (though in valid cases, a majority element is guaranteed to exist).

    TIME COMPLEXITY
    - O(N), since we traverse the array twice.

    SPACE COMPLEXITY
    - O(1), as we use only a few extra variables.
*/

class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;
        
        // First pass: Find the candidate element
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if(num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // Second pass: Validate the candidate
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
            if (count > nums.length / 2) {
                return candidate;
            }
        }
        
        return -1;
    }
}
