class Solution {
  public int countSubsequenceWithTargetSum(int[] arr, int k) {
    return countSubsequenceWithTargetSum(arr, k, 0, 0);
  }

  public int countSubsequenceWithTargetSum(int[] arr, int k, int sum, int idx) {
    if (sum > k) {
      return 0;
    }

    if (idx == arr.length) {
      if (sum == k) {
        return 1;
      }
      return 0;
    }

    sum += arr[idx];
    int l = countSubsequenceWithTargetSum(arr, k, sum , idx + 1);

    sum -= arr[idx];
    int r = countSubsequenceWithTargetSum(arr, k, sum , idx + 1);

    return l + r;
  }
}
