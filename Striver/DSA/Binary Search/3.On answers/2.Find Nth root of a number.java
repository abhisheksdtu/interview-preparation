class Solution {
  public int NthRoot(int n, int m) {
    for (int i = 1; i <= m; i++) {
      int num = (int) Math.pow(i, n);
      if (num == m) {
        return i;
      } else if(num>m) {
        break;
      }
    }
    return -1;
  }
}


class Solution {
  public int NthRoot(int n, int m) {
    int lo = 1;
    int hi = m;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int num = (int)Math.pow(mid, n);
      if (num == m) {
        return mid;
      } else if (num < m) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return -1;
  }
}


class Solution {
  public int NthRoot(int n, int m) {
    int lo = 1;
    int hi = m;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int num = func(mid,n,m );
      if (num == 1) {
        return mid;
      } else if (num == 0) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return -1;
  }

  public int func(int mid,int n,int m){
    long ans = 1;
    for(int i=1;i<=n;i++){
      ans = ans*mid;
      if(ans>m){
        return 2;
      }
    }
    return ans==m?1:0;
  }
}

