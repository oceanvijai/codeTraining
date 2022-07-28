public class RandomPickWithWeight{
  
  /**
      You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.

      You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) 
      and returns it. The probability of picking an index i is w[i] / sum(w).

      For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of 
      picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
      
      Input
      ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
      [[[1,3]],[],[],[],[],[]]
      Output
      [null,1,1,1,1,0]

      Explanation
      Solution solution = new Solution([1, 3]);
      solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
      solution.pickIndex(); // return 1
      solution.pickIndex(); // return 1
      solution.pickIndex(); // return 1
      solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

      Since this is a randomization problem, multiple answers are allowed.
      All of the following outputs can be considered correct:
      [null,1,1,1,1,0]
      [null,1,1,1,1,1]
      [null,1,1,1,0,0]
      [null,1,1,1,0,1]
      [null,1,0,1,0,0]
      ......
      and so on.
  **/
  
  /**
    Since we have weights involved, we can create a big array of repeating elements and pick from randomly.
    But that aray can be huge
    
    So, we do a trick here
    We create an aux array which adds up the weight of the current index + all the previous ones.
    So it will be a increasing array
    
    Now we get a randomly generated number from 0 to sumOfAllWeights
    Then we decide which index holds that randomly generated number.
    WE can use binary search to do it.
  
  **/
  
  
    int[] indexLimit;
    Random r;
    
    public RandomPickWithWeight(int[] w) {
        r = new Random();
        indexLimit = new int[w.length];
        indexLimit[0] = w[0];
        for(int i = 1; i < w.length; i++) {
            indexLimit[i] = indexLimit[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int n = indexLimit.length;
        int pickedIndex = r.nextInt(indexLimit[n-1])+1;
        
        // do binary search to find which index associated with pickedIndex
        int left=0,right=n-1;
        
        while(right > left){
            int mid = (left+right)/2;
            if(indexLimit[mid] == pickedIndex)
                return mid;
            else if(indexLimit[mid] < pickedIndex)
                left = mid+1;
            else
                right = mid;
        }
        
        return left;
    }
}
