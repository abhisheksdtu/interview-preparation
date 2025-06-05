class Solution {
  public long floorSqrt(long n) {
    if(n==0) return 0;
    long res = 1;
    for (long i = 1; i <= n; i++) {
      if (i * i <= n) {
        res = i;
      } else {
        break;
      }
    }
    return res;
  }
}


class Solution {
  public long floorSqrt(long n) {
    if(n==0) return 0;
    long res =1;
    long lo=1;
    long hi=n;

    while(lo<=hi){
      long mid = lo+(hi-lo)/2;
      if(mid*mid<=n){
        res = mid;
        lo=mid+1;
      }else{
        hi=mid-1;
      }
    }
    return res;
  }
}
