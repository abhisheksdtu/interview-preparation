/*
    BRUTE FORCE APPROACH
    - For every starting index, use a set to track fruit types in the current subarray.
    - If you ever have more than 2 types, break.
    - Track the maximum length for each starting index.

    TIME: O(N) outer loop * O(N) inner loop = O(N^2)
      - For each index, check all possible subarrays starting from there.
      - Nested loops result in quadratic time.

    SPACE: O(1) to O(N) 
      - Set holds at most 3 types at a time (so O(1) for fruit types), 
      - but in rare cases with unique fruits, could go up to O(N) (not common for this problem).

    (In interview: just say O(1) for practical input constraints.)
*/
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int max = 0;
        for(int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            int len = 0;
            for(int j = i; j < n; j++) {
                set.add(fruits[j]);
                if(set.size() > 2) break;
                len++;
            }
            max = Math.max(len, max);
        }
        return max;
    }
}

/*
    OPTIMAL - SLIDING WINDOW WITH MAP
    - Use two pointers to maintain a window with at most 2 fruit types.
    - Track frequency of each type using a map.
    - Move right pointer to include new fruits.
    - If window has more than 2 types, move left pointer and update counts.
    - Always keep track of the maximum window size.

    TIME: O(N) for right pointer + O(N) for left pointer = O(2N) = O(N)
      - Each element is processed at most twice (added and removed from map).

    SPACE: O(1)
      - Map holds at most 3 keys (since we remove when >2 types), 
      - so space is constant with respect to input size.
*/
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int max = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        while (right < n) {
            freq.put(fruits[right], freq.getOrDefault(fruits[right], 0) + 1);
            while (freq.size() > 2) {
                freq.put(fruits[left], freq.get(fruits[left]) - 1);
                if (freq.get(fruits[left]) == 0) {
                    freq.remove(fruits[left]);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
}

/*
    SLIDING WINDOW (ALTERNATIVE IMPLEMENTATION)
    - Similar to above, but only update max when window has at most 2 types.
    - When types exceed 2, shrink window from left and update map counts.

    TIME: O(N) for right pointer + O(N) for left pointer = O(2N) = O(N)
      - Both pointers traverse the array at most once.

    SPACE: O(1)
      - Map size limited to at most 3 (since we remove when >2 types).
*/
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int max = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        while (right < n) {
            freq.put(fruits[right], freq.getOrDefault(fruits[right], 0) + 1);
            if (freq.size() > 2) {
                freq.put(fruits[left], freq.get(fruits[left]) - 1);
                if (freq.get(fruits[left]) == 0) {
                    freq.remove(fruits[left]);
                }
                left++;
            } else {
                max = Math.max(max, right - left + 1);
            }
            right++;
        }
        return max;
    }
}
