class Solution {
    public double myPow(double x, int n) {
        double pow = power(x,Math.abs(n));
        return n<0 ? 1.0/pow : pow;
    }

    public double power(double x, int n) {
        if(n==0){
            return 1.0;
        }

        return x * power(x,n-1);
    }
}