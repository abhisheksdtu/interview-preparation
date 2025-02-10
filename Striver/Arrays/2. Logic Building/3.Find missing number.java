class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        for(int i=0;i<=n;i++){
            boolean flag=false;
            for(int j=0;j<n;j++){
                if(nums[j]==i){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                return i;
            }
        }
        return -1;
    }
}

class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        boolean vis[]=new boolean[n+1];
        for(int i=0;i<n;i++){
            vis[nums[i]]=true;
        }
        for(int i=0;i<=n;i++){
            if(!vis[i]){
                return i;
            }
        }
        return -1;
    }
}

class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        int sum = (int)(n * (n+1))/2;

        int s=0;
        for(int i=0;i<n;i++){
            s+=nums[i];
        }
        
        return sum-s;
    }
}

class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;

        int xor1=0;

        for(int i=0;i<=n;i++){
            xor1=xor1 ^ i;
        }

        int xor2=0;

        for(int i=0;i<n;i++){
            xor2=xor2 ^ nums[i];
        }

        return xor1^xor2;        
    }
}