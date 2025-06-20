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
