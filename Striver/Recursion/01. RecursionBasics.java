// PRINT NAME N TIMES
class Solution {
    public void printName(String name,int n) {
        if(n==0) return;
        System.out.println(name);
        printName(name,n-1);
    }
}

// PRINT LINEARLY FROM 1 TO N
class Solution {
    public void print(int n) {
        if(n==0) return;
        print(n-1);
        System.out.println(n);
    }
}

// PRINT LINEARLY FROM N TO 1
class Solution {
    public void print(int n) {
        if(n==0) return;
        System.out.println(n);
        print(n-1);
    }
}

// SUM OF FIRST N NUMBERS
class Solution {
    public int sum(int n) {
        if(n==0) return 0 ;
        return n + sum(n-1);
    }
}

// FACTORIAL OF N 
class Solution {
    public int factorial(int n) {
        if(n==0) return 1 ;
        return n * factorial(n-1);
    }
}

// REVERSE AN ARRAY
class Solution {
    public void reverse(int arr[]) {
        reverse(arr,0,arr.length-1);
    }

    public void reverse(int arr[], int lo,int hi) {
        if(lo>=hi) return;

        int t=arr[lo];
        arr[lo]=arr[hi];
        arr[hi]=t;
        reverse(arr,lo+1,hi-1);
    }
}

class Solution {
    public void reverse(int arr[]) {
        reverse(arr,0);
    }

    public void reverse(int arr[], int i) {
        if(i>=arr.length/2) return;

        int t=arr[i];
        arr[i]=arr[arr.length-i-1];
        arr[arr.length-i-1]=t;
        reverse(arr,i+1);
    }
}

// PALINDROME STRING
class Solution {
    public boolean palindrome(String s) {
        return palindrome(s,0,s.length()-1);
    }

    public boolean palindrome(String s, int lo,int hi) {
        if(lo>=hi) return true;

        return s.charAt(lo)==s.charAt(hi) && palindrome(s,lo+1,hi-1);
    }
}

class Solution {
    public boolean palindrome(String s) {
        return palindrome(s,0);
    }

    public boolean palindrome(String s, int i) {
        if(i>=s.length()/2) return true;

        return s.charAt(i)==s.charAt(s.length()-i-1) && palindrome(s,i+1);
    }
}


// RETURN NTH FIBONACCI NUMBER
class Solution {
    public int fibonacci(int n) {
        if(n==0||n==1) return n;
        return fibonacci(n-1)+fibonacci(n-2);
    }
}

// PRINT ALL SUBSEQUNECE
// SUBSEQUENCE - A subsequence is any subset of the array that maintains relative order but can skip elements.
// SUBARRAY - A subarray is a contiguous part of the array. It must maintain the order of elements and cannot skip elements.

// SUBARRAYS 
// Single-element:   [3], [1], [2]
// Two-element:      [3,1], [1,2], [3,1,2]
// Three-element:    [3,1,2] (entire array)
// TOTAL SUBARRAYS - n * (n+1) / 2

// SUBSEQUNECE
// No-element:       []
// Single-element:   [3], [1], [2]
// Two-element:      [3,1], [3,2], [1,2]
// Three-element:    [3,1,2]

// The total number of subsequences for an array of size n is 2^n:

class Solution {
    public void subsequence(int arr[]) {
        subsequence(arr,new ArrayList<>(),0);
    }

    public void subsequence(int arr[], ArrayList<Integer> subs,int idx) {
        if(idx==arr.length){
            System.out.println(Arrays.deepToString(subs.toArray()));
            return;
        }
        subs.add(arr[idx]);
        subsequence(arr,subs,idx+1);
        // subs.removeLast();
        // OR
        subs.remove(subs.size()-1);
        subsequence(arr,subs,idx+1);
    }
}

// PRINT SUBSEQUNECE WHOSE SUM IS K
class Solution {
    public void subsequence(int arr[],int k) {
        subsequence(arr,k,new ArrayList<>(),0,0);
    }

    public void subsequence(int arr[], int k, ArrayList<Integer> subs,int idx,int sum) {
        if(idx==arr.length){
            if(sum==k){
                System.out.println(Arrays.deepToString(subs.toArray()));
            }
            return;
        }
        
        subs.add(arr[idx]);
        sum+=arr[idx];
        subsequence(arr,k,subs,idx+1,sum);
        // subs.removeLast();
        // OR
        subs.remove(subs.size()-1);
        sum-=arr[idx];
        subsequence(arr,k,subs,idx+1,sum);
    }
}

// PRINT ANY SUBSEQUNECE WHOSE SUM IS K
class Solution {
    public boolean subsequence(int arr[],int k) {
        return subsequence(arr,k,new ArrayList<>(),0,0);
    }

    public boolean subsequence(int arr[], int k, ArrayList<Integer> subs,int idx,int sum) {
        if(idx==arr.length){
            if(sum==k){
                System.out.println(Arrays.deepToString(subs.toArray()));
                return true;
            }
            return false;
        }        
        
        subs.add(arr[idx]);
        sum+=arr[idx];
        if(subsequence(arr,k,subs,idx+1,sum)) return true;
        // subs.removeLast();
        // OR
        subs.remove(subs.size()-1);
        sum-=arr[idx];
        return subsequence(arr,k,subs,idx+1,sum);
    }
}

// COUNT SUBSEQUNECE WHOSE SUM IS K
class Solution {
    public int subsequence(int arr[],int k) {
        return subsequence(arr,k,new ArrayList<>(),0,0);
    }

    public int subsequence(int arr[], int k, ArrayList<Integer> subs,int idx,int sum) {
        if(idx==arr.length){
            if(sum==k){
                // System.out.println(Arrays.deepToString(subs.toArray()));
                return 1;
            }
            return 0;
        }        
        
        subs.add(arr[idx]);
        sum+=arr[idx];
        int l = subsequence(arr,k,subs,idx+1,sum);
        // subs.removeLast();
        // OR
        subs.remove(subs.size()-1);
        sum-=arr[idx];
        int r = subsequence(arr,k,subs,idx+1,sum);

        return l+r;
    }
}

// COMBINATION SUM

// PRINT

class Solution {
    public void combinationSum(int arr[],int k) {
        combinationSum(arr,k,new ArrayList<>(),0,0);
    }

    public void combinationSum(int arr[], int k, ArrayList<Integer> com,int idx,int sum) {
        if(sum>k){
            return;
        }
        if(idx==arr.length){
            if(sum==k){
                System.out.println(Arrays.deepToString(com.toArray()));
            }
            return;
        }        
        
        com.add(arr[idx]);
        sum+=arr[idx];
        combinationSum(arr,k,com,idx,sum);
        com.removeLast();
        sum-=arr[idx];
        combinationSum(arr,k,com,idx+1,sum);
    }
}

// RETURN
class Solution {
    public List<List<Integer>> combinationSum(int arr[],int k) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(arr,k,res,new ArrayList<>(),0,0);
        return res;
    }

    public void combinationSum(int arr[], int k, List<List<Integer>> res, List<Integer> com,int idx,int sum) {
        if(sum>k){
            return;
        }
        if(idx==arr.length){
            if(sum==k){
                res.add(new ArrayList<>(com));
            }
            return;
        }        
        
        com.add(arr[idx]);
        sum+=arr[idx];
        combinationSum(arr,k,res,com,idx,sum);
        com.removeLast();
        sum-=arr[idx];
        combinationSum(arr,k,res,com,idx+1,sum);
    }
}

class Solution {
    public List<List<Integer>> combinationSum(int arr[],int k) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(arr,k,res,new ArrayList<>(),0);
        return res;
    }

    public void combinationSum(int arr[], int k, List<List<Integer>> res, List<Integer> com,int idx) {
        if(idx==arr.length){
            if(sum==k){
                res.add(new ArrayList<>(com));
            }
            return;
        }        
        if(arr[idx]<=k){
            com.add(arr[idx]);
            combinationSum(arr,k-arr[idx],res,com,idx);
            com.removeLast();
        }
        combinationSum(arr,k,res,com,idx+1);
    }
}