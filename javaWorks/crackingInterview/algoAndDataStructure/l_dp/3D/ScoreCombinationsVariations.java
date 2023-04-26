public class ScoreCombinationsVariations{
    
    /**
        Write a program that takes a final score and scores for individual plays, 
        and returns the number of combinations of plays that result in the final score.
        
        For example, a 9 point score can be achieved in the following ways:
         scoring 7 points, followed by a 2 point play,
         scoring 6 points, followed by a 3 point play, and
         scoring 2 points, followed by a 7 point play.
    
          This is the same as achieving the given sum using the given coins.
        
          Now we are interested in its variations
    **/
  
  
    /** 
        Variant: Suppose the final score is given in the form (s,s'), i.ev Team 1 scored s points and Team 2 scored s' points. 
        How would you compute the number of distinct scoring sequences which result in this score? For example, if the 
        final score is (6,3) then Team 1 scores 3, Team 2 scores 3, Team 1 scores 3 is a scoring sequence which results in this score.
        
        
        Here the first D is s, the second D is s` and the third D is the socres.
        
        Recurssion is trying out the all ways to achive it as follows.
        
        Time: ????
    **/
  
      public int findSquences(int s, int sPrime, int[] availableScores){
        if(s == 0 && sPrime == 0){
          return 1;
        }
        
        int count = 0;
        for(int i : availableScores){
            count += findSquences(s-i,sPrime);
            count += findSquences(s,sPrime-i);
        }
        
        return count;
      }
  
      
      /**
          The DP solution is similar. 
      
          Time: O(K*s*sPrime)
      **/
        
      
        public int findSquences(int s, int sPrime, int[] availableScores){
          int[][] dp = new int[s+1][sPrime+1];
          int count = 0;
           
          for(int c : availableScores){ // For each score available
              for(int i = 0; i <= s; i++){
                for(int j = 0; i <= sPrime; j++){
                    // Check for bound overflow
                  
                    // Set "1" for the base condition
                  
                    // USE the same recurrence relation
                    dp[i][j] += dp[i-c][j] + dp[i][j-c];
                }
              }
          }
        
          return dp[s][sPrime];
        }
  
  
       /** 
        Variant: Suppose the final score is (s,s'). How would you compute the maximum number of times the 
        team that lead could have changed? 
        For example, if s =10 and s' = 6, the lead could have 
        changed 4 times: Team 1 scores 2, then Team 2 scores 3 (lead change), then Team 1 scores 2 (lead change), 
        then Team 2 scores 3 (lead change), then Team 1 scores 3 (lead change) followed by 3.
        
        Here the first D is s, the second D is s` and the third D is the socres.
        
        Recurssion is trying out the all ways as above, only the base case changes
        
        Time: ????
    **/
    
    public int findSquences(int s, int sPrime, int[] availableScores){
        // BAse case which returns 1
        
        int count = 0;
        for(int i : availableScores){
            count += findSquences(s-i,sPrime);
            count += findSquences(s,sPrime-i);
        }
        
        return count;
      }
    
    
    /**
        DP will be similar, only difference are as follows,
        Base case
        Since its a max, we may need one more iteration at the end
    
    
    **/
    
    
    public int findSquences(int s, int sPrime, int[] availableScores){
          int[][] dp = new int[s+1][sPrime+1];
          int count = 0;
           
          for(int c : availableScores){ // For each score available
              for(int i = 0; i <= s; i++){
                for(int j = 0; i <= sPrime; j++){
                    // Check for bound overflow
                  
                    // Set "1" for the base condition
                  
                    // USE the same recurrence relation
                    dp[i][j] += dp[i-c][j] + dp[i][j-c];
                }
              }
          }
        
          int maxChanges = 0;
            for (int i = 0; i <= s; i++) {
                for (int j = 0; j <= sPrime; j++) {
                    maxChanges = Math.max(maxChanges, dp[i][j]);
                }
            }
    
            return maxChanges;
   }
  
}
