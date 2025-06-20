/*
    GREEDY APPROACH (FRACTIONAL KNAPSACK)
    - For each item, compute value/weight ratio.
    - Sort items by ratio in descending order.
    - Pick as much as possible from the highest ratio item until the knapsack is full.
    - If you can't take the whole item, take the possible fraction and stop.

    TIME: O(N) to build items + O(N log N) for sorting + O(N) for picking = O(N log N)
      - O(N): Build the array of item objects.
      - O(N log N): Sort the items by value/weight ratio.
      - O(N): Loop through the sorted items and fill the knapsack.
      - Total: O(N + N log N + N) = O(N log N) (sorting dominates).

    SPACE: O(N)
      - Extra space for the items array (stores all items as objects).
      - No other significant data structures.

    Why?
    - Building and iterating: O(N)
    - Sorting: O(N log N) is the largest term, so it determines the final time complexity.
*/
class Solution {
    class Item{
        int value;
        int weight;
        double ratio;

        Item(int value,int weight){
            this.value = value;
            this.weight=weight;
            this.ratio = (double)value/weight;
        }

        int getWeight(){return this.weight;}
        int getValue(){return this.value;}
        double getRatio(){return this.ratio;}
    }
    double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n  = values.length;
        Item[] items= new Item[n];
        for(int i=0;i<n;i++){
            items[i] = new Item(values[i],weights[i]);
        }

        Arrays.sort(items, (a,b) -> Double.compare(b.ratio,a.ratio));

        double res = 0;
        for(int i=0;i<n;i++){
            if(items[i].getWeight() <= W){
                res += items[i].getValue();
                W-=items[i].getWeight();
            }else{
                res += W * items[i].getRatio();
                break;
            }
        }

        return res;
    }
}
