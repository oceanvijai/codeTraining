public class RandomFlipMatrix{
  
  /**
    
    There is an m x n binary grid matrix with all the values set 0 initially. Design an algorithm to randomly pick an index (i, j) 
    where matrix[i][j] == 0 and flips it to 1. All the indices (i, j) where matrix[i][j] == 0 should be equally likely to be returned.

    Optimize your algorithm to minimize the number of calls made to the built-in random function of your language and optimize 
    the time and space complexity.

    Implement the Solution class:
    Solution(int m, int n) Initializes the object with the size of the binary matrix m and n.
    int[] flip() Returns a random index [i, j] of the matrix where matrix[i][j] == 0 and flips it to 1.
    void reset() Resets all the values of the matrix to be 0.


    Example 1:

    Input
    ["Solution", "flip", "flip", "flip", "reset", "flip"]
    [[3, 1], [], [], [], [], []]
    Output
    [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

    Explanation
    Solution solution = new Solution(3, 1);
    solution.flip();  // return [1, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
    solution.flip();  // return [2, 0], Since [1,0] was returned, [2,0] and [0,0]
    solution.flip();  // return [0, 0], Based on the previously returned indices, only [0,0] can be returned.
    solution.reset(); // All the values are reset to 0 and can be returned.
    solution.flip();  // return [2, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.  
  
  
  **/
  
  
  /**
      Use the same approach as RandomPickWithBlacklist.java
      
      Whatever you picked so far, backlist it and then proceed further by reducing the range
      All operations are O(1)
  
  **/
  
  
  
  
  int range = 0;
    int reducedRange = 0;
    int rowCount = 0;
    int colCount = 0;

    Map<Integer,Integer> reMaps;
    Random r;
    
    public Solution(int m, int n) {
        init(m,n);
    }
    
    public int[] RandomFlipMatrix() {
        int num = r.nextInt(reducedRange);
        
        // actual value mapped for this randome number in the previous run if any
        int x = reMaps.getOrDefault(num,num);
        
        // Remap it to the last element in the series to the random number
        // Meaning the old mapped value will be replaced if it appears again
        reducedRange--;
        int lastValueMappedInt = reMaps.getOrDefault(reducedRange,reducedRange);
        reMaps.put(num, lastValueMappedInt);
        
        
        int row= x/colCount;
        int col= x%colCount;
        return new int[]{row, col};
    }
    
    public void reset() {
        init(rowCount,colCount);
    }
    
    public void init(int row, int col){
        reMaps = new HashMap<>();
        r = new Random();
        range = row*col;
        reducedRange = range;
        rowCount = row;
        colCount = col;
    }

  
  

}
