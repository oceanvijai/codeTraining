public class JumpGame5{
	

	// reachNumber

	/**

		You are standing at position 0 on an infinite number line. There is a goal at position target.

		On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

		Return the minimum number of steps required to reach the destination.



		Example 1:
		Input: target = 3
		Output: 2
		Explanation:
		On the first move we step from 0 to 1.
		On the second step we step from 1 to 3.


		Example 2:
		Input: target = 2
		Output: 3
		Explanation:
		On the first move we step from 0 to 1.
		On the second move we step  from 1 to -1.
		On the third move we step from -1 to 2.
	**/

	/**

		This is actually a BFS problem. But if the target is very large the it will timeout.
		So we need a MATH solution.
		
		Intuition1: The target can be -ve or +ve, its the same for us.
		Intuition2: Since in nth step we move n steeps, we will be doing 1+3+6+10... etc
					So if the sum == target, the we can return the number of steps we took to reach target.
					If we sum > target, then if sum-target is even, then we can make one of the even numbers in the series to -ve to get to target
					If sum-target is odd, the we need another way to reach target.

	**/





	public int reachNumber(int target) {
        // -ve target is same as +ve
        target = Math.abs(target);
        int k = 0;
        // like jump, 1,2,3,4.... be do for index/target
        // like, 1,3,6,10...
        while (target > 0)
            target -= ++k;
        
        // If the target is zero, then k is the min jumps required
        // If the remaning target is odd the its one case, and even its another case
        return target % 2 == 0 ? k : k + 1 + k%2;
    }




}