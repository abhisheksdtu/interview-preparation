// BRUTE

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

// OPTIMAL
class Solution {
    // Function to find the minimum number of platforms required at the
    // railway station such that no train waits.
    static int findPlatform(int arr[], int dep[]) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int n = arr.length;
        int res=1;
        int c=1;
        int i=1,j=0;
        while(i<n && j<n){
            if(dep[j]>=arr[i]){
                c++;
                i++;
            }else{
                c--;
                j++;
            }
            res=Math.max(res,c);
        }
        
        return res;
    }
}
