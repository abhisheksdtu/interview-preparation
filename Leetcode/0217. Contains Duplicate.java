/*
    APPROACH
    1. Use Set. 
*/ 
// TC - O(N)
// SC - O(N)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}

/*
    APPROACH 
    1. Sort the numbers.
    2. Iterate from first element and check if the previous element is equal to the current element.
    3. If yes, return true.
    4. Else, return false.

    TIME COMPLEXITY
    1. For sorting the array - O(N * LOG N) 
    2. For iterating over array - O(N)
    3. Overall - O(N * LOG N) + O(N) = O(N * LOG N)

    SPACE COMPLEXITY
    1. O(1)
*/
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }
}