public class MaximalSquare{
	// Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

	/*
		1 0 1 0 0
		1 0 1 1 1
		1 1 1 1 1
		1 0 0 1 0

	*/

	// Output: 4

	public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        
        int[][] dp = new int[matrix.length][matrix[0].length];
        
        /* Set first column*/
        for(int i = 0; i < matrix.length; i++) 
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0; 
      
        /* Set first row*/
        for(int j = 0; j < matrix[0].length; j++) 
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0; 
          
        /* Construct other entries of S[][]*/
        for(int i = 1; i < matrix.length; i++) 
        { 
            for(int j = 1; j < matrix[0].length; j++) 
            { 
                if(matrix[i][j] == '1')  
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1; 
                else
                    dp[i][j] = 0; 
            }  
        } 
        
        int max = 0;
        for(int i = 0; i < matrix.length; i++) 
        { 
            for(int j = 0; j < matrix[0].length; j++) 
            { 
                max = Math.max(dp[i][j], max);
            }  
        }
        
        return max * max;
    }
}