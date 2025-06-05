/*
    APPROACH - SPIRAL TRAVERSAL
    1. Define boundaries: rowStart, rowEnd, colStart, colEnd.
    2. Traverse the matrix in a spiral order:
       - Left to right (top row)
       - Top to bottom (right column)
       - Right to left (bottom row, if applicable)
       - Bottom to top (left column, if applicable)
    3. Shrink boundaries after completing each direction.
    4. Repeat until all elements are added to the result list.

    TIME COMPLEXITY
    - O(N * M), where N is the number of rows and M is the number of columns.

    SPACE COMPLEXITY
    - O(1), ignoring the output list.
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        int colStart = 0, colEnd = cols - 1;
        int rowStart = 0, rowEnd = rows - 1;

        while (colStart <= colEnd && rowStart <= rowEnd) {
            // Traverse from left to right
            for (int i = colStart; i <= colEnd; i++) {
                result.add(matrix[rowStart][i]);
            }
            rowStart++;
            
            // Traverse from top to bottom
            for (int i = rowStart; i <= rowEnd; i++) {
                result.add(matrix[i][colEnd]);
            }
            colEnd--;
            
            // Traverse from right to left if there are remaining rows
            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    result.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }
            
            // Traverse from bottom to top if there are remaining columns
            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    result.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }

        return result;
    }
}
