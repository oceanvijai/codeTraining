public class FindSubSequenceWithSum {
    // Given an array find a subsequence that will add up to a sum
    // return its start and end

    // The trick is have a continiousSum and remember where we saw that sum in the
    // hashMap
    // When we see a zero or the same sum again, we know the
    // subsequence starts from the index in the hashMap and to the current index

    // Ex: given array {1 ,2 ,-2 ,4 ,-4} and sum is 0
    // then {2 ,-2 ,4 ,-4}

    public ArrayList<Integer> subSeqSum(ArrayList<Integer> a) {
        int start = 0, end = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
            if (map.containsKey(sum)) {
                int currStart = map.get(sum), currEnd = i;
                if (currEnd - currStart + 1 > end - start + 1) {
                    start = currStart;
                    end = currEnd;
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = start + 1; i <= end; i++) {
            result.add(a.get(i));
        }
        return result;
    }


    // Same with a given sum

    // Now we have two case
        // 1.   We find the sum straight away 
        //      - that means everthing before can be counted in the sum
        //      - Ex: k  3; arr =  1,0,-1,5,-2,5 -> then {1,0,-1,5,-2}
        //2.    We find (sum - k) in the map
        //      - that means, the value (sum - k) is already encountered, so find the next index where it was seen
        //      - Ex: k  3; arr =  8,1,1,1 -> (11-3) = 8 is found in index 0, so take everthing after that {1,1,1}


    public int maxSubArrayLen(int[] nums, int k) {
        
        Map<Integer,Integer> map = new HashMap<>();
        
                
        int maxLength = 0;
        int sum = 0;
        for(int i =0; i < nums.length; i++){
            sum = sum + nums[i];
            System.out.println("Sum:" + sum);
            if(sum == k){
                if(maxLength < i+1){
                    maxLength = i+1;
                }
            }else if(map.get(sum-k) != null){
                int len = i - (map.get(sum-k));
                maxLength = Math.max(maxLength, len);
            }
            if( map.get(sum) == null){
                map.put(sum,i);
            }
        }
        
        return maxLength;
        
    }

}