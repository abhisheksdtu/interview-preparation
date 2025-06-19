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