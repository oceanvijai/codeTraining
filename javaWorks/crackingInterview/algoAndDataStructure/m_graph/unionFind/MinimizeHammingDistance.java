public class MinimizeHammingDistance {

	/**
		You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps 
		where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times and in any order.

		The Hamming distance of two arrays of the same length, source and target, is the number of positions 
		where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where 
		source[i] != target[i] (0-indexed).

		Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.


		Example 1:

		Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
		Output: 1
		Explanation: source can be transformed the following way:
		- Swap indices 0 and 1: source = [2,1,3,4]
		- Swap indices 2 and 3: source = [2,1,4,3]
		The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
		Example 2:

		Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
		Output: 2
		Explanation: There are no allowed swaps.
		The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
		Example 3:

		Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
		Output: 0

	**/

	/**
		Approach: Use uion find

		* Group all the indices by parent relationship
		* Iterate though the source and tagert array
		* Put each item in the soruce array in a seperate bucket based on the parent index value
		* Also make sure, we count each values in the bucket

		* When we do the above, we can also decrement the values count for the value in the target array
		* What we are essentially doing is cancelling each value in source array vs tagert array

		* Ideally we should be left with only 0 for all values count. If not count them as difference
	**/


    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        
        // We can use union find
        // Form the parent releationship
        int[] parent = new int[source.length];
        for(int i = 0; i < source.length; i++) parent[i] = i;
        
        for(int[] swap : allowedSwaps){
            int index1 = swap[0];
            int index2 = swap[1];
            
            int parentOf1 = find(index1, parent);
            int parentOf2 = find(index2, parent);
            
            if(parentOf1 == parentOf2){
                continue;
            }
                        
            if(parentOf1 < parentOf2){
                parent[parentOf2] = parentOf1;
            }else{
                parent[parentOf1] = parentOf2;
            }
        }
        
        Map<Integer, Map<Integer, Integer>> countMap = new HashMap<>();
        for(int i = 0; i < source.length; i++){
            int p = find(i, parent);
            
            Map<Integer, Integer> parentSet =  countMap.computeIfAbsent(p, k -> new HashMap<>());
            // incement the source value count 
            parentSet.put(source[i], parentSet.getOrDefault(source[i], 0)+1);
            // Decrement the target value count
            parentSet.put(target[i], parentSet.getOrDefault(target[i], 0)-1);
        }
        
        // We incremented and decremented source and taget together, 
        // if all source and value are same, then we expect ZERO in all the counts
        int ans = 0;
        for(Map<Integer, Integer> parentSetCounts : countMap.values()){
            int count = 0;
            for(Integer valueCount : parentSetCounts.values()){
                if(valueCount != 0){
                    count += Math.abs(valueCount);
                }
            }
            
            count = count / 2; // since one different values produce 2
            ans += count;
        }
        
        return ans;
        
    }
    
    private int find(int index, int[] parent){
        
        if(parent[index] != index){
            parent[index] =  find(parent[index], parent);
        }
        
        return parent[index];
    }
}