/*
    BRUTE APPROACH
    1. Initialize an array nge with -1 to store the next greater elements.
    2. Use a nested loop to find the next greater element:
        a. Outer loop iterates through each element.
        b. Inner loop searches for a greater element to the right of the current element.
    3. If no greater element is found on the right:
        a. Use another loop to search for a greater element from the start of the array to the current position.
    4. Update nge[i] with the first greater element found, or leave it as -1 if none is found.
    5. Return the nge array.

    TIME COMPLEXITY
    - O(n^2): Two nested loops (one to the right and one to the left of the current element).

    SPACE COMPLEXITY
    - O(n): Space used by the nge array.
*/

class Solution {
    public int[] nextGreaterElement2(int arr[]) {
        int nge[] = new int[arr.length];
        Arrays.fill(nge, -1);

        for (int i = 0; i < arr.length; i++) {
            // Find next greater element to the right
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    nge[i] = arr[j];
                    break;
                }
            }

            // If not found, check from the start to the current position
            if (nge[i] == -1) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[i]) {
                        nge[i] = arr[j];
                        break;
                    }
                }
            }
        }

        return nge;
    }
}
