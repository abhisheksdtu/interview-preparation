class Solution {
    public boolean checkValidString(String s) {
        return checkValidString(s, 0, 0);
    }

    public boolean checkValidString(String s, int idx, int c) {
        if (c < 0)
            return false;
        if (idx == s.length())
            return c == 0;

        if (s.charAt(idx) == '(')
            return checkValidString(s, idx + 1, c + 1);
        else if (s.charAt(idx) == ')')
            return checkValidString(s, idx + 1, c - 1);
        else
            return checkValidString(s, idx + 1, c + 1) ||
                    checkValidString(s, idx + 1, c - 1) ||
                    checkValidString(s, idx + 1, c);

    }
}



class Solution {
    public boolean checkValidString(String s) {
        int min = 0;
        int max = 0;
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)== '('){
                min+=1;
                max+=1;
            }else if(s.charAt(i)== ')'){
                min-=1;
                max-=1;
            }else{
                min-=1;
                max+=1;;
            }
            if(min<0) min = 0;
            if(max<0) return false;
        }
        return min == 0;
    }
}