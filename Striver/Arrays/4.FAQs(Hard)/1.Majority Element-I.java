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
    APPROACH - HASHMAP (FREQUENCY COUNT)
    1. Use a HashMap to count the frequency of each element in the array.
    2. Iterate through the array, updating the frequency count for each element.
    3. Traverse the HashMap to find the element with the highest frequency.
    4. Return the element with the maximum count, which is the majority element.

    TIME COMPLEXITY
    - O(N), since we traverse the array once to build the HashMap and once to find the maximum frequency element.

    SPACE COMPLEXITY
    - O(N), since we store the frequency count for each unique element in the HashMap.
*/

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Count occurrences of each element
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        int majorityElement = nums[0];
        int maxFrequency = 1;
        
        // Find element with maximum frequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                majorityElement = entry.getKey();
            }
        }
        
        return majorityElement;
    }
}


class Solution {
  public int majorityElement(int[] nums) {
    int ele = nums[0];
    int c = 0;
    for (int i = 0; i < nums.length; i++) {
      if (c == 0) {
        ele = nums[i];
        c++;
      } else if (ele == nums[i]) {
        c++;
      } else {
        c--;
      }
    }

    c = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == ele) {
        c++;
      }

      if (c > nums.length / 2) {
        return ele;
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
