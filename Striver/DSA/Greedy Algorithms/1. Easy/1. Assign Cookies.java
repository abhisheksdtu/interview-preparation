/*
    APPROACH
    - Sort both arrays: children's greed (g) and cookie sizes (s).
    - Try to satisfy each child with the smallest cookie that works:
        - If the current cookie can satisfy the current child, move to the next child.
        - Always move to the next cookie.
    - The answer is how many children were satisfied.

    TIME: O(M log M + N log N)   // Sorting both arrays (M = g.length, N = s.length)
    SPACE: O(1)                  // No extra space (sorting is in-place)
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
