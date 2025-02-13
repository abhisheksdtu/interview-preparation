class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        int n=nums.length;
        for(int i=0;i<n-1;i++){
            List<Integer> list=new ArrayList<>();
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n-1;k++){
                    for(int l=k+1;l<n-1;l++){
                        if(nums[i]+nums[j]+nums[k]+nums[l]==target ){
                            Collections.addAll(list,nums[i],nums[j],nums[k],nums[l]);
                            Collections.sort(list);
                            set.add(list);
                        }
                    }
                }
            }
        }
        res.addAll(set);
        return res;
    }
}

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Set<List<Integer>> set = new HashSet<>();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      List<Integer> list = new ArrayList<>();
      for (int j = i + 1; j < n; j++) {
        Set<Integer> s = new HashSet<>();
        for (int k = j + 1; k < n; k++) {
          int num = target - (nums[i] + nums[j] + nums[k]);
          if (s.contains(num)) {
            Collections.addAll(list, nums[i], nums[j], nums[k], num);
            Collections.sort(list);
            set.add(list);
          }
          s.add(nums[k]);
        }
      }
    }
    res.addAll(set);
    return res;
  }
}
