/*
    APPROACH - BRUTE FORCE
    1. Iterate through the array and consider each element as a candidate.
    2. Use a HashSet to track processed candidates to avoid duplicate counts.
    3. Count occurrences of each candidate in a nested loop.
    4. If the count exceeds ⌊N/3⌋, add it to the result list.
    5. Stop once we find at most two majority elements.
    6. Sort the result list before returning.

    TIME COMPLEXITY
    - O(N^2), due to the nested loop iterating through the array.

    SPACE COMPLEXITY
    - O(N), for storing processed elements in a HashSet.
*/

class Solution {
    public List<Integer> majorityElementTwo(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int size = nums.length;
        Set<Integer> processed = new HashSet<>();

        for (int candidate : nums) {
            if (processed.contains(candidate)) {
                continue;
            }
            
            int count = 0;
            for (int num : nums) {
                if (num == candidate) {
                    count++;
                }
            }

            if (count > size / 3) {
                result.add(candidate);
                processed.add(candidate);
            }

            if (result.size() == 2) {
                break;
            }
        }

        Collections.sort(result);
        return result;
    }
}

/*
    APPROACH - HASHMAP OPTIMIZATION
    1. Use a HashMap to store the frequency of each element while iterating through the array.
    2. Traverse the HashMap to find elements with frequency greater than ⌊N/3⌋.
    3. Add such elements to the result list.
    4. Sort the result list before returning.

    TIME COMPLEXITY
    - O(N log N), due to sorting the result list, where N is the number of elements.
    - O(N), for building the HashMap and filtering elements.

    SPACE COMPLEXITY
    - O(N), since we store the frequency of each unique element in the HashMap.
*/

class Solution {
    public List<Integer> majorityElementTwo(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count occurrences of each element
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int threshold = nums.length / 3;
        frequencyMap.forEach((key, value) -> {
            if (value > threshold) {
                result.add(key);
            }
        });

        // Sort the result list
        Collections.sort(result);
        return result;
    }
}

/*
    APPROACH - BOYER-MOORE VOTING ALGORITHM (EXTENDED)
    1. Initialize two potential majority element candidates and their respective counts.
    2. Traverse the array:
       - If a candidate count is zero, assign the current number as a candidate.
       - If the current number matches a candidate, increase its count.
       - Otherwise, decrement both candidate counts.
    3. Perform a second pass to verify the actual count of the candidates.
    4. If a candidate appears more than ⌊N/3⌋ times, add it to the result.
    5. Sort the result before returning.

    TIME COMPLEXITY
    - O(N), since we traverse the array twice.

    SPACE COMPLEXITY
    - O(1), as only a few extra variables are used.
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int ele1 = 0, ele2 = 0, count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == ele1) {
                count1++;
            } else if (num == ele2) {
                count2++;
            } else if (count1 == 0) {
                ele1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                ele2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == ele1) count1++;
            else if (num == ele2) count2++; 
        }

        int n = nums.length;
        if (count1 > n / 3) result.add(ele1);
        if (count2 > n / 3) result.add(ele2);

        // Sort the result list
        Collections.sort(result);
        return result;
    }
}