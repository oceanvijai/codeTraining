public class NumberOfIslands{

  /**
      Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

      An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
      You may assume all four edges of the grid are all surrounded by water.
       
      
      Example 1:
      
      Input: grid = [
        ["1","1","1","1","0"],
        ["1","1","0","1","0"],
        ["1","1","0","0","0"],
        ["0","0","0","0","0"]
      ]
      Output: 1
      Example 2:
      
      Input: grid = [
        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
      ]
      Output: 3
  **/

  /**
    Approach 1: Straight forward DFS and connected components
  **/

  public int numIslands(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];        
        int connectedComponent = 0;
        for(int i = 0; i < grid.length;i++){
            for(int j =0; j < grid[0].length; j++){
                if(grid[i][j] =='1' && !visited[i][j]){
                    //System.out.println("i:"+i+" j:"+j);
                    doDFS(grid,i,j,visited);
                    connectedComponent++;
                }
            }
        }
        return connectedComponent;
    }
    
    private void doDFS(char[][] grid,int i, int j,boolean[][] visited){
        if(visited[i][j]){
            return;
        }
        
        if(grid[i][j] != '1'){
            return;
        }
        
        visited[i][j] = true;
        //System.out.println("visiting:"+i+" j"+j);
        
        if(i-1 >= 0){
            doDFS(grid, i-1, j,visited);
        }
        if(i+1 < grid.length){
            doDFS(grid, i+1, j,visited);
        }
        
        if(j-1 >= 0){
            doDFS(grid, i, j-1,visited);
        }
        
        if(j+1 < grid[0].length){
            doDFS(grid, i, j+1,visited);
        }   
    }


  /**
    Approach 2: Without DFS. For graph which are very big and nested ilands

    iterate over the matrix. If a land is found if it belongs to the iland group at the top or bottom. If not add a group. If yes merge and remove it.

  **/

  public int numIslands(char[][] grid) {
        // Approach to do without DFS
        // This was we can count both land and water bodies
        return countComponents('1', grid);
    }

    private int countComponents(char land, char[][] grid){
        // initilize iland counters
        int noOfIlands = 0;
        int ans = 0;

        // To assign colour to each iland group
        int[][] mapColour = new int[grid.length][grid[0].length];
        // initlize hashMap to map parent child connections between ilands
        Map<Integer, Integer> parentMap = new HashMap<>();

        // iterate over the matrix
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                // If its a water return
                if(grid[i][j] != land){
                    continue;
                }

                // If land, see top and bottom if there is an existing iland
                int topIland = -1, previousIland = -1;
                if(i-1 >= 0 && grid[i-1][j] == land){
                    // We can check the top cell
                    topIland = getParentOf(mapColour[i-1][j], parentMap);
                }
                if(j-1 >= 0 && grid[i][j-1] == land){
                    // We can check the previous cell
                    previousIland = getParentOf(mapColour[i][j-1], parentMap);

                }

                // We can mark this as a new iland for now
                // If we dont have land up and previous left
                if(topIland == -1 && previousIland == -1){
                    ans++;
                    mapColour[i][j] = ++noOfIlands;
                }
                // If we have land only upside
                else if(topIland != -1 && previousIland == -1){ 
                    // assign the top iland group
                    mapColour[i][j] = topIland;
                }
                // If we have land only previous left 
                else if(previousIland != -1 && topIland == -1){ 
                    // assign the top previous iland group
                    mapColour[i][j] = previousIland;
                }
                // If we have land both and previous left
                // If both land belongs to same group
                else if(topIland == previousIland){
                    // we use the top one
                    mapColour[i][j] = topIland;
                }else if(topIland != previousIland){ 
                    // if both land belongs to different group we merge them
                    parentMap.put(previousIland, topIland);
                    mapColour[i][j] = topIland;
                    ans--;
                }
            }
        }

        return ans;
    }

    private int getParentOf(int group, Map<Integer, Integer> parentMap){
        int parent = group;
        while(parentMap.containsKey(parent)){
            parent = parentMap.get(parent);
        }

        return parent;
    }


  
}
