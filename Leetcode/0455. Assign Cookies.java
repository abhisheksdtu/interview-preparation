/*
    APPROACH
    1. Sort both arrays so that we can assign the smallest suitable cookie to
       each child.
    2. Use two pointers `i` for children and `j` for cookies.
    3. If the current cookie satisfies the current child's greed factor,
       increment `i` to move to the next child.
    4. Always increment `j` to check the next cookie.

    TIME COMPLEXITY
    - O(m log m + n log n) for sorting, where `m` is the number of children and
      `n` is the number of cookies.

    SPACE COMPLEXITY
    - O(1) (ignoring the recursion stack used by the sorting algorithm).
*/
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;

        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
