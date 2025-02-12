class Solution {
  public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int num = target - nums[i];

        if(nums[j]==num){
            return new int[]{i,j};
        }
      }
    }

    return new int[0];
  }
}


class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int num = target - nums[i];

            if(map.containsKey(num)&&map.get(num)!=i){
                return new int[]{i,map.get(num)};
            }
        }

        return new int[0];
    }
}