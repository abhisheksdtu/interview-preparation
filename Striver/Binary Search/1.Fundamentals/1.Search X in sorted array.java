/*
    TIME COMPLEXITY
    - O(log N), as we halve the search space in each iteration.

    SPACE COMPLEXITY
    - O(1), as we use only a few variables.
*/

class Solution {
  public int search(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    // Binary search loop
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2; // Avoids potential overflow

      // If mid element is the target, return index
      if (nums[mid] == target) {
        return mid;
      }
      // If target is greater, search in right half
      else if (nums[mid] < target) {
        lo = mid + 1;
      }
      // If target is smaller, search in left half
      else {
        hi = mid - 1;
      }
    }

    return -1; // Target not found
  }
}


class Solution { 
    public int search(int[] nums, int target) {
        return binarySearch(nums,0,nums.length-1,target);
    }

    public int binarySearch(int[] nums,int lo,int hi, int target){
        if(lo>hi){
            return -1;
        }

        int mid = lo+(hi-lo)/2;

        if(nums[mid]==target){
            return mid;
        }else if(nums[mid]>target){
            return binarySearch(nums,lo,mid-1,target);
        }else{
            return binarySearch(nums,mid+1,hi,target);
        }
    }
}