class Solution {
    public int[] intersectionArray(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        
        boolean vis[]=new boolean[b.length];

        for(int i=0;i<a.length;i++){
            for(int j=0;j<b.length;j++){
                if(a[i]==b[j] && !vis[j]){
                    list.add(a[i]);
                    vis[j]=true;
                    break;
                }

                if(b[j]>a[i]){
                    break;
                }
            }
        }

        return list.stream().mapToInt(num -> num).toArray();
    }
}

class Solution {
  public int[] intersectionArray(int[] a, int[] b) {
    List<Integer> list = new ArrayList<>();

    int i = 0;
    int j = 0;
    while (i < a.length && j < b.length) {
      if (a[i] < b[j]) {
        i++;
      } else if (b[j] < a[i]) {
        j++;
      } else {
        list.add(a[i]);
        i++;
        j++;
      }
    }

    return list.stream().mapToInt(num -> num).toArray();
  }
}
