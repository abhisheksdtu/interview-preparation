// Given two strings, write a method to decide if one is a permutation of the other

import java.util.Arrays;

public class Main {

    // SORT BOTH THE STRINGS AND CHECK IF THEY ARE EQUAL
    // TC - O(N LOG N)
    public static boolean brute(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        return sort(a).equals(sort(b));
    }

    public static String sort(String s) {
        char arr[] = s.toCharArray();
        Arrays.sort(arr);
        // return String.valueOf(arr);
        return new String(arr);
        // return Arrays.toString(arr); // WILL GIVE WRONG OUTPUT arr[] = {'a','b','c'} -> output -> [abc]
    }

    /*
     * CREATE A FREQ ARRAY FOR FIRST STRING. DECREASE FREQ FOR THE SECOND STRING.
     * IF FREQ < 0 AFTER DECREMENT THEN RETURN FALSE.
     * IT MEANS THAT SECOND STRING CONTAINS SOME CHARACTERS WHICH ARE NOT PRESENT IN FIRST STRING.
     * HENCE SECOND STRING IS NOT A PERMUTATION OF FIRST STRING
     * 
     * FOR ASCII CHARACTERS USE INTEGER ARRAY OF LENGTH 128. OR CREATE A FREQUENCY MAP
     * SC - O(N) OR O(1)
     * TC - O(N Log N)
     * 
     */
    public static boolean optimized(String a, String b) {
        int freq[] = new int[128];
        for (int i = 0; i < a.length(); i++) {
            int ch = a.charAt(i);
            freq[ch]++;
        }

        for (int i = 0; i < b.length(); i++) {
            int ch = b.charAt(i);
            if (freq[ch] == 0) {
                return false;
            }
            freq[ch]--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(brute("abc", "bac"));
        System.out.println(brute("abc", "cab"));
        System.out.println(brute("abc", "adc"));

        System.out.println("----------------------------");

        System.out.println(optimized("abc", "bac"));
        System.out.println(optimized("abc", "cab"));
        System.out.println(optimized("abc", "adc"));
    }
}