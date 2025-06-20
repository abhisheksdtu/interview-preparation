/*
    TWO PASS APPROACH
    - Do a left-to-right pass: each child with a higher rating than the left neighbor gets more candy.
    - Do a right-to-left pass: each child with a higher rating than the right neighbor gets more candy.
    - At the end, each child gets the max candy from both passes.

    TIME: O(N) first pass + O(N) second pass + O(N) sum = O(3N) = O(N)
      - One pass to fill left[], one pass to fill right[], one pass to sum up candies.
      - Each pass is linear in array size.

    SPACE: O(N) for left[] + O(N) for right[] = O(2N) = O(N)
      - Need two arrays to track minimum candies in both directions.
*/
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int left[] = new int[n];
        int right[] = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        right[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }

        return sum;
    }
}

/*
    TWO PASS OPTIMIZED SPACE APPROACH
    - Do a left-to-right pass and store result in left[].
    - Do a right-to-left pass with a single integer, combine result with left[].
    - Sum up maximum of left[i] and right count for each child.

    TIME: O(N) left pass + O(N) right pass + O(N) sum = O(3N) = O(N)
      - Each loop is a single pass.

    SPACE: O(N) for left[] + O(1) for right = O(N)
      - Only one extra array, right uses a single variable.
*/
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int left[] = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        int sum = left[n - 1];
        int right = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            sum += Math.max(left[i], right);
        }

        return sum;
    }
}

/*
    SINGLE PASS PEAK-VALLEY APPROACH
    - Use one loop to track increasing (uphill) and decreasing (downhill) sequences.
    - Handle equal ratings directly.
    - Adjust sum for overlap between peaks and valleys.

    TIME: O(N)
      - While loop runs through the array once, with some nested increments but total work is still linear.

    SPACE: O(1)
      - Only variables for counts and pointers.
      - No extra arrays used.
*/
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int sum = 1;
        int i = 1;
        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                sum++;
                i++;
                continue;
            }
            int peak = 1;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak++;
                sum += peak;
                i++;
            }
            int down = 1;
            while (i < n && ratings[i] < ratings[i - 1]) {
                sum += down;
                down++;
                i++;
            }
            if (down > peak) {
                sum += (down - peak);
            }
        }
        return sum;
    }
}
