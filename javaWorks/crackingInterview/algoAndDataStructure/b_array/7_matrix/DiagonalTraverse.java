public class DiagonalTraverse {
	
	/**
		Given a matrix of M x N elements (M rows, N columns), 
		return all elements of the matrix in diagonal order as shown in the below image.


		Example:

		Input:
		[
		 [ 1, 2, 3 ],
		 [ 4, 5, 6 ],
		 [ 7, 8, 9 ]
		]

		Output:  [1,2,4,7,5,3,6,8,9]

	**/


	/**

		Approach 1: time: O(n*m) space: (n*m)
			- create buckets and fill as we go row by row
			- pick from bucket to form the ans. Reverse if required


		Approach 2: time: O(n*m) space: (MIN(n,m))
			- Iterate diagonally and add to a tmp list. Append to the ans at the end of iteration
			  Reverse if required


		Approach 3: time: O(n*m) space: (1)
			- Simulation
			  - Iterate exactly how we visualize it
			  - iterate form top to bottom and bottom to top aletrnatively
			  - tricky part is to find the head at the start of every iteration
			  	- calculate the head from previous iteration final state

		Approach 4: time: O(n*m) space: (MIN(n,m))
			- Binary tree 
			- Not implemented, 
			- but you can imagine each cell will produce a right and left when viewed diagonally

	**/


	// Approach 1

    public int[] findDiagonalOrder(int[][] matrix) {
        
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        // create buckets
        List<List<Integer>> tmp = new ArrayList<>();
        tmp.add(new ArrayList<>());
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int bucket = i + j;
                if(tmp.size() < bucket+1){
                    tmp.add(new ArrayList<>());
                }
                
                tmp.get(bucket).add(matrix[i][j]);
            }
        }
        
        
        // pick from buckets
        int[] ans = new int[n*m];
        boolean reverse = true;
        int j = 0;
        for(int i=0; i < n+m-1; i++){
            List<Integer> bucket = tmp.get(i);
            if(reverse){
                Collections.reverse(bucket);
            }
            for(int e : bucket){
                ans[j] = e;
                j++;
            }
            
            reverse = !reverse;
        }
        
        
        return ans;
    }


    // Approch 2


    public int[] findDiagonalOrder(int[][] matrix) {
        
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        
        int N = matrix.length;
        int M = matrix[0].length;
        
        // The two arrays as explained in the algorithm
        int[] result = new int[N*M];
        int k = 0;
        ArrayList<Integer> intermediate = new ArrayList<Integer>();
        
        // We have to go over all the elements in the first
        // row and the last column to cover all possible diagonals
        for (int diagnol = 0; diagnol < N + M - 1; diagnol++) {
            
            // Clear the intermediate array every time we start
            // to process another diagonal
            intermediate.clear();
            
            // We need to figure out the "head" of this diagonal
            // The elements in the first row and the last column
            // are the respective heads.
            int r = diagnol < M ? 0 : diagnol - M + 1;
            int c = diagnol < M ? diagnol : M - 1;
            
            // Iterate until one of the indices goes out of scope
            // Take note of the index math to go down the diagonal
            while (r < N && c > -1) {
                
                intermediate.add(matrix[r][c]);
                ++r;
                --c;
            }
                
            // Reverse even numbered diagonals. The
            // article says we have to reverse odd 
            // numbered articles but here, the numbering
            // is starting from 0 :P
            if (diagnol % 2 == 0) {
                Collections.reverse(intermediate);
            }
            
            for (int i = 0; i < intermediate.size(); i++) {
                result[k++] = intermediate.get(i);
            }
        }
        return result;
    }



    // Approach 3


    public int[] findDiagonalOrder(int[][] matrix) {
        
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        
        int N = matrix.length;
        int M = matrix[0].length;
        
        int[] result = new int[N*M];
        int k = 0;
        boolean reverse = true;
       
        int r = 0;
        int c = 0;
        int i = 0;
        for (int diagnol = 0; diagnol < N + M - 1; diagnol++) {
            
           if(reverse == false){
               r = diagnol < M ? 0 : diagnol - M +1;
               c = diagnol < M ? diagnol : M-1;
           }else{
               r = diagnol < N ? r : N-1;
               c = diagnol < N ? 0 : c+2;
           }
            
            
            if(reverse == false){
                while (r < N && c > -1 && i < N*M) {
                    result[i] = matrix[r][c];
                    r++;
                    c--;
                    i++;
                }
            }else{
                while (r >= 0 && c < M && i < N*M) {
                    result[i] = matrix[r][c];
                    r--;
                    c++;
                    i++;
                }
            }
            
            
            
            reverse = !reverse;
                
        }
        return result;
    }



}