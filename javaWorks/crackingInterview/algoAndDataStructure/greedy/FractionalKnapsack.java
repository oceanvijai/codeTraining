public class FractionalKnapsack{
  /**
    Given the weights and profits of N items, in the form of {profit, weight} put these items in a knapsack 
    of capacity W to get the maximum total profit in the knapsack. 
    In Fractional Knapsack, we can break items for maximizing the total value of the knapsack.
    
    
    Consider the example: arr[] = {{100, 20}, {60, 10}, {120, 30}}, W = 50.
    
    Output: 240 
    Explanation: By taking items of weight 10 and 20 kg and 2/3 fraction of 30 kg. 
    Hence total price will be 60+100+(2/3)(120) = 240
    
    The price is also added as fraction if required
  
  
  **/
  
  
  /**
      Now this might sound like a DP problem. But not really.
      The nomal knapsack problem can we solved by DB but not greedily. Since if cannot be divided.
      The reverse is true here. The greedy is good for finding optimal solution since we can divided it.
      
      So the solution is as follows
      
        Calculate the value-to-weight ratio for each item.
        Sort the items in descending order based on their value-to-weight ratio.
        Iterate through the sorted items and include as much of each item as possible, starting from the item with the highest value-to-weight ratio.
        If the entire item can be included, add its value to the total value and subtract its weight from the remaining capacity of the knapsack.
        If the entire item cannot be included, calculate the fraction of the item that fits into the remaining capacity, 
        add the corresponding fraction of its value to the total value, and update the remaining capacity to reflect the space used by the fraction.
        Return the total value obtained.

  **/
  
  
  private static double getMaxValue(ItemValue[] arr,
                                      int capacity)
    {
        // Sorting items by profit/weight ratio;
        Arrays.sort(arr, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue item1,
                               ItemValue item2)
            {
                double cpr1
                    = new Double((double)item1.profit
                                 / (double)item1.weight);
                double cpr2
                    = new Double((double)item2.profit
                                 / (double)item2.weight);
 
                if (cpr1 < cpr2)
                    return 1;
                else
                    return -1;
            }
        });
 
        double totalValue = 0d;
 
        for (ItemValue i : arr) {
 
            int curWt = (int)i.weight;
            int curVal = (int)i.profit;
 
            if (capacity - curWt >= 0) {
 
                // This weight can be picked whole
                capacity = capacity - curWt;
                totalValue += curVal;
            }
            else {
 
                // Item cant be picked whole
                double fraction
                    = ((double)capacity / (double)curWt);
                totalValue += (curVal * fraction);
                capacity
                    = (int)(capacity - (curWt * fraction));
                break;
            }
        }
 
        return totalValue;
    }
  
  // Item value class
    static class ItemValue {
 
        int profit, weight;
 
        // Item value function
        public ItemValue(int val, int wt)
        {
            this.weight = wt;
            this.profit = val;
        }
    }


}
