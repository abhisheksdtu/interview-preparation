class Solution {
  public int minimumRateToEatBananas(int[] nums, int h) {
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      max = Math.max(num, max);
    }

    for (int divisor = 1; divisor <= max; divisor++) {
      int sum = 0;
      for (int num : nums) {
        sum += Math.ceil((double) num / divisor);
      }
      if (sum <= h) {
        return divisor;
      }
    }
    return -1;
  }
}

class Solution {
  public int minimumRateToEatBananas(int[] nums, int h) {
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      max = Math.max(num, max);
    }

    int lo=1;
    int hi=max;
    int hours = -1;
    while(lo<=hi){
      int mid = lo+(hi-lo)/2;
      int sum=getSum(nums,mid);
      if(sum<=h){
        hi=mid-1;
        hours=mid;
      }else{
        lo=mid+1;
      }
    }
    return hours;
  }
  public int getSum(int nums[],int divisor){
    int sum=0;
    for(int num:nums){
      sum += Math.ceil((double) num / divisor);
    }
    return sum;
  }
}
