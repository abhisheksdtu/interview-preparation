class Solution {
  public void moveZeroes(int[] nums) {
    List<Integer> list = new ArrayList<>();

    for(int num:nums){
      if(num!=0){
        list.add(num);
      }
    }

    for(int i=0;i<nums.length;i++){
      if(i<list.size()){
        nums[i]=list.get(i);
      }else{
        nums[i]=0;
      }
    }
  }
}


class Solution {
  public void moveZeroes(int[] nums) {
    int i = -1;
    for(int j=0;j<nums.length;j++){
      if(nums[j]==0){
        i=j;
        break;
      }
    }

    if(i==-1){
      return;
    }

    for(int j=i+1;j<nums.length;j++){
      if(nums[j]!=0){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
        i++;
      }
    }
  }
}
