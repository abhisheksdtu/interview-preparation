class Solution {
  public int smallestDivisor(int[] nums, int limit) {
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      max = Math.max(num, max);
    }

    for (int divisor = 1; divisor <= max; divisor++) {
      int sum = 0;
      for (int num : nums) {
        sum += (int) (Math.ceil((double)num / divisor));
      }
      if (sum <= limit) {
        return divisor;
      }
    }

    return -1;
  }
}


class Solution {
  public int smallestDivisor(int[] nums, int limit) {
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      max = Math.max(num, max);
    }

    int lo = 1;
    int hi = max;
    int smallest = -1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int sum = getSum(nums, mid);
      if (sum <= limit) {
        hi = mid - 1;
        smallest = mid;
      } else {
        lo = mid + 1;
      }
    }

    return smallest;
  }

  public int getSum(int nums[], int divisor) {
    int sum = 0;
    for (int num : nums) {
      sum += Math.ceil((double) num / divisor);
    }
    return sum;
  }
}
