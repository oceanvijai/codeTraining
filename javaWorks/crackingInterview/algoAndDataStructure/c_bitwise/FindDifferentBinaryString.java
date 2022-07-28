public class FindDifferentBinaryString{
  
  /**
    Given an array of strings nums containing n unique binary strings each of length n, return a binary 
    string of length n that does not appear in nums. 
    If there are multiple answers, you may return any of them.
      
    Input: nums = ["01","10"]
    Output: "11"
    Explanation: "11" does not appear in nums. "00" would also be correct.
  
  **/
  
  /**
    Solution1: simple start from 0 and try and see if the values are already avaialble
    time: O(n^2)
    - N^2 to create hashSet
    - One binary string lenght n and at max (n+1) comparisons. so totally 2N^2
    
    Solution2: bad ass O(N), see below
  
  **/
  
  
  public String findDifferentBinaryString(String[] nums) {
        // popualte nums in a set
        //Set<String> available = Arrays.stream(nums).collect(Collectors.toCollection(HashSet::new));
        Set<String> available = new HashSet<>();
        for(String num : nums){
            available.add(num);
        }
        
        int n = nums.length;
        int upperBound = (int)Math.pow(2,n);
        
        // start from 0 till 2^n to see which one is not taken
        for(int i=0; i < upperBound; i++){
            String str = convertToBinary(i,n);
            if(!available.contains(str)) return str;
        }
        
        return "";
    }
    
    private String convertToBinary(int n, int digits) {
        StringBuilder sb = new StringBuilder();
        for(int i=digits-1;i>=0;i--) 
            sb.append((n&1<<i) !=0 ?"1":"0");
        return sb.toString();
    }
    
    
    /**
       Based on "Cantor's Diagonalization"(God knows what it is)
       But the logic is as follows,
       
       If the numbers are unquie, and same size. If we diagonally pick bits from their respective bit Index location,
       Then the number formed will be a different number then we already have.
       
       I dont know how !!!!!
    
    
    **/
    
    
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder ans= new StringBuilder();                  
        for(int i=0; i<nums.length; i++)  
            ans.append(nums[i].charAt(i) == '0' ? '1' : '0');
        return ans.toString();
    }

}
