// User function Template for Java

class Solution {
    // Function to find the minimum number of platforms required at the
    // railway station such that no train waits.
    static int findPlatform(int arr[], int dep[]) {
        
        int count=0;

        for(int i=0;i<arr.length;i++){
            int c=0;
            for(int j=i+1;j<arr.length;j++){
                if(checkColiision(arr, dep, i, j)){
                    c++;
                }
            }
            count=Math.max(count,c);
        }

        return count;
    }
    static boolean checkColiision(int arr[], int dep[], int i, int j) {
        return arr[j]>=arr[i] && dep[j]>=dep[i] ||
        arr[i]>=arr[j] && dep[i]>=dep[j] ||
        arr[j]>=arr[i] && dep[i]>=dep[j] ||
        arr[i]>=arr[j] && dep[j]>=dep[i];
    }
}


/*
 arr[] = [900, 940, 950, 1100, 1500, 1800]
 dep[] = [910, 1200, 1120, 1130, 1900, 2000]

 i=0, j=i+1

 a[j]>d[i]

 */