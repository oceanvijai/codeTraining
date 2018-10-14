public class LongestIncreasingSubsequence{

    public int longestSubsequenceLength(final List<Integer> A) {
        
        int[] inc = new int[A.size()];
        Arrays.fill(inc, 1);
        for(int i =1; i < A.size(); i++){
            for(int j =0; j < i; j++){
                if(A.get(j) < A.get(i) && inc[i] < inc[j] + 1){
                    inc[i] = inc[j]+1;
                }
            }
        }

        int max = 0;
        for(int i=0; i < A.size(); i++){
            if(inc[i] > max){
                max = inc[i];
            }
        }
        return max > 0 ? max : 0;


    }

}