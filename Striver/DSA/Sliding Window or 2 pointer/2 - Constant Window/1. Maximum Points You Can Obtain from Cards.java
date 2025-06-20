/*
    OPTIMAL APPROACH
    - First, pick all k cards from the left (start) and calculate their sum.
    - Then, for each step, take one less card from the left and one more card from the right (end), updating sums.
    - Track the maximum total at each step.

    TIME: O(k) first loop + O(k) second loop = O(2k) = O(k)
      - First loop sums the first k cards from the left.
      - Second loop shifts cards from left to right for all k positions.
      - Both loops are linear in k.

    SPACE: O(1)
      - Only variables for sums and pointers are used, no extra data structures.
*/
class Solution {
  public int maxScore(int[] cardScore, int k) {

    int leftSum = 0;
    int rightSum = 0;
    int maxSum = 0;

    for (int i = 0; i < k; i++) {
      leftSum += cardScore[i];
      maxSum = Math.max(maxSum, leftSum);
    }

    int rightIndex = cardScore.length - 1;

    for (int i = k - 1; i >= 0; i--) {
      leftSum -= cardScore[i];
      rightSum += cardScore[rightIndex];
      maxSum = Math.max(maxSum, leftSum + rightSum);
      rightIndex--;
    }

    return maxSum;
  }
}
