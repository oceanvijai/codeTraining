public class AntiDiagonals {

    // Give a N*N square matrix, return an array of its anti-diagonals. Look at the
    // example for more details.

    /**
     * Input: 	

        1 2 3
        4 5 6
        7 8 9

        Return the following :

        [ 
        [1],
        [2, 4],
        [3, 5, 7],
        [6, 8],
        [9]
        ]
     */

    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> A) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        int cross = (A.size() * 2) - 1;
        for (int c = 0; c <= cross; c++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i <= c; i++) {
                int j = c - i;
                if (i < A.size() && j < A.size()) {
                    row.add(A.get(i).get(j));
                }
            }
            if (row.size() > 0) {
                ans.add(row);
            }
        }

        return ans;

    }


    // Another bad ass solution 
    // Create each level and pick it up in the fly for update

    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    for(int i = 0; i < a.size()*2-1; i++)
	        res.add(new ArrayList<Integer>());
	        
	    for(int i = 0; i < a.size(); i++){
	        for(int j = 0; j < a.get(0).size(); j++){
	            res.get(i+j).add(a.get(i).get(j));
	        }
	    }
	    
	    return res;
	}

}