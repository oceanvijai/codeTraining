public class RandomPickWithBlacklist{
  /**
    You are given an integer n and an array of unique integers blacklist. Design an algorithm to pick a random integer in the range [0, n - 1] 
    that is not in blacklist. Any integer that is in the mentioned range and not in blacklist should be equally likely to be returned.

    Optimize your algorithm such that it minimizes the number of calls to the built-in random function of your language.

    Implement the Solution class:

    Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
    int pick() Returns a random integer in the range [0, n - 1] and not in blacklist.
  
  **/
  
  /**
    Example 1:

    Input
    ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
    [[7, [2, 3, 5]], [], [], [], [], [], [], []]
    Output
    [null, 0, 4, 1, 6, 1, 0, 4]

    Explanation
    Solution solution = new Solution(7, [2, 3, 5]);
    solution.pick(); // return 0, any integer from [0,1,4,6] should be ok. Note that for every call of pick,
                     // 0, 1, 4, and 6 must be equally likely to be returned (i.e., with probability 1/4).
    solution.pick(); // return 4
    solution.pick(); // return 1
    solution.pick(); // return 6
    solution.pick(); // return 1
    solution.pick(); // return 0
    solution.pick(); // return 4
  **/
  
  
  /**
      The can map the invalid values with valid values. Then reduce the upperbound. 
      If you find some blacklisted numbers when generating random number, then return the valid values which was mapped
      
      // Ex: n=10, blacklist=[2,4,8,10]
      // Then range = 10, reducedRange = 10-4 = 6
      // The size values are [1,2,3,4,5,6] gets mapped as [1,7,3,9,5,6]
      // So wahtever is inside the reduced range which is not valid, we make it with valid values outside reduced range 
      
      Time: O(B) for solution & O(1) for pick
      
  
  **/
  
  
  Map<Integer,Integer> bListMapper = new HashMap<>();
    int range = 0;
    int reducedRange=0;
    Random r = new Random();

    public Solution(int n, int[] blacklist) {
        // Calulate the upper boound
        this.range = n; 
        this.reducedRange = range-blacklist.length;
        
        // Ex: n=10, blacklist=[2,4,8,10]
        // Then range = 10, reducedRange = 10-4 = 6
        // The siz values are [1,2,3,4,5,6] gets mapped as [1,7,3,9,5,6]
        // So wahtever is inside the reduced range which is not valid, we make it with valid values outside reduced range 
        
        for(int num: blacklist){
            bListMapper.put(num,-1);
        }
        
        int valuesForMapping = range-1;
        for(int num: blacklist){
            if(num < reducedRange){
                while(bListMapper.containsKey(valuesForMapping)){
                    valuesForMapping--;
                }
                bListMapper.put(num, valuesForMapping);
                valuesForMapping--;
            }
        }
        
    }
    
    public int pick() {
        if(reducedRange == 0){
            return 0;
        }
        int ans = r.nextInt(reducedRange);
        
        // Then pick from the reduced range
        // IF the random number is there in the map returned the mapped value, else the same value
        return bListMapper.getOrDefault(ans, ans);
    }

}
