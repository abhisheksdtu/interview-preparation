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

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> freq = new HashMap<>();

        for(int num:nums){
            freq.put(num,freq.getOrDefault(num,0)+1);
        }

        int maxNum = nums[0];
        int maxFreq=1;

        for(Map.Entry<Integer,Integer> entry:freq.entrySet()){
            if(entry.getValue()>maxFreq){
                maxFreq=entry.getValue();
                maxNum=entry.getKey();
            }
        }

        return maxNum;
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
