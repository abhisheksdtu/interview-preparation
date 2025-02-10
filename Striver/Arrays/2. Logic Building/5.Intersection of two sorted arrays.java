/*
    BRUTE APPROACH - TWO POINTERS
    1. Use a boolean array `vis` to track visited elements in `b` to avoid duplicates.
    2. Traverse each element in `a` and compare it with elements in `b`.
       - If `a[i] == b[j]` and `b[j]` is not visited, add it to the list and mark it as visited.
       - If `b[j] > a[i]`, break early since the remaining elements in `b` are greater.
    3. Convert the list to an integer array and return it.

    TIME COMPLEXITY
    - O(N * M), where N and M are the lengths of `a` and `b`.

    SPACE COMPLEXITY
    - O(M), due to the `vis` array and output list.

    IMPROVEMENT
    - This approach is inefficient for large inputs.
    - A **better approach** is using sorting & two-pointer technique or HashSet.
*/

class Solution {
    public int[] intersectionArray(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        boolean vis[] = new boolean[b.length];

        // Brute force search with visited tracking
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j] && !vis[j]) {
                    list.add(a[i]);
                    vis[j] = true;
                    break;
                }

                if (b[j] > a[i]) {
                    break;
                }
            }
        }

        return list.stream().mapToInt(num -> num).toArray();
    }
}


/*
    APPROACH - TWO POINTERS (OPTIMAL FOR SORTED ARRAYS)
    1. Use two pointers `i` and `j` to traverse both sorted arrays `a` and `b`.
    2. If `a[i] < b[j]`, increment `i` (move to the next element in `a`).
    3. If `b[j] < a[i]`, increment `j` (move to the next element in `b`).
    4. If `a[i] == b[j]`, add the element to the list and move both pointers.
    5. Convert the list to an integer array and return it.

    TIME COMPLEXITY
    - O(N + M), where N and M are the lengths of arrays `a` and `b`.
      - Each element is processed at most once.

    SPACE COMPLEXITY
    - O(min(N, M)), for storing the intersection elements.
    - Uses only extra space for the output list (no additional data structures like HashSets).
*/

class Solution {
  public int[] intersectionArray(int[] a, int[] b) {
    List<Integer> list = new ArrayList<>();

    int i = 0, j = 0;

    while (i < a.length && j < b.length) {
      if (a[i] < b[j]) {
        i++; // Move pointer in 'a'
      } else if (b[j] < a[i]) {
        j++; // Move pointer in 'b'
      } else {
        if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
          list.add(a[i]); // Add unique intersection element
        }
        i++;
        j++;
      }
    }

    return list.stream().mapToInt(num -> num).toArray();
  }
}
