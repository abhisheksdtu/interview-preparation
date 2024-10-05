class Solution {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}

class Solution {
    public void sortColors(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else if (num == 1) {
                oneCount++;
            } else {
                twoCount++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < zeroCount) {
                nums[i] = 0;
            } else if (i < zeroCount + oneCount) {
                nums[i] = 1;
            } else if (i < zeroCount + oneCount + twoCount) {
                nums[i] = 2;
            }
        }
    }
}

class Solution {
    public void sortColors(int[] nums) {
        int lo = 0;
        int mid = 0;
        int hi = nums.length - 1;

        while (mid <= hi) {
            if (nums[mid] == 0) {
                // SWAP ELEMENTS AT LO & MID
                int temp = nums[lo];
                nums[lo] = nums[mid];
                nums[mid] = temp;

                lo++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                // SWAP ELEMENTS AT HI & MID
                int temp = nums[hi];
                nums[hi] = nums[mid];
                nums[mid] = temp;

                hi--;
            }
        }
    }
}