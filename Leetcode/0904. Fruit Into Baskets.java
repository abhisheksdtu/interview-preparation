class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int max = 0;
        for(int i=0;i<n;i++){
            Set<Integer> set = new HashSet<>();
            int len = 0;
            for(int j=i;j<n;j++){
                set.add(fruits[j]);
                if(set.size()>2)    break;
                len++;
            }
            max = Math.max(len,max);
        }
        return max;
    }
}

class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int max = 0;
        int l = 0;
        int r = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        while (r < n) {
            freq.put(fruits[r], freq.getOrDefault(fruits[r], 0) + 1);
            while (freq.size() > 2) {
                freq.put(fruits[l], freq.get(fruits[l]) - 1);
                if (freq.get(fruits[l]) == 0) {
                    freq.remove(fruits[l]);
                }
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }
}

class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int max = 0;
        int l = 0;
        int r = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        while (r < n) {
            freq.put(fruits[r], freq.getOrDefault(fruits[r], 0) + 1);
            if (freq.size() > 2) {
                freq.put(fruits[l], freq.get(fruits[l]) - 1);
                if (freq.get(fruits[l]) == 0) {
                    freq.remove(fruits[l]);
                }
                l++;
            } else {
                max = Math.max(max, r - l + 1);
            }
            r++;
        }
        return max;
    }
}