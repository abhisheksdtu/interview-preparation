class Solution {
    public boolean checkSubsequenceSum(int[] arr, int k) {
        return checkSubsequenceSum(arr,k,0,0);
    }

    public boolean checkSubsequenceSum(int[] arr, int k,int sum,int idx){
        if(sum>k){
            return false;
        }
        if(idx==arr.length){
            if(sum==k){
                return true;
            }
            return false;
        }

        sum+=arr[idx];

        boolean c = checkSubsequenceSum(arr,k,sum,idx+1);

        if(c){
            return true;
        }

        sum-=arr[idx];

        return checkSubsequenceSum(arr,k,sum,idx+1);

    }
}