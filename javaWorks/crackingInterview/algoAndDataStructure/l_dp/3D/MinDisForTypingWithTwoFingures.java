public class MinDisForTypingWithTwoFingures{
    /**
      Minimum Distance to Type a Word Using Two Fingers
        
      A B C D E F
      G H I J K L
      M N O P Q R
      S T U V W X
      Y Z

      You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at some coordinate.

      For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at coordinate (0, 1), 
      the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
      Given the string word, return the minimum total distance to type such string using only two fingers.
      
      The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.

      Note that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers 
      do not have to start at the first letter or the first two letters.


    **/


    /**
      Approach 1: Recursion where at every level we decide to with fingure 1 or fingure 2
      O(2^n)

    **/


    Map<Character, int[]> gridMap;
    public int minimumDistance(String word) {
        gridMap = getGridMap();
        return solve(0, word,-1,-1);
    }

    private int solve(int currentIndex, String word, int ff_currentCharIndex, int sf_currentCharIndex){
        if(currentIndex >= word.length()){
            return 0;
        }

        int firstFingerPath = getDistance(ff_currentCharIndex, currentIndex, word) 
                                + solve(currentIndex+1, word, currentIndex, sf_currentCharIndex);

        int secondFingerPath = getDistance(sf_currentCharIndex, currentIndex, word) 
                                + solve(currentIndex+1, word, ff_currentCharIndex, currentIndex);

        return Math.min(firstFingerPath, secondFingerPath);
    }

    private int getDistance(int currentCharIndex, int targetCharIndex, String word){
        if(currentCharIndex == -1){
            return 0;
        }

        int[] c1 = gridMap.get(word.charAt(currentCharIndex));
        int[] c2 = gridMap.get(word.charAt(targetCharIndex));
        
        return Math.abs((c1[0]-c2[0]))+Math.abs((c1[1]-c2[1]));
    }

    private Map<Character, int[]> getGridMap(){
        Map<Character, int[]> gridMap = new HashMap<>();
        gridMap.put('A', new int[]{0,0});
        gridMap.put('B', new int[]{0,1});
        gridMap.put('C', new int[]{0,2});
        gridMap.put('D', new int[]{0,3});
        gridMap.put('E', new int[]{0,4});
        gridMap.put('F', new int[]{0,5});
        gridMap.put('G', new int[]{1,0});
        gridMap.put('H', new int[]{1,1});
        gridMap.put('I', new int[]{1,2});
        gridMap.put('J', new int[]{1,3});
        gridMap.put('K', new int[]{1,4});
        gridMap.put('L', new int[]{1,5});
        gridMap.put('M', new int[]{2,0});
        gridMap.put('N', new int[]{2,1});
        gridMap.put('O', new int[]{2,2});
        gridMap.put('P', new int[]{2,3});
        gridMap.put('Q', new int[]{2,4});
        gridMap.put('R', new int[]{2,5});
        gridMap.put('S', new int[]{3,0});
        gridMap.put('T', new int[]{3,1});
        gridMap.put('U', new int[]{3,2});
        gridMap.put('V', new int[]{3,3});
        gridMap.put('W', new int[]{3,4});
        gridMap.put('X', new int[]{3,5});
        gridMap.put('Y', new int[]{4,0});
        gridMap.put('Z', new int[]{4,1});
        return gridMap;
    }


    /**
        Approach 2: top down DP. 3D DP. Same as above with DP memorisation.
        Time: N^3
        Space: N^3 + N 

        But this also Times out. So we need a better approach.

    **/


    Map<Character, int[]> gridMap;
    public int minimumDistance(String word) {
        int n = word.length();
        gridMap = getGridMap();
        int[][][] dp = new int[n+1][n+1][n+1];
        return solve(0, word,-1,-1, dp);
    }

    private int solve(int currentIndex, String word, int ff_currentCharIndex, int sf_currentCharIndex, int[][][] dp){
        if(currentIndex >= word.length()){
            return 0;
        }

        if(ff_currentCharIndex != -1 && sf_currentCharIndex != -1 
            && dp[currentIndex][ff_currentCharIndex][sf_currentCharIndex] != 0){
                return dp[currentIndex][ff_currentCharIndex][sf_currentCharIndex];
        }

        int firstFingerPath = getDistance(ff_currentCharIndex, currentIndex, word) 
                                + solve(currentIndex+1, word, currentIndex, sf_currentCharIndex, dp);

        int secondFingerPath = getDistance(sf_currentCharIndex, currentIndex, word) 
                                + solve(currentIndex+1, word, ff_currentCharIndex, currentIndex, dp);
        int result   = Math.min(firstFingerPath, secondFingerPath);
        if(ff_currentCharIndex != -1 && sf_currentCharIndex != -1){
            dp[currentIndex][ff_currentCharIndex][sf_currentCharIndex] = Math.min(firstFingerPath, secondFingerPath);
        }

        
        return result;
    }

    private int getDistance(int currentCharIndex, int targetCharIndex, String word){
        if(currentCharIndex == -1){
            return 0;
        }

        int[] c1 = gridMap.get(word.charAt(currentCharIndex));
        int[] c2 = gridMap.get(word.charAt(targetCharIndex));
        
        return Math.abs((c1[0]-c2[0]))+Math.abs((c1[1]-c2[1]));
    }

    private Map<Character, int[]> getGridMap(){
        Map<Character, int[]> gridMap = new HashMap<>();
        gridMap.put('A', new int[]{0,0});
        gridMap.put('B', new int[]{0,1});
        gridMap.put('C', new int[]{0,2});
        gridMap.put('D', new int[]{0,3});
        gridMap.put('E', new int[]{0,4});
        gridMap.put('F', new int[]{0,5});
        gridMap.put('G', new int[]{1,0});
        gridMap.put('H', new int[]{1,1});
        gridMap.put('I', new int[]{1,2});
        gridMap.put('J', new int[]{1,3});
        gridMap.put('K', new int[]{1,4});
        gridMap.put('L', new int[]{1,5});
        gridMap.put('M', new int[]{2,0});
        gridMap.put('N', new int[]{2,1});
        gridMap.put('O', new int[]{2,2});
        gridMap.put('P', new int[]{2,3});
        gridMap.put('Q', new int[]{2,4});
        gridMap.put('R', new int[]{2,5});
        gridMap.put('S', new int[]{3,0});
        gridMap.put('T', new int[]{3,1});
        gridMap.put('U', new int[]{3,2});
        gridMap.put('V', new int[]{3,3});
        gridMap.put('W', new int[]{3,4});
        gridMap.put('X', new int[]{3,5});
        gridMap.put('Y', new int[]{4,0});
        gridMap.put('Z', new int[]{4,1});
        return gridMap;
    }


    /**
        Approach 3: We still use 3D DP. But take advantage of that this is a problem based on characters ans also only upper case.
        So total we have 26 upper case characters. So we can do something like this.
        

    **/


    

  
}
