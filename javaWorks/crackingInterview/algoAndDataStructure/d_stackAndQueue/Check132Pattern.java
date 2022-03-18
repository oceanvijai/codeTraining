public class Check132Pattern {
    /**
        Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

        Return true if there is a 132 pattern in nums, otherwise, return false.

        Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?



        Example 1:

        Input: nums = [1,2,3,4]
        Output: false
        Explanation: There is no 132 pattern in the sequence.
        Example 2:

        Input: nums = [3,1,4,2]
        Output: true
        Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
        Example 3:

        Input: nums = [-1,3,2,0]
        Output: true
        Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

    **/


    /**
        The pattern 132, it "Low valley" -> peak -> "second peak/secondLow"
        So to get this, we will use stack to keep the peek always at the top.

        But we also need second peek which is as close to the peek.
        So when we find/set peek set the second peek in another variable

        When the peek and second peek is set, then if any number after that is less than second peek,
        Then we found 1/Low valley, so return true.


    **/


    public boolean find132pattern(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        int two = Integer.MIN_VALUE;
        
        for(int i = nums.length-1; i >= 0; i--){
            
            if(nums[i] < two){
                return true;
            }
            
            // Set 3 component in the top of the list
            // Get the second last in the two variable
            while(!stack.isEmpty() && nums[i] > stack.peekLast()){
                two = stack.removeLast();
            }
            
            stack.addLast(nums[i]);
        }
        
        return false;
    }


}