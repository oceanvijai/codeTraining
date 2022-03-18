public SortMartixDiagonally{
	/**

		A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

		Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.



		Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
		Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]

	**/


	/**
		Itertate each diagnal and put the elements in the heap. And pop them back to the incides.
		So N*M items and N*M push and pops into the heap

		So, O(N*M Min*(N,M)), Min(N,M) the size of a biggest diagonal 

	**/




	int[][] mat;
    int n, m;
    
    // Sort the current diagonal
    public void sortDiagonal(int i, int j) {
        // max heap -> to keep max element always on top
        Queue<Integer> diagonal = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        // store the current diagonal 
        // in the heap
        while (i < n && j < m) {
            diagonal.add(mat[i][j]);
            ++i;
            ++j;    
        }

        // push the sorted values 
        // back into the matrix
        while (i > 0 && j > 0) {
            --i;
            --j;
            mat[i][j] = diagonal.remove();    
        }
    }
    
    public int[][] diagonalSort(int[][] mat) {
        this.mat = mat;
        n = mat.length;
        m = mat[0].length;
                
        // sort all diagonals 
        // in the lower left corner
        for (int i = 0; i < n; ++i) {
            sortDiagonal(i, 0);       
        } 
        // sort all diagonals 
        // in the upper right corner
        for (int j = 0; j < m; ++j) {
            sortDiagonal(0, j);  
        } 
        return mat;
    }



}