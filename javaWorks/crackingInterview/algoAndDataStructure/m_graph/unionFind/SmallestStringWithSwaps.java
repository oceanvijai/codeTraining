public class SmallestStringWithSwaps{
	/**

		You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
		You can swap the characters at any pair of indices in the given pairs any number of times.
		Return the lexicographically smallest string that s can be changed to after using the swaps.


		Example 1:

		Input: s = "dcab", pairs = [[0,3],[1,2]]
		Output: "bacd"
		Explaination: 
		Swap s[0] and s[3], s = "bcad"
		Swap s[1] and s[2], s = "bacd"
		Example 2:

		Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
		Output: "abcd"
		Explaination: 
		Swap s[0] and s[3], s = "bcad"
		Swap s[0] and s[2], s = "acbd"
		Swap s[1] and s[2], s = "abcd"
		Example 3:

		Input: s = "cba", pairs = [[0,1],[1,2]]
		Output: "abc"
		Explaination: 
		Swap s[0] and s[1], s = "bca"
		Swap s[1] and s[2], s = "bac"
		Swap s[0] and s[1], s = "abc"

	**/



	/**
		Approach : The swap pairs form a graph and in turn connected components.
		So find the conected components, sort the chars in the component and palce them in order

	**/


	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // Form the connected components using union find or DFS
        int n = s.length();
        Map<Integer, Integer> parent = new HashMap<>();
        for(List<Integer> pair : pairs){
            int indx1 = pair.get(0);
            int indx2 = pair.get(1);
            
            int parentOfindx1 = find(indx1, parent);
            int parentOfindx2 = find(indx2, parent);
            
            if(parentOfindx1 < parentOfindx2){
                parent.put(parentOfindx2, parentOfindx1);
            }else{
                parent.put(parentOfindx1, parentOfindx2);
            }
        }
        
        // group the chars component wise
        Map<Integer, PriorityQueue<Character>> mapper = new HashMap<>();
        for(int i = 0; i < n; i++){
            int root = find(i, parent);
            mapper.putIfAbsent(root, new PriorityQueue<>());
            mapper.get(root).offer(s.charAt(i));
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int root = find(i, parent);
            sb.append(mapper.get(root).poll());
        }
        
        return sb.toString();
    }
    
    private int find(int indx, Map<Integer, Integer> parent){
        if(!parent.containsKey(indx)){
            parent.put(indx, indx);
            return indx;
        }
        
        if(parent.get(indx) == indx){
            return indx;
        }
        parent.put(indx, find(parent.get(indx), parent));
        return parent.get(indx);
    }



}