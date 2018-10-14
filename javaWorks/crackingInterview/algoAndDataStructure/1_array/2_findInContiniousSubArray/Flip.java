public class Flip {
    /**
     * You are given a binary string(i.e. with characters 0 and 1) S consisting of
     * characters S1, S2, …, SN. In a single operation, you can choose two indices L
     * and R such that 1 ≤ L ≤ R ≤ N and flip the characters SL, SL+1, …, SR. By
     * flipping, we mean change character 0 to 1 and vice-versa.
     * 
     * Your aim is to perform ATMOST one operation such that in final string number
     * of 1s is maximised. If you don’t want to perform the operation, return an
     * empty array. Else, return an array consisting of two elements denoting L and
     * R. If there are multiple solutions, return the lexicographically smallest
     * pair of L and R.
     */

     /**
      * S = 010
        Pair of [L, R] | Final string
        _______________|_____________
        [1 1]          | 110
        [1 2]          | 100
        [1 3]          | 101
        [2 2]          | 000
        [2 3]          | 001
        We see that two pairs [1, 1] and [1, 3] give same number of 1s in final string. So, we return [1, 1].
      */


      // Ok, this may look like we can use a two pointer or window technique
      // It true, we can do windowing here as well
      // But the below is some better practise

      // We can do something like this,
      
      // Observation 1:
      // So when flipping, we have to make 1 -> 0 and 0 -> 1.
      // So we care more about the number of 0's in the string, because they produce the 1 in the finals

      // // Observation 2
      // From the above observation we decude some more like,
      // if lets say A = number of O's
      //             B = number of 1's
      //             Then A - B will tell us how man ones we can produce
      //             So, lets say we make i to -1, i.e all the elements in B to -1
      //            Then it natuarally produces  A - B at any point in the array

      // So, lets take the string and make 0's as 1 and 1 as -1.

      // Once we do the above, we want to find a sub array with max 1's on flip
      // forget flip, since we made it already
      // So, we need a sub array with max 1 from start to end
      // this is similaire to Kadane, where we need a max sum from start to end


      public ArrayList<Integer> flip(String A) {

        int n = A.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = A.charAt(i) == '0' ? 1 : -1;
        }

        // Kadane max sum
        int maxStart = 0;
        int maxEnd = 0;

        int start = 0;
        int end = 0;
        int maxValueSoFar = 0;

        int sum = 0;
        int i = 0;
        while (i < n) {
            sum += dp[i];
            if (dp[i] > sum) {
                sum = dp[i];
                start = i;
                end = i;
            }

            if (sum > maxValueSoFar) {
                maxValueSoFar = sum;
                maxStart = start;
                maxEnd = i;
            }

            i++;
        }

        if (maxValueSoFar <= 0) {
            return new ArrayList<Integer>();
        }

        // Just packing up the result
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(maxStart + 1);
        ans.add(maxEnd + 1);

        return ans;
    }

}