class Solution {
  public int roseGarden(int n, int[] nums, int k, int m) {
    if (m * k > n) {
      return -1;
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      min = Math.min(min, num);
      max = Math.max(max, num);
    }
    
    for (int i = min; i <= max; i++) {
      if (fun(nums, i, k, m)) {
        return i;
      }
    }
    return -1;
  }

  public boolean fun(int nums[], int day, int k, int m) {
    int numberOfBouq = 0;
    int c = 0;
    for (int num : nums) {
      if (num <= day) {
        c++;
      } else {
        numberOfBouq += c / k;
        c = 0;
      }
    }
    numberOfBouq += c / k;
    return numberOfBouq >= m;
  }
}


class Solution {
  public int roseGarden(int n, int[] nums, int k, int m) {
    if (m * k > n) {
      return -1;
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      min = Math.min(min, num);
      max = Math.max(max, num);
    }

    int lo = min;
    int hi = max;
    int ans = -1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (fun(nums, mid, k, m)) {
        ans = mid;
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return ans;
  }

  public boolean fun(int nums[], int day, int k, int m) {
    int numberOfBouq = 0;
    int c = 0;
    for (int num : nums) {
      if (num <= day) {
        c++;
      } else {
        numberOfBouq += c / k;
        c = 0;
      }
    }
    numberOfBouq += c / k;
    return numberOfBouq >= m;
  }
}
