public class StoneGame6{
  /**
    Alice and Bob take turns playing a game, with Alice starting first.

    There are n stones in a pile. On each player's turn, they can remove a stone from the pile and receive points based on the stone's value. Alice and Bob may value the stones differently.
    
    You are given two integer arrays of length n, aliceValues and bobValues. Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value the ith stone.
    
    The winner is the person with the most points after all the stones are chosen. If both players have the same amount of points, the game results in a draw. Both players will play optimally. Both players know the other's values.
    
    Determine the result of the game, and:
    
    If Alice wins, return 1.
    If Bob wins, return -1.
    If the game results in a draw, return 0.
 

      Example 1:
      
      Input: aliceValues = [1,3], bobValues = [2,1]
      Output: 1
      Explanation:
      If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
      Bob can only choose stone 0, and will only receive 2 points.
      Alice wins.
      Example 2:
      
      Input: aliceValues = [1,2], bobValues = [3,1]
      Output: 0
      Explanation:
      If Alice takes stone 0, and Bob takes stone 1, they will both have 1 point.
      Draw.
      Example 3:
      
      Input: aliceValues = [2,4,3], bobValues = [1,6,7]
      Output: -1
      Explanation:
      Regardless of how Alice plays, Bob will be able to have more points than Alice.
      For example, if Alice takes stone 1, Bob can take stone 2, and Alice takes stone 0, Alice will have 6 points to Bob's 7.
      Bob wins.

  **/


  /**
    The first intution says that we can do it in nlogn by forming a queue and pick one at each iteration.

    But Which criteria to use for sorting the queue. We can get the diff or both the values of alice and bob for each store and use it.
    But it might not work since it might be biased to one side.

   So lets if we can get a pattern

   aliceValues=[5, 99999]
  bobValues=[1000000, 3]
  Alice prefers Stone1
  
  aliceValues=[5, 1000000]
  bobValues=[1000000, 3]
  Alice prefers Stone1
  
  aliceValues=[5, 1000001]
  bobValues=[1000000, 3]
  Alice prefers Stone1
  
  aliceValues=[5, 1000002]
  bobValues=[1000000, 3]
  Alice doesn't have a favorite stone
  
  aliceValues=[5, 1000003]
  bobValues=[1000000, 3]
  !!Alice prefers Stone2!!

  We can see if the sum of values of each is greater, the player pickes it up.
  So we will use that for our sorting.

  **/


  public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] queue = new int[n][3]; // sorted queue
        for(int i = 0; i < n; i++){
            queue[i] = new int[]{bobValues[i] - aliceValues[i], aliceValues[i], bobValues[i]};
        }

        Arrays.sort(queue, (a,b)-> b[0]-a[0]);

        int aliceScore = 0, bobScore = 0, i = 0;
        boolean alice = true;
        while(i < n){
            if(alice){
                aliceScore += queue[i][1];
            }else{
                bobScore += queue[i][2];
            }
            i++;
            alice = !alice;
        }

        System.out.println("->"+aliceScore + " : "+ bobScore);

        if(aliceScore == bobScore){
            return 0;
        }else if(aliceScore > bobScore){
            return 1;
        }else{
            return -1;
        }

    }

  
}
