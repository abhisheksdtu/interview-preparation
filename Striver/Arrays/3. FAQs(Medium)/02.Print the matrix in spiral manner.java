class Solution {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    int n = matrix.length;
    int m = matrix[0].length;

    int colStart = 0, colEnd = m - 1;
    int rowStart = 0, rowEnd = n - 1;

    while (colStart <= colEnd && rowStart <= rowEnd) {
      for (int i = colStart; i <= colEnd; i++) {
        res.add(matrix[rowStart][i]);
      }
      rowStart++;
      for (int i = rowStart; i <= rowEnd; i++) {
        res.add(matrix[i][colEnd]);
      }
      colEnd--;

      if (rowStart <= rowEnd) {
        for (int i = colEnd; i >= colStart; i--) {
          res.add(matrix[rowEnd][i]);
        }
        rowEnd--;
      }

      if (colStart <= colEnd) {
        for (int i = rowEnd; i >= rowStart; i--) {
          res.add(matrix[i][colStart]);
        }
        colStart++;
      }
    }

    return res;
  }
}
