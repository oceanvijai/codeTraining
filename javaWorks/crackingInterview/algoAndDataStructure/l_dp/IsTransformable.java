public class IsTransformable{
  
  /**
      Given two strings s and t, transform string s into string t using the following operation any number of times:

      Choose a non-empty substring in s and sort it in place so the characters are in ascending order.
      For example, applying the operation on the underlined substring in "14234" results in "12344".
      Return true if it is possible to transform s into t. Otherwise, return false.

      A substring is a contiguous sequence of characters within a string.



      Example 1:

      Input: s = "84532", t = "34852"
      Output: true
      Explanation: You can transform s into t using the following sort operations:
      "84532" (from index 2 to 3) -> "84352"
      "84352" (from index 0 to 2) -> "34852"
      Example 2:

      Input: s = "34521", t = "23415"
      Output: true
      Explanation: You can transform s into t using the following sort operations:
      "34521" -> "23451"
      "23451" -> "23415"
      Example 3:

      Input: s = "12345", t = "12435"
      Output: false
  **/
  
  
  /**
  
  We dont have to try and form the string, we just need to see if its achievable.
  
  So we need to find conditions that will make it invalid.
  
  1. Length should be same
  2. same characters and in same frequencies
  3. By start from the last (We can do it from front also)
      If a number lets say s = XXXX3XXX t = XXXXXXX3
      from soruce 3 can move to the last position if there are only smaller number to its right in the source
      So if we check from 3,4,5,6,7,8,9 for their last seen index, and if its right of 3's index (4) then we know its not possible
  
  
  **/
  
  
  public boolean isTransformable(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        int n = s.length();

        Map<Integer, List<Integer>> DP = new HashMap<>();
        // Remember the values index occrunences
        for(int i = 0; i < n; i++){ 
            int val = Character.getNumericValue(s.charAt(i));
            if(!DP.containsKey(val)){
                DP.put(val, new ArrayList<>());
            }
            DP.get(val).add(i);
        }

        for(int i = n-1; i >= 0; i--){
            // Values which needs to be validated
            int val = Character.getNumericValue(t.charAt(i));
             List<Integer> indexArray = DP.get(val);
             // If not there, then we have a char which is t but not in s
            if(indexArray == null || indexArray.isEmpty()){
                return false;
            }
             // The last index of the val, which we will use as the lower bound for our scan
             int searchIndexlowerBound = indexArray.get(indexArray.size()-1);

            // Any number greater then val should not be right of val's last location in s
            // If it does we cannot place val in the same index of t
            for(int checkVal=val+1; checkVal < 10; checkVal++){
                List<Integer> checkValIndexList = DP.get(checkVal);

                if(checkValIndexList == null || checkValIndexList.isEmpty()){
                    continue;
                }

                // The last index of the number (checkVal) which is greater than val
                int checkValLastIndex = checkValIndexList.get(checkValIndexList.size()-1);
                // checkValLastIndex should not be right of searchIndexlowerBound
                if(checkValLastIndex >= searchIndexlowerBound){
                    return false;
                }
            }
            // Remove this last index, which we have placed that char in the array
            indexArray.remove(indexArray.size()-1);
        }

        return true;
    }


}
