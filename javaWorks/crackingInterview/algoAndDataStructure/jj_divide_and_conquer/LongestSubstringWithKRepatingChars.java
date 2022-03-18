public class LongestSubstringWithKRepatingChars{
	/**

		Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

		Example 1:

		Input: s = "aaabb", k = 3
		Output: 3
		Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
		Example 2:

		Input: s = "ababbc", k = 2
		Output: 5
		Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

	**/

	/**
		Approach: 1 brute force. Form all substring and validate, O(N^2) & O(1)
	**/

	/**
		Approach: 2 Divide and conqure, O(N^2) & O(N)
	**/


	public int longestSubstring(String s, int k) {
       return divideAndConqure(s,k,0,s.length());
    }
    
    private int divideAndConqure(String s, int k, int start, int end){
        if(end < k){
            return 0;
        }
        int[] map = new int[26];
        for(int i = start; i < end; i++)
            map[s.charAt(i)-'a']++;
        
        for(int i = start; i < end; i++){
            if(map[s.charAt(i)-'a'] < k){
                // divide at the invalid index
                // Make sure we divide before and after the invalid char substring
                int midEnd = i+1;
                while(midEnd < end && map[s.charAt(midEnd)-'a'] < k)
                    midEnd++;
                return Math.max(divideAndConqure(s,k,start,i),divideAndConqure(s,k,midEnd,end));
            }
        }
        
        // If no invalid chars, return the length
        return (end - start);
    }


    /**
		Approach: 3 Sliding window, O()
    **/

	public int longestSubstring(String s, int k) {
	    char[] str = s.toCharArray();
	    int[] counts = new int[26];
	    int h, i, j, idx, max = 0, unique, noLessThanK;
	    
	    for (h = 1; h <= 26; h++) {
	        Arrays.fill(counts, 0);
	        i = 0; 
	        j = 0;
	        unique = 0;
	        noLessThanK = 0;
	        while (j < str.length) {
	            if (unique <= h) {
	                idx = str[j] - 'a';
	                if (counts[idx] == 0)
	                    unique++;
	                counts[idx]++;
	                if (counts[idx] == k)
	                    noLessThanK++;
	                j++;
	            }
	            else {
	                idx = str[i] - 'a';
	                if (counts[idx] == k)
	                    noLessThanK--;
	                counts[idx]--;
	                if (counts[idx] == 0)
	                    unique--;
	                i++;
	            }
	            if (unique == h && unique == noLessThanK)
	                max = Math.max(j - i, max);
	        }
	    }
	    
	    return max;
	}



	/**
		Approach: 4 DP, O(UniqueCharCout * N)
		Have a map for each unique characters count from backward. Like at every index we need to know how many of that are to its right.
		Then iterate from front. Start couting each chars when we go forward. Also record start and end.
		
		Now at each index find if the character is a valid or not using front and back counts.
		If not valid, reset froward count, start and end.
		
		To create the backward count, we need O(UniqueCharCout * N). Forward iteration is O(n)
    **/

}