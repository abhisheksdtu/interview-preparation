class Solution {
    public int rowWithMax1s(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int max = 0;
        int maxIdx = -1;
        for (int i = 0; i < n; i++) {
            int c = 0;
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    c++;
                }
            }
            if (c > max) {
                max = c;
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}

class Solution {
    public int rowWithMax1s(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int max = 0;
        int maxIdx = -1;
        for (int i = 0; i < n; i++) {
            int c = m - lowerBound(mat[i], 1);
            if (c > max) {
                max = c;
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public int lowerBound(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int low = 0, high = nums.length - 1;
        int resultIndex = nums.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            // MAYBE AN ANSWER
            if (nums[mid] >= target) {
                resultIndex = mid;
                // LOOK FOR MORE SMALL IDX ON LEFT
                high = mid - 1;
            } else {
                // LOOK FOR TARGET ON RIGHT
                low = mid + 1;
            }
        }

        return resultIndex;
    }
}