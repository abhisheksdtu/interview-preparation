class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }

        int freq[]=new int[26];
        for(int i=0;i<s.length();i++){
            char ss=s.charAt(i);
            char tt=t.charAt(i);
            freq[ss-'a']++;
            freq[tt-'a']--;
        }

        for(int i=0;i<26;i++){
            if(freq[i]!=0) return false;
        }

        return true;
    }
}
