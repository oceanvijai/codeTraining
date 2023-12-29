public class TrappingRainWater2 {
	/**
		Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

		Given the following 3x6 height map:
		[
		  [1,4,3,1,3,2],
		  [3,2,1,3,2,4],
		  [2,3,3,2,3,1]
		]

		Return 4.

	**/

	/**
		It may look like, we can solve using DP like we did for TrappingRainWater1.
		Like calculate leftToRight, RightToLeft, TopToBottom, BottomToTop
		and for each one edge vertx, conside the min of all of its surroundings using the above 4 DPs
		and find the water collected at that index.

		But the problem is that, the water collected at that index is not only influenced by the min heights from the 4 sides.
		It can increase or decrease, based on the water collected on its adjacent cell.

	**/

	/**
		So, we need to find the water collected on the adjacent cells. But then they dependent on their adjacent cells.
		Ex: [[5,5,5,1],
		     [5,1,1,5],
		     [5,1,5,5],
		     [5,2,5,8]]

		So, we see a GRAPH traversal here.

		Which algo to use.
		Looks like BFS for now. Lets see further.

		What are the vertices and edges
		The cells are the vertices.
		Its four adjecent cells are edges

		So where do we start. We wont start on the boundar edges. Since they wont log water as per the problem.
		But we can get the smallest boundary vertex.
		This boundary vertex will be responsible for filling its adjacent cells. The based on the water filled in its 
		adjacent cells, the connected cells will also get the water based on its own height. 
		So once we start, we cotiue to get follow the path the water gets logged due to this border egde min height.

		We may have disconnected components. So what every boundary edge for that next component is min, will contribute to the 
		water limit in that componet
	**/


	public int trapRainWater(int[][] heightMap) {
        Queue<int[]> heap = new PriorityQueue<>((a,b)-> heightMap[a[0]][a[1]] - heightMap[b[0]][b[1]]);
        int rows = heightMap.length, cols = heightMap[0].length;
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(i == 0 || j == 0 || i == heightMap.length-1 || j == heightMap[0].length-1){
                    heap.offer(new int[]{i,j});
                }
            }
        }
        
        
        boolean[][] visited = new boolean[rows][cols];
        
        int filledWater = 0, maxHeight = Integer.MIN_VALUE;
        while(!heap.isEmpty()){
            int[] vertex = heap.poll();
            int i = vertex[0], j = vertex[1];
            
            if(visited[i][j]){
                continue;
            }
            
            visited[i][j] = true;
            maxHeight = Math.max(maxHeight, heightMap[i][j]);
            
            // relax the costs
            if(i-1 >= 0 && !visited[i-1][j]){
                heap.offer(new int[]{i-1,j});
                //visited[i-1][j] = true;
            }
            if(i+1 < rows && !visited[i+1][j]){
                heap.offer(new int[]{i+1,j});
                //visited[i+1][j] = true;
            }
            if(j-1 >= 0 && !visited[i][j-1]){
                heap.offer(new int[]{i,j-1});
                //visited[i][j-1] = true;
            }
            if(j+1 < cols && !visited[i][j+1]){
                heap.offer(new int[]{i,j+1});
                //visited[i][j+1] = true;
            }
            
            
            // if the maxHeight is less than the current cell height, then include it
            if(maxHeight > heightMap[i][j]){
                filledWater += maxHeight - heightMap[i][j];
            }
        }
        
        return filledWater;
    }



}
