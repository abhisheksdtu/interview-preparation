/*
    BRUTE APPROACH
    1. Initialize an array nge with -1 to store the next greater elements.
    2. Use two nested loops:
        a. Outer loop picks each element from the array.
        b. Inner loop finds the first greater element to the right.
    3. Update nge[i] with the first greater element if found; otherwise, it remains -1.
    4. Return the nge array.

    TIME COMPLEXITY
    - O(n ^ 2): Outer loop runs O(n), and the inner loop can run up to O(n) in the worst case.

    SPACE COMPLEXITY
    - O(n): Space used by the nge array.
*/

class Solution {
    public int[] nextGreaterElement(int arr[]) {
        int nge[] = new int[arr.length];
        Arrays.fill(nge, -1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    nge[i] = arr[j];
                    break;
                }
            }
        }
        
        return nge;
    }
}

/*
    OPTIMIZED APPROACH
    1. Use a stack to efficiently find the next greater element.
    2. Traverse the array from right to left:
        a. Remove elements from the stack smaller than or equal to the current element.
        b. If the stack is empty, there is no greater element, so set `nge[i] = -1`.
        c. If the stack is not empty, the top of the stack is the next greater element.
    3. Push the current element onto the stack for future comparisons.
    4. Return the `nge` array.

    TIME COMPLEXITY
    - O(2 * n): Each element is pushed and popped from the stack at most once.

    SPACE COMPLEXITY
    - O(n): Space used by the stack and the result array.
*/
class Solution {
    public int[] nextGreaterElement(int arr[]) {
        int nge[] = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        // Traverse the array from right to left
        for (int i = arr.length - 1; i >= 0; i--) {
            // Remove elements smaller or equal to the current element
            while (!st.isEmpty() && arr[i] >= st.peek()) {
                st.pop();
            }

            // Set next greater element
            if (st.isEmpty()) {
                nge[i] = -1;
            } else {
                nge[i] = st.peek();
            }

            // Push current element to the stack
            st.push(arr[i]);
        }

        return nge;
    }
}
