public class CheckStraightLine{
	
	// You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. 
	// Check if these points make a straight line in the XY plane.

	// Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
	// Output: true

	public boolean solve(int[][] coordinates) {
        
        // to do this we need to calculate the slope of first two points and 
        // see if all other points lie on the same slope
        
        // slope of 2 points => (x,y) and (x1,y1)
        // slope = (y1-y)/(x1-x)
        
        // now if third point if it has the same slope
        // slope of => (x,y) and (x2,y2)
        // slope1 = (y2-y)/(x2-x)
        
        // slope = slope2, then they are in the stratight line
        
        // This is all we need, but while calculating the slope we have 
        // a division by zero problem
        // So to fix it we convert this algo into a multiplication problem
        
        // slope = slope1 => (y1-y)/(x1-x) == (y2-y)/(x2-x)
        // => (y1-y) * (x2-x) == (y2-y) * (x1-x)
        // That's it
        
        int x = coordinates[0][0];
        int y = coordinates[0][1];
        
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];
        
        for(int i = 2; i < coordinates.length; i++){
            int x2 = coordinates[i][0];
            int y2 = coordinates[i][1];
            
            if(((y1-y)*(x2-x)) != ((y2-y)*(x1-x))){
                return false;
            }
        }
        
        return true;
    }

}