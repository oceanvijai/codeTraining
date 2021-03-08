public class StringsAreClose {
	/**

		Two strings are considered close if you can attain one from the other using the following operations:

		Operation 1: Swap any two existing characters.
		For example, abcde -> aecdb
		Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
		For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
		You can use the operations on either string as many times as necessary.

		Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise



		Example 1:

		Input: word1 = "abc", word2 = "bca"
		Output: true
		Explanation: You can attain word2 from word1 in 2 operations.
		Apply Operation 1: "abc" -> "acb"
		Apply Operation 1: "acb" -> "bca"
		Example 2:

		Input: word1 = "a", word2 = "aa"
		Output: false
		Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
		Example 3:

		Input: word1 = "cabbba", word2 = "abbccc"
		Output: true
		Explanation: You can attain word2 from word1 in 3 operations.
		Apply Operation 1: "cabbba" -> "caabbb"
		Apply Operation 2: "caabbb" -> "baaccc"
		Apply Operation 2: "baaccc" -> "abbccc"
		Example 4:

		Input: word1 = "cabbba", word2 = "aabbss"
		Output: false
		Explanation: It is impossible to attain word2 from word1, or vice versa, in any amount of operations.

	**/

	/**

		Approach: we dont have to form the string since we only need to return True/False
					To find, if we can form word1 to word2, we need to check a couple of things
					1.	If the chars set in word1 and word2 are the same
						So we can perform as many swaps to get the required string
					2.	If the frequecies of word1 and word2 are the same
						So we can perform operation 2 as many time to get the result


	**/


	public boolean closeStrings(String word1, String word2) {
        
        if(word1.length() != word2.length()){
            return false;
        }
        
        // get the characters and their frequencies
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for(int i = 0; i < word1.length(); i++){
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0)+1);
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0)+1);
        }
        
        // Check if we have the same set of characters
        if(!map1.keySet().equals(map2.keySet())){
            return false;
        }
        
        // Check if we have the same set of frequencies
        return map1.values().stream().collect(Collectors.toSet())
            .equals(map2.values().stream().collect(Collectors.toSet()));
    }
}