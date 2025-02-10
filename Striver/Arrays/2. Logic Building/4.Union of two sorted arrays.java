/*
    BRUTE APPROACH - USING TREESET
    1. Use a TreeSet to store unique elements from both arrays.
       - A TreeSet automatically removes duplicates and keeps elements sorted.
    2. Add all elements from `a` to the set.
    3. Add all elements from `b` to the set.
    4. Convert the set into an integer array and return it.

    TIME COMPLEXITY
    - O(N log N + M log M), where N and M are the lengths of a and b.
      - Inserting each element into the TreeSet takes O(log N), so inserting N elements takes O(N log N).
    
    SPACE COMPLEXITY
    - O(N + M), for storing unique elements in the TreeSet.
*/

class Solution {
    public int[] unionArray(int[] a, int[] b) {
        Set<Integer> set = new TreeSet<>();

        // Add all elements from a
        for (int num : a) {
            set.add(num);
        }

        // Add all elements from b
        for (int num : b) {
            set.add(num);
        }

        // Convert set to an array
        int res[] = new int[set.size()];
        int i = 0;
        for (int num : set) {
            res[i++] = num;
        }

        return res;
    }
}


/*
    OPTIMAL APPROACH - MERGING TWO SORTED ARRAYS
    1. Use a List to store the unique elements from both arrays.
    2. Use two pointers (`i` and `j`) to traverse both sorted arrays.
    3. Compare elements:
       - If `a[i] < b[j]`, add `a[i]` to the list (if not duplicate) and move `i`.
       - If `a[i] > b[j]`, add `b[j]` to the list (if not duplicate) and move `j`.
       - If equal, add only one instance and move both pointers.
    4. Add any remaining elements from `a` and `b` (if not duplicate).
    5. Convert the list to an integer array and return.

    TIME COMPLEXITY
    - O(N + M), where N and M are the lengths of arrays `a` and `b`.

    SPACE COMPLEXITY
    - O(N + M), for storing the union in a list.
*/

class Solution {
    public int[] unionArray(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                // Add a[i] if it's not a duplicate
                if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                    list.add(a[i]);
                }
                i++;
            } else if (a[i] > b[j]) {
                // Add b[j] if it's not a duplicate
                if (list.isEmpty() || list.get(list.size() - 1) != b[j]) {
                    list.add(b[j]);
                }
                j++;
            } else {
                // Add either a[i] or b[j] (they are equal) and move both pointers
                if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                    list.add(a[i]);
                }
                i++;
                j++;
            }
        }

        // Add remaining elements from a
        while (i < a.length) {
            if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                list.add(a[i]);
            }
            i++;
        }

        // Add remaining elements from b
        while (j < b.length) {
            if (list.isEmpty() || list.get(list.size() - 1) != b[j]) {
                list.add(b[j]);
            }
            j++;
        }

        // Convert list to array
        return list.stream().mapToInt(num -> num).toArray();
    }
}
