public class HouseRobers3{
	

	/**
		The thief has found himself a new place for his thievery again. 
		There is only one entrance to this area, called the "root." Besides the root, 
		each house has one and only one parent house. 
		After a tour, the smart thief realized that "all houses in 
		this place forms a binary tree". It will automatically contact the police 
		if two directly-linked houses were broken into on the same night.

	**/

	/**
		We can thing of this similare to the previous 2 variations
		only we need to conside left and right every step

	**/


	public int rob(TreeNode root) {
        MyState res = solve(root);
        return res.maxSoFar;
    }
    
    private MyState solve(TreeNode root){
        if(root == null){
            return new MyState(0,0,0);
        }
        
        MyState left = solve(root.left);
        MyState right = solve(root.right);
        
        int rob = root.val + left.notRob + right.notRob;
        int noRob = left.rob + right.rob; // we can also use left.maxSoFar + right.maxSoFar;
        int maxSofar = Math.max(rob,noRob);
        
        return new MyState(maxSofar, noRob,maxSofar);
    }
    
    private class MyState{
        int rob;
        int notRob;
        int maxSoFar;
        
        public MyState(int r, int nr, int max){
            this.rob = r;
            this.notRob = nr;
            this.maxSoFar = max;
        }
    }

}