public class PartitionLabels {
	

	/** 

	A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

	**/


	/**

	Input: S = "ababcbacadefegdehijhklij"
	Output: [9,7,8]
	Explanation:
	The partition is "ababcbaca", "defegde", "hijhklij".
	This is a partition so that each letter appears in at most one part.
	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

	**/


	/**
	Solution, if we can find the first and last occurance of each of the characters, then we can do MERGE INTERVALS
	to see how man non opeverlapping intervals we can find. 
	Will work.

	But, the final will be non overlapping intervals. So if you see, we dont need the starting occurance, we need to ending occurance of each char.
	Then if we check how far each char extends and include everthing inside it. If someother char extends more in the partiation,
	then increase the partion size, just like

	JUMP GAME: https://leetcode.com/problems/jump-game/

	**/


	public List<Integer> partitionLabels(String S) {
        int[] endIndex = new int[256];
        char[] str = S.toCharArray();
        int n = str.length;
        
        // Get all the last index of all characters
        for(int i = 0; i < n; i++){
            endIndex[str[i]] = i;
        }
        
        // partition
        List<Integer> ans = new ArrayList<>();
        int maxLength = 0;
        int partitonSize = 0;
        for(int i =0 ; i < n; i++){
            maxLength = Math.max(maxLength, endIndex[str[i]]);
            partitonSize++;
            if(i == maxLength){
                ans.add(partitonSize);
                partitonSize = 0;
            }
        }
        
        return ans;
    }
}