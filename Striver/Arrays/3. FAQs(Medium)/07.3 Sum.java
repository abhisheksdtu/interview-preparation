class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> r = new ArrayList<>();
                        Collections.addAll(r,nums[i],nums[j],nums[k]);
                        Collections.sort(r);

                        set.add(r);
                    }
                }       
            }
        }

        res.addAll(set);

        return res;        
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> resultSet = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            Set<Integer> set = new HashSet<>();
            for(int j=i+1;j<nums.length;j++){
                int target = -1 * (nums[i]+nums[j]);

                if(set.contains(target)){
                    List<Integer> temp = new ArrayList<>();
                    Collections.addAll(temp,nums[i],nums[j],target);
                    Collections.sort(temp);
                    resultSet.add(temp);
                }

                set.add(nums[j]);
            }
        }

        res.addAll(resultSet);

        return res;
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        int n =nums.length;

        Arrays.sort(nums);

        for(int i=0;i<n;i++){
            if(i>0 &&nums[i]==nums[i-1]){
                continue;
            }

            int j= i+1;
            int k=n-1;

            while(j<k){
                int s=nums[i]+nums[j]+nums[k];
                if(s<0){
                    j++;
                }else if(s>0){
                    k--;
                }else{
                    List<Integer> list = new ArrayList<>();
                    Collections.addAll(list,nums[i],nums[j],nums[k]);
                    res.add(list);
                    j++;
                    k--;

                    while(j<k&&nums[j]==nums[j-1]){
                        j++;
                    }

                    while(j<k&&nums[k]==nums[k-1]){
                        k--;
                    }
                }
            }
        }

        return res;
    }
}