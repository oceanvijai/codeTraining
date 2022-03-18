public class MaximumXOR{

	/**
		Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.

		Input: nums = [3,10,5,25,2,8]
		Output: 28
		Explanation: The maximum result is 5 XOR 25 = 28.


		Input: nums = [0]
		Output: 0

		Input: nums = [2,4]
		Output: 6

	**/

	/**
		Approach 1: brute force straight forward

		Approach 2: Using trie

		1. So we build a trie using the bits from all the numbers
		2. The take each number, use it bits to traverse through the trie
		3. If the current bit is 0, then try to traverse the 1 node
		4. If the current bit is 1, then try to traverse the 0 node
		5. If the current node has only one child, then use it
		6. Along the travesal collect the bits and form a number at the end
		7. Any number you formed is from the trie. So that number exists in the list
		8. The number you formed will be different number, since you tried to use a different bit every time
		9. Get the max of it

	**/

	private class TrieNode {
        private final TrieNode[] children;
        
        private TrieNode() {
            this.children = new TrieNode[2];
        }
    }
    
    private TrieNode root;
    
    public int findMaximumXOR(int[] nums) {
        TrieNode current;
        root = new TrieNode();
        
        for (int num : nums) {
            current = root;
            
            for (int i = 30; i >= 0 ; i--) {
                int bit = getIthBit(num, i);
                
                if (current.children[bit ^ 1] == null) {
                    current.children[bit ^ 1] = new TrieNode();
                }
                
                current = current.children[bit ^ 1];
            }
        }

        int result = Integer.MIN_VALUE;
        
        for (int num : nums) {
            current = root;
            int localResult = 0;
            
            for (int i = 30; i >= 0 ; i--) {
                int bit = getIthBit(num, i);
                
                if (current.children[bit] != null) {
                    current = current.children[bit];
                    
                    localResult += (1 << i);
                } else {
                    current = current.children[bit ^ 1];   
                }
            }
            
            result = Math.max(result, localResult);
            
            if (result == Integer.MIN_VALUE) return 0;
        }
        
        return result;
    }
    
    private int getIthBit(int num, int i) {
        return (num & (1 << i)) == 0 ? 0 : 1;
    }


}