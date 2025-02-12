class Solution {
    public void rotateMatrix(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                int t=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=t;
            }
        }

        for(int i=0;i<n;i++){
            reverse(matrix[i]);
        }
    }

    public void reverse(int arr[]){
        int lo=0;
        int hi=arr.length-1;

        while(lo<hi){
            int t=arr[lo];
            arr[lo]=arr[hi];
            arr[hi]=t;

            lo++;
            hi--;
        }
    }
}