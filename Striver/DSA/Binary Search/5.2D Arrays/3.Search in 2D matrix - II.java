class Solution {
    public boolean searchMatrix(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Solution {
    public boolean searchMatrix(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;

        for (int i = 0; i < n; i++) {
            boolean res = binarySearch(mat[i], target);
            if(res){
                return true;
            }            
        }
        return false;
    }

    public boolean binarySearch(int arr[], int target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }
}

class Solution {
  public boolean searchMatrix(int[][] mat, int target) {
    int n = mat.length;
    int m = mat[0].length;

    int r = 0;
    int c = m - 1;

    while (r < n && c >= 0) {
      if (mat[r][c] == target) {
        return true;
      } else if (mat[r][c] < target) {
        r++;
      } else {
        c--;
      }
    }
    return false;
  }
}


class Solution {
  public boolean searchMatrix(int[][] mat, int target) {
    int n = mat.length;
    int m = mat[0].length;

    int r = n-1;
    int c = 0;

    while (r >= 0 && c < m) {
      if (mat[r][c] == target) {
        return true;
      } else if (mat[r][c] < target) {
        c++;
      } else {
        r--;
      }
    }
    return false;
  }
}
