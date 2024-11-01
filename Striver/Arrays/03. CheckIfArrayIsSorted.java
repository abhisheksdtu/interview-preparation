// https://www.naukri.com/code360/problems/ninja-and-the-sorted-check_6581957?utm_source=striver&utm_medium=website&utm_campaign=codestudio_a_zcourse

/*
    TC - O(N)
    SC - O(1)
*/
public class Solution {
    public static int isSorted(int n, int []a) {
        for(int i=1;i<n;i++){
            if(a[i-1]>a[i]){
                return 0;
            }
        }
        return 1;
    }
}