public int maxSubarraySumCircular(int[] A) {



        /*
            Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

            Here, a circular array means the end of the array connects to the beginning of the array.  
            (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

            Also, a subarray may only include each element of the fixed buffer A at most once.  
            (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j 
            with k1 % A.length = k2 % A.length.)


            Example 1:

            Input: [1,-2,3,-2]
            Output: 3
            Explanation: Subarray [3] has maximum sum 3
            Example 2:

            Input: [5,-3,5]
            Output: 10
            Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
            Example 3:

            Input: [3,-1,2,-1]
            Output: 4
            Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
            Example 4:

            Input: [3,-2,2,-3]
            Output: 3
            Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
            Example 5:

            Input: [-2,-3,-1]
            Output: -1
            Explanation: Subarray [-1] has maximum sum -1

        */
        
        
        // we have two possibility
        // 1. the max sum is found in normal array and found using normal kadane
        // 2. the max sum is found in the circular array
        
        // possibility 1 is stratight forward, so we find it and call it (K)
        // In order to find the max in circular array, we get the (total sum) - (min sub array)
        // imagine if the sum is like this => 1,2,3] -9,-8,-7[5,8,9
        // then, total sum = 1+2+3-9-8-7+5+8+9, max sum = 1+2+3+5+8+9, min sum = -9-8-7
        // So max sum = total sum - min sum
        
        // Finding total sum is straight forward
        // finding min sum can be achieved in two ways, either
        // apply kadane and modify it to get min sum instead of max sum
        // Or, multiple all the numbers by -1 and then apply regualr kadane, which will give min sum
        // which needs NOT to be converted into -ve since we jsut need to magnitude be subracted form 
        // the total sum.
        // We get the CK as CK = total sum + min sum
        
        // We compare K and CK and return the max
        
        int n = A.length;
        int K = kadane(A);
        
        int totalSum = 0;
        for(int i = 0; i < n; i++){
            totalSum = totalSum + A[i];
        }
        
        int[] negArray = new int[A.length];
        for(int i = 0; i < n; i++){
            negArray[i] = -1 * A[i];
        }
        
        int CK = totalSum + kadane(negArray);
        
        if(CK == 0){
            return K;
        }else{
            return Math.max(K,CK);
        }
        
     }
    
    private int kadane(int[] array){
        int max = array[0];
        int sum = max;
        
        for(int i = 1; i < array.length; i++){
            sum = sum + array[i];
            
            if(array[i] > sum){
                sum = array[i];
            }
            
            max = Math.max(sum, max);
        }
        return max;
    }