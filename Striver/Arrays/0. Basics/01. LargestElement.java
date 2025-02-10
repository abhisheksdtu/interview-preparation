// https://www.geeksforgeeks.org/problems/largest-element-in-array4009/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=largest-element-in-array
// https://www.naukri.com/code360/problems/largest-element-in-the-array-largest-element-in-the-array_5026279?utm_source=striver&utm_medium=website&utm_campaign=codestudio_a_zcourse

/* 
    TC - O(N * LOG N)
    SC - O(1)
*/

import java.util.*;
import java.io.*;

public class Solution {
    static int largestElement(int[] arr, int n) {
        Arrays.sort(arr);
        return arr[n - 1];
    }
}

/* 
    TC - O(N)
    SC - O(1)
*/
import java.util.*;
import java.io.*;

public class Solution {
    static int largestElement(int[] arr, int n) {
        int max = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }
}