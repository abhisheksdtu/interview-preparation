class Solution {
    public int aggressiveCows(int[] nums, int cows) {
        int n = nums.length;
        Arrays.sort(nums);
        
        int diff = nums[n-1]-nums[0];

        int res = -1;

        for(int i=1;i<=diff;i++){
            if(isPossible(nums,i,cows)){
                res=i;
            }else{
                break;
            }
        }

        return res;
    }

    public boolean isPossible(int[] nums,int dist,int cows){
        int currCows=1;
        int last = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]-last>=dist){
                currCows++;
                last=nums[i];
            }
            if(currCows==cows){
                return true;
            }
        }
        return false;
    }
}


class Solution {
    public int aggressiveCows(int[] nums, int cows) {
        int n = nums.length;
        Arrays.sort(nums);
        
        int diff = nums[n-1]-nums[0];

        int res = -1;

        int lo=1;
        int hi = diff;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            boolean possible = isPossible(nums,mid,cows);

            if(possible){
                res = mid;
                lo=mid+1;
            }else{
                hi=mid-1;
            }
        }

        return res;
    }

    public boolean isPossible(int[] nums,int dist,int cows){
        int currCows=1;
        int last = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]-last>=dist){
                currCows++;
                last=nums[i];
            }
            if(currCows==cows){
                return true;
            }
        }
        return false;
    }
}
