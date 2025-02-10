/*
    BRUTE APPROACH - BRUTE FORCE (INEFFICIENT)
    1. Iterate from 0 to `n` and check if the number exists in `nums`.
    2. Use a nested loop:
       - Outer loop iterates over numbers from 0 to `n`.
       - Inner loop checks if the number is present in `nums`.
    3. If a number is missing, return it.
    4. Return -1 if no number is missing (should never happen).

    TIME COMPLEXITY
    - O(N²), since for each number from 0 to `n`, we scan the array.

    SPACE COMPLEXITY
    - O(1), as we use only constant extra space.

    IMPROVEMENT
    - This solution is inefficient for large `n`.
    - A **better approach** is using the sum formula or XOR method.
*/

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        // Check for each number from 0 to n
        for (int i = 0; i <= n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (nums[j] == i) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return i;
            }
        }
        return -1;
    }
}

/*
    BETTER APPROACH - USING BOOLEAN ARRAY (EXTRA SPACE)
    1. Create a boolean array `vis` of size `n + 1` to track numbers present in `nums`.
    2. Mark `vis[nums[i]] = true` for each number in `nums`.
    3. Iterate from `0` to `n` to find the first `false` value in `vis`, which is the missing number.
    4. Return the missing number.

    TIME COMPLEXITY
    - O(N), since we traverse the array twice (once to mark numbers, once to find the missing one).

    SPACE COMPLEXITY
    - O(N), since we use an extra boolean array of size `n + 1`.

    IMPROVEMENT
    - This approach uses **extra space**, making it inefficient for large inputs.
    - A **better approach** is using the sum formula or XOR method.
*/

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        boolean vis[] = new boolean[n + 1];

        // Mark the numbers present in nums
        for (int i = 0; i < n; i++) {
            vis[nums[i]] = true;
        }

        // Find the missing number
        for (int i = 0; i <= n; i++) {
            if (!vis[i]) {
                return i;
            }
        }
        return -1;
    }
}


/*
    OPTIMAL APPROACH - SUM FORMULA (OPTIMAL)
    1. Compute the expected sum using the formula: `sum = n * (n + 1) / 2`.
    2. Compute the actual sum of elements in `nums`.
    3. The missing number is `expectedSum - actualSum`.

    TIME COMPLEXITY
    - O(N), as we compute the sum in a single pass.

    SPACE COMPLEXITY
    - O(1), since we use only a few integer variables.
*/

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
}

/*
    OPTIMAL APPROACH - XOR METHOD (OPTIMAL)
    1. XOR all numbers from `0` to `n` with elements in `nums`.
    2. Since `x ^ x = 0`, all paired numbers cancel out, leaving only the missing number.

    TIME COMPLEXITY
    - O(N), since we traverse the array once.

    SPACE COMPLEXITY
    - O(1), as we use only a single integer variable.
*/

class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        int n = nums.length;

        // XOR all numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }

        // XOR with array elements
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}