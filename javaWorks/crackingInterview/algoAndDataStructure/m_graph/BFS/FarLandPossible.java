public class FarLandPossible{
	
	/**
		Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, 
		find a water cell such that its distance to the nearest land cell is maximized, 
		and return the distance. If no land or water exists in the grid, return -1.

		The distance used in this problem is the Manhattan distance: the distance 
		between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

		Example 1:
		Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
		Output: 2
		Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.


		Example 2:
		Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
		Output: 4
		Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.


	**/

	/**
		Approach 1: Brute force

		Add all one coordinates to a list.
		Then for every zero coordinate check with all ones for their distance and find the ans

		O(N^4) - Time out

	**/


	public int maxDistance(int[][] grid) {
        List<int[]> ones = new ArrayList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    ones.add(new int[]{i,j});
                }
            }
        }
        
        int ans = -1;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    int max = Integer.MAX_VALUE;
                    for(int[] oneCooredinate : ones){
                        max = Math.min(max, (Math.abs(oneCooredinate[0] - i) + Math.abs(oneCooredinate[1] - j)));
                    }
                    ans = Math.max(ans, max);
                }
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    /**
		Approach 2: BFS. O(n)
		Visualize the BFS like a water drop wave. Lets say the drop falls on grid where there is value 1.
		Then find all its neighbours which are zero and see how far they are from the 1.

		The zeroth place will be visited by its nearest one due to BFS shortest path property. So we know 
		the nearest 1 for any 0 cell.

		Note that even though we can do diagonally to visit a "0" cell/node, we dont do it, since the 1 in the 
		same row or col is close than an diagonal one. Ex:
		
		[[1,0,0],
		 [1,0,0],
		 [1,0,0]]
		the "0" at 2,2 is closer to the "1" in 2,0 than in 0,0. But in BFS doing diagonally 0,0 will first reach 2,2 then 2,0.

		Use these to do BFS

    **/


	public int maxDistance(int[][] grid) {
        Map<String, int[]> nearestOnePosition = new HashMap<>();
        
        LinkedList<int[]> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    int[] pos = new int[]{i,j};
                    queue.addLast(pos);
                    nearestOnePosition.put(i+"-"+j, pos);
                }
            }
        }
        
        int ans = 0;
        while(!queue.isEmpty()){
            int[] node = queue.pollFirst();
            int i = node[0];
            int j = node[1];
            
            int[] nearestOne = nearestOnePosition.get(i+"-"+j);
            ans = Math.max(ans, Math.abs(nearestOne[0] - i) + Math.abs(nearestOne[1] - j));
            
            String str = (i-1)+"-"+j;
            if(i-1 >= 0 && grid[i-1][j] == 0 && !nearestOnePosition.containsKey(str)){
                queue.addLast(new int[]{i-1,j});
                nearestOnePosition.put(str, nearestOne);
            }
            str = (i+1)+"-"+j;
            if(i+1 < grid.length && grid[i+1][j] == 0 && !nearestOnePosition.containsKey(str)){
                queue.addLast(new int[]{i+1,j});
                nearestOnePosition.put(str, nearestOne);
            }
            str = i+"-"+(j-1);
            if(j-1 >= 0 && grid[i][j-1] == 0 && !nearestOnePosition.containsKey(str)){
                queue.addLast(new int[]{i,j-1});
                nearestOnePosition.put(str, nearestOne);
            }
            str = i+"-"+(j+1);
            if(j+1 < grid[0].length && grid[i][j+1] == 0 && !nearestOnePosition.containsKey(str)){
                queue.addLast(new int[]{i,j+1});
                nearestOnePosition.put(str, nearestOne);
            }
        }
        
        return ans == 0 ? -1 : ans;
    }


    /**
		Approach 3: Same as approch 2, but we use another useful intution to make it run faster

		i.e, the max level/steps from a "1" cell in BFS is equal to the manhatten distance of any "0" cell.
		So we just need to find, how many level can this BFS go

    **/



    public int maxDistance(int[][] grid) {
        /* Insert all the 1's in the queue and expand the area in top,right,bottom
        and left till the all the values in grid becomes 1*/
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                if(grid[i][j]==1){
                    queue.add(new int[]{i,j});
                }
            }
        }
        /* if queue size is zero all water is present and if queue size is equal to
        (gridlength) square then all land is present hence solution cannot be obtained*/
        if(queue.size()==0 || queue.size()==(grid.length*grid.length)){
            return -1;
        }
        int count=0;
        /* here diagonal direction is not used as it will increase the count by 2 let
        (0,0) be land (1,1) be water so distance is 2 not 1*/ 
        while(!queue.isEmpty()){
            int size=queue.size();
            while(size!=0){
                int[] curr=queue.poll();
        if(isPossible(curr[0]-1,curr[1],grid.length) && grid[curr[0]-1][curr[1]]==0){
                    grid[curr[0]-1][curr[1]]=1;
                   queue.add(new int[]{curr[0]-1,curr[1]});
                }
        if(isPossible(curr[0]+1,curr[1],grid.length) && grid[curr[0]+1][curr[1]]==0){
                    grid[curr[0]+1][curr[1]]=1;
              queue.add(new int[]{curr[0]+1,curr[1]});
                }
        if(isPossible(curr[0],curr[1]+1,grid.length) && grid[curr[0]][curr[1]+1]==0){
                    grid[curr[0]][curr[1]+1]=1;
              queue.add(new int[]{curr[0],curr[1]+1});
                }
        if(isPossible(curr[0],curr[1]-1,grid.length) && grid[curr[0]][curr[1]-1]==0){
                    grid[curr[0]][curr[1]-1]=1;
              queue.add(new int[]{curr[0],curr[1]-1});
                }
             size--;   
            }
            count++;
        }
        
        /* after the BFS all the water becomes land and the count is reduced because we
        need to get the distance of the last water body that is away from the land*/
        return count-1;
        
        
    }
    
    private boolean isPossible(int x,int y,int length){
        
        if(x<0 || y<0 || x==length || y==length){
            return false;
        }
        return true;
    }



}