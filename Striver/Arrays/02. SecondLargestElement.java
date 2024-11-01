// https://www.geeksforgeeks.org/problems/second-largest3735/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=second-largest

/*
    TC - O(N * LOG N + N)
    SC - O(1)
*/
class Solution {
    public int getSecondLargest(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int max = arr[n-1];
        int smax=-1;
        for(int i=n-2;i>=0;i--){
            if(arr[i]!=max){
                smax=arr[i];
                break;
            }
        }
        return smax;
    }
}

/*
    TC - O(N * LOG N + N)
    SC - O(1)
*/
class Solution {
    public int getSecondLargest(int[] arr) {
        int n = arr.length;
        int max = arr[0];
        
        for(int i=1;i<n;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        
        int smax = -1;
        
        for(int i=0;i<n;i++){
            if(arr[i]>smax && arr[i]!=max){
                smax=arr[i];
            }
        }
        
        return smax;
    }
}

/*
    smax = -1 -> ONLY WORKS WHEN NUMBERS OF ARRAY ARE GREATER THAN 0 IF NOT THEN TAKE smax = Integer.MIN_VALUE
    
    TC - O(N * LOG N + N)
    SC - O(1)
*/
class Solution {
    public int getSecondLargest(int[] arr) {
        int n = arr.length;
        int max = arr[0];
        int smax = -1;
        for(int i=1;i<n;i++){
            if(arr[i]>max){
                smax=max;
                max=arr[i];
            }else if (arr[i]<max && arr[i]>smax){
                smax = arr[i];
            }
        }
        
        
        return smax;
    }
}

/*
    https://www.naukri.com/code360/problems/ninja-and-the-second-order-elements_6581960?utm_source=striver&utm_medium=website&utm_campaign=codestudio_a_zcourse&leftPanelTabValue=PROBLEM
    BOTH SECOND LARGEST AND SECOND SMALLEST
*/
public class Solution {
    public static int[] getSecondOrderElements(int n, int []a) {
        int secondLargest = getSecondLargest(a,n);
        int secondSmallest = getSecondSmallest(a,n);
        return new int[]{secondLargest,secondSmallest};
    }

    public static int getSecondLargest(int a[],int n){
        int largest = a[0];
        int secondLargest = -1;

        for(int i=1;i<n;i++){
            if(a[i]>largest){
                secondLargest=largest;
                largest=a[i];
            }else if(a[i]!=largest && a[i]>secondLargest){
                secondLargest=a[i];
            }
        }

        return secondLargest;
    }

    public static int getSecondSmallest(int a[],int n){
        int smallest = a[0];
        int secondSmallest = Integer.MAX_VALUE;

        for(int i=1;i<n;i++){
            if(a[i]<smallest){
                secondSmallest=smallest;
                smallest=a[i];
            }else if(a[i]!=smallest && a[i]<secondSmallest){
                secondSmallest=a[i];
            }
        }

        return secondSmallest;
    }
}