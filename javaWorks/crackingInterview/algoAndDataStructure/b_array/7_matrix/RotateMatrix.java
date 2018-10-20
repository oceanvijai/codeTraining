public class RotateMatrix{


    public void rotate(ArrayList<ArrayList<Integer>> a) {
        
        int n = a.size()-1;
        
        for(int row = 0; row <= n/2; row++){
            for(int col = row; col < n-row; col++){
                int t = a.get(row).get(col);
                int r = a.get(col).get(n-row);
                int b = a.get(n-row).get(n-col);
                int l = a.get(n-col).get(row);
                
                a.get(col).set(n-row,t);
                a.get(n-row).set(n-col,r);
                a.get(n-col).set(row,b);
                a.get(row).set(col,l);
            }
        }
        
    }

}