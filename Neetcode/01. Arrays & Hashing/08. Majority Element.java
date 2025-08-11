class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        int max=0;
        int n=Integer.MIN_VALUE;
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
            if(map.get(num)>max){
                max=map.get(num);
                n=num;
            }
        }
        return n;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        int max=0;
        int n=Integer.MIN_VALUE;
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
            if(map.get(num)>max){
                max=map.get(num);
                n=num;
            }
        }
        return n;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        int res=0,c=0;
        for(int num:nums){
            if(c==0){
                res=num;
            }
            if(res==num){
                c++;
            }else{
                c--;
            }
        }
        return res;
    }
}