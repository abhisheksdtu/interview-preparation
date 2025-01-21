class Solution {
    public String largestOddNumber(String num) {
        String res = "";

        for (int i = num.length() - 1; i >= 0; i--) {
            int n = (int) (num.charAt(i) - '0');

            if (n % 2 != 0) {
                res = num.substring(0, i + 1);
                break;
            }
        }

        return res;
    }
}