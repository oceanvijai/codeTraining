public class RobotBoundedInCircle{
	/**
		On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

		"G": go straight 1 unit;
		"L": turn 90 degrees to the left;
		"R": turn 90 degress to the right.
		The robot performs the instructions given in order, and repeats them forever.

		Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.					

	**/


	/**
		Example 1:

		Input: "GGLLGG"
		Output: true
		Explanation: 
		The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
		When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
		Example 2:

		Input: "GG"
		Output: false
		Explanation: 
		The robot moves north indefinitely.
		Example 3:

		Input: "GL"
		Output: true
		Explanation: 
		The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...							

	**/


	/**
		Approach: If we take a 2D martix which has no bounds, if we apply a sequence and if the final direction 
		is not the same, as initial, then the robot always come back.

		Try it out in paper

		for we have a direction variable which moves in either of the four direction based on L and R.

		corner case: Also if the direction is same as intial at the end, then if the 
		current coordinate is 0,0 then also it forms cycle.



	**/


	public boolean isRobotBounded(String instructions) {
        // direction 0 -> north
        // direction 1 -> west
        // direction 2 -> south
        // direction 3 -> east
        
        int direction = 0, row = 0, col = 0;
        for(int i = 0; i < instructions.length(); i++){
            char c = instructions.charAt(i);
            if(c == 'G'){ // move forward based on the direction
                if(direction == 0){ 
                    // North movement
                    row++;
                }else if(direction == 1){
                    // west movement
                    col--;
                }else if(direction == 2){
                    // south movement
                    row--;
                }else if(direction == 3){
                    // west movement
                    col++;
                }
            }else if(c == 'L'){
                direction = (direction +1) % 4;
            }else if(c == 'R'){
                direction = (direction -1 + 4) % 4;
            }
        }
        
        if(direction != 0 || (row ==0 && col == 0)){
            return true;
        }
        
        return false;
    }
}