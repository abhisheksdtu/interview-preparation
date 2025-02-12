class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n=nums.length;
        int res[] = new int[n];
        
        List<Integer> pl = new ArrayList<>();
        List<Integer> nl = new ArrayList<>();

        for(int num:nums){
            if(num>=0){
                pl.add(num);
            }else{
                nl.add(num);
            }
        }

        for(int i=0;i<n/2;i++){
            res[2*i]=pl.get(i);
            res[2*i+1]=nl.get(i);
        }

        return res;
    }
}