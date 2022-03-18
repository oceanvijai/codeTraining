public class HouseRobbers2{
    
    /*
        You are a professional robber planning to rob houses along a street. 
        Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. 
        That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, 
        and it will automatically contact the police if two adjacent houses were broken into on the same night.

        Given a list of non-negative integers nums representing the amount of money of each house, 
        return the maximum amount of money you can rob tonight without alerting the police.


    */

    /*
        Approach, same as houseRobers only you try it twice. One with the first house and other without the 1st house

    */

    
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(robHouse(nums,0,nums.length-2),robHouse(nums,1,nums.length-1));
    }
    
    private int robHouse(int[] nums, int start, int end){
        if(start == end){
            return nums[start];
        }
        
        if(nums.length == 0 || start > end){
            return 0;
        }
        
        int include = nums[start];
        int exclude = Math.max(nums[start+1], include);
        
        for(int i = start+2; i <= end; i++){
            int c = Math.max(include+nums[i],exclude);
            include = exclude;
            exclude = c;
        }
        
        return Math.max(include, exclude);
    }

}