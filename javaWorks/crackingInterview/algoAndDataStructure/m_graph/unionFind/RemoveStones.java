public class RemoveStones{
	/**
		On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

		A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

		Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.


		Example 1:

		Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
		Output: 5
		Explanation: One way to remove 5 stones is as follows:
		1. Remove stone [2,2] because it shares the same row as [2,1].
		2. Remove stone [2,1] because it shares the same column as [0,1].
		3. Remove stone [1,2] because it shares the same row as [1,0].
		4. Remove stone [1,0] because it shares the same column as [0,0].
		5. Remove stone [0,1] because it shares the same row as [0,0].
		Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
		

		Example 2:

		Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
		Output: 3
		Explanation: One way to make 3 moves is as follows:
		1. Remove stone [2,2] because it shares the same row as [2,0].
		2. Remove stone [2,0] because it shares the same column as [0,0].
		3. Remove stone [0,2] because it shares the same row as [0,0].
		Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
		

		Example 3:

		Input: stones = [[0,0]]
		Output: 0
		Explanation: [0,0] is the only stone on the plane, so you cannot remove it.


	**/


	/**
		Approach 1: DFS

		We see that removing one depends and also affects the other. 
		we can remove a stone if and only if,
		there is another stone in the same column OR row
		
		We try to remove as many as stones as possible.


		Two stones are connected if they are in the same row or same col.
		Connected stones will build a connected graph.
		It's obvious that in one connected graph,we can't remove all stones.

		We have to have one stone left.
		An intuition is that, in the best strategy, we can remove until 1 stone.
		

		Connected stones can be reduced to 1 stone,
		the maximum stones can be removed = stones number - islands number.
		so just count the number of "islands/connected components".


		So lets find the conencted componets using DFS
		Time: O(N^2)
		Space: O(N)
		
	**/


	public int removeStones(int[][] stones) {
        int numberOfStones = stones.length;
        
        Set<String> visited = new HashSet<>();
        int connectedComponents = 0;
        for(int[] stone : stones){
            String node = stone[0]+"-"+stone[1];
            if(!visited.contains(node)){
                connectedComponents++;
                DFS(stone, visited, stones);
            }
        }
        
        return numberOfStones - connectedComponents;
    }
    
    private void DFS(int[] stone, Set<String> visited, int[][] stones){
        
        String node = stone[0]+"-"+stone[1];
        if(visited.contains(node)){
            return;
        }
        
        visited.add(node);
        int row = stone[0];
        int col = stone[1];
        
        for(int[] s : stones){
            if(stone != s && (s[0] == row || s[1] == col) ){
                DFS(s, visited, stones);
            }
        }
    }


    /**
		Approach 1: Union Find
		Again like we explained above, we just need to find the number of conected components

		The problem we find here is, how to build the graph. We since row value and column values can be same, this can create and issue.
		Ex: [0,1] and [1,0] has the same rows and columns values, but not conected if visualized in the matrix.

		So to solve this, we can padd the column values inorder to segerate the row and column values.
		We can also padd the rows in the similare way.
		We can padd lets day with value 1000.

		Next, how to build the graph.
		Now we create an edge between the row and the column of a single stone. So the parent of the col is the row.
		Now we need to group all the columns under one/many of the row values.

    **/



	public int removeStones(int[][] stones) {
        int numberOfStones = stones.length;
        int connectedComponents = 0;
        Map<Integer,Integer> parent = new HashMap<>();
        Map<Integer,Integer> rank = new HashMap<>();
        
        for(int[] stone : stones){
            int row = stone[0];
            int col = stone[1]+10000; // Padding it with a big number as explained above
            
            if(!parent.containsKey(row)){
                parent.put(row, row);
                connectedComponents++; // We found an unconnected stoe
            }
            
            if(!parent.containsKey(col)){
                parent.put(col, row);
            }
            
            int parentOfRow = find(row, parent);
            int parentOfCol = find(col, parent);
            
            if(parentOfRow != parentOfCol){
                if(rank.getOrDefault(parentOfRow, 0) >= rank.getOrDefault(parentOfCol, 0)){
                    parent.put(parentOfCol, parentOfRow);
                    rank.put(parentOfRow, rank.getOrDefault(parentOfRow, 0)+1);
                }else{
                    parent.put(parentOfRow, parentOfCol);
                    rank.put(parentOfCol, rank.getOrDefault(parentOfCol, 0)+1);
                }
                
                connectedComponents--; // Since we merged the components
            }
        }
        
        return numberOfStones - connectedComponents;
    }
    
    private int find(int node, Map<Integer,Integer> parent){
        if(parent.get(node) == node){
            return node;
        }
        
        parent.put(node, find(parent.get(node), parent));
        
        return parent.get(node);
    }




}