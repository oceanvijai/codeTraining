public class StoneGame4{
  /**
    Alice and Bob take turns playing a game, with Alice starting first.

    Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
    
    Also, if a player cannot make a move, he/she loses the game.
    
    Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.
    
     
    
    Example 1:
    
    Input: n = 1
    Output: true
    Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
    Example 2:
    
    Input: n = 2
    Output: false
    Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
    Example 3:
    
    Input: n = 4
    Output: true
    Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
  **/

  /**
    The intution is, if by anychange or choise we make our oponent to produce false. The current player can win the game. 
    So both Alice and bob will try to do it.

    So in first chance, Alice will make decesions and see if any of the decision will force Bob to produce false. If so Alice wins.
    On the other hand, in the successive recursions, Bob will also try all choices to make sure it reuturns only True from it side. If its choices are exhaused, it will produce false.



  **/


  public boolean winnerSquareGame(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return solve(n, 0, dp);
    }

    private boolean solve(int n, int currentIndex, int[] dp){
        if(currentIndex > n){
            return false;
        }

        if(dp[currentIndex] != -1){
            return dp[currentIndex] == 1;
        }

        for(int i = 1; currentIndex+(i*i) <= n; i++){
            if(!solve(n, currentIndex + (i*i), dp)){
                dp[currentIndex] = 1;
                return true;
            }
        }

        dp[currentIndex] = 0;
        return false;
    }


  
}
