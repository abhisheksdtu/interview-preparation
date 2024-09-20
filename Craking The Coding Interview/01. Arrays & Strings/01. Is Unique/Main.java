// Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures? 

import java.util.Arrays;

public class Main {

    // We can use Set also but it'll give us Space Complexity of O(N)
    // For ASCII characters we can use an array of length 128. S.C. - O(1)
    // TC - O(N)
    public static boolean brute(String s) {
        boolean characterSet[] = new boolean[128];

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i);
            if (characterSet[idx]) {
                return false;
            }
            characterSet[idx] = true;
        }

        return true;
    }

    // SC - O(N)
    // TC - O(N Log N)
    public static boolean optimized(String s) {
        char[] str = s.toCharArray();
        Arrays.sort(str);
        for (int i = 1; i < str.length; i++) {
            if (str[i - 1] == str[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(brute("abc"));
        System.out.println(brute("aabbc"));

        System.out.println(optimized("abc"));
        System.out.println(optimized("aabbc"));
    }
}