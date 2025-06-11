/*
    BRUTE FORCE APPROACH
    - Scan every element in the matrix.
    - If you find the target, return true.
    - If not found after checking all, return false.

    TIME: O(N*M)   // N = rows, M = columns
    SPACE: O(1)    // No extra space
*/
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

/*
    BINARY SEARCH ROW-WISE
    - For each row, use binary search to look for the target.
    - Return true if found in any row.

    TIME: O(N * log M)  // Binary search in each of N rows
    SPACE: O(1)
*/
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
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return false;
    }
}
/*
    OPTIMAL: STAIRCASE SEARCH (from top-right)
    - Start from top-right corner.
    - If current is equal to target: found!
    - If current < target: move down.
    - If current > target: move left.

    TIME: O(N + M)
    SPACE: O(1)
*/
class Solution {
  public boolean searchMatrix(int[][] mat, int target) {
    int n = mat.length;
    int m = mat[0].length;
    int r = 0, c = m - 1;

    while (r < n && c >= 0) {
      if (mat[r][c] == target) return true;
      else if (mat[r][c] < target) r++;
      else c--;
    }
    return false;
  }
}

/*
    OPTIMAL: STAIRCASE SEARCH (from bottom-left)
    - Start from bottom-left corner.
    - If current is equal to target: found!
    - If current < target: move right.
    - If current > target: move up.

    TIME: O(N + M)
    SPACE: O(1)
*/
class Solution {
  public boolean searchMatrix(int[][] mat, int target) {
    int n = mat.length;
    int m = mat[0].length;
    int r = n-1, c = 0;

    while (r >= 0 && c < m) {
      if (mat[r][c] == target) return true;
      else if (mat[r][c] < target) c++;
      else r--;
    }
    return false;
  }
}
