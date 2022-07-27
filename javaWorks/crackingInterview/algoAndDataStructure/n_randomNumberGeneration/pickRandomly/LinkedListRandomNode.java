public class LinkedListRandomNode{
	

	/**

		Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

		Follow up:
		What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

		Example:

		// Init a singly linked list [1,2,3].
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		Solution solution = new Solution(head);

		// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
		solution.getRandom();


	**/



    private ArrayList<Integer> lst = new ArrayList<>();
    public Solution(ListNode head) {
        while(head != null){
            lst.add(head.val);
            head = head.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int index = (int)(Math.random() * lst.size());
        return lst.get(index);
    }


    /**
	For follow up, where from a stream of data, we need to pick samples randomly, so that all of them have equal chances

	we can use the Resorvior sampling
	https://leetcode.com/problems/linked-list-random-node/solution/ - Approach 2
	https://en.wikipedia.org/wiki/Reservoir_sampling
	https://www.youtube.com/watch?v=DWZqBN9efGg (explanation and math proof - simple)
	
    **/

	ListNode head;

    public Solution(ListNode head) {
        this.head = head;
    }
    
    public int getRandom() {
        int itemsVistedSoFar = 0;
        int ans = 0;
        
        ListNode currentNode = head;
        while(currentNode != null){
            itemsVistedSoFar++;
            
            // Choose a random number and bring it to the current bound
            int rmdInt = (int)(Math.random() * itemsVistedSoFar);
            
            // Check if the "rmdInt" is itemsVistedSoFar, if so choose its value as answer
            // Reservoir sampling
            if(rmdInt == itemsVistedSoFar-1) ans = currentNode.val;
            
            
            currentNode = currentNode.next;
        }
        
        return ans;
    }


}
