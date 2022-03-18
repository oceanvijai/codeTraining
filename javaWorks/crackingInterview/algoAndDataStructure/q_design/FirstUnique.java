public class FirstUnique{
	/*

	You have a queue of integers, you need to retrieve the first unique integer in the queue.

	Implement the FirstUnique class:

    * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
    * int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
    * void add(int value) insert value to the queue.


    * Input: 
	* ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
	* [[[2,3,5]],[],[5],[],[2],[],[3],[]]
	* Output: 
	* [null,2,null,2,null,3,null,-1]

	* Explanation: 
	* FirstUnique firstUnique = new FirstUnique([2,3,5]);
	* firstUnique.showFirstUnique(); // return 2
	* firstUnique.add(5);            // the queue is now [2,3,5,5]
	* firstUnique.showFirstUnique(); // return 2
	* firstUnique.add(2);            // the queue is now [2,3,5,5,2]
	* firstUnique.showFirstUnique(); // return 3
	* firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
	* firstUnique.showFirstUnique(); // return -1

	*/

	Map<Integer,Node> map = new HashMap<>();
    Node head = null;
    Node tail = null;
    Set<Integer> dSet = new HashSet<>();

    public FirstUnique(int[] nums) {
        for(Integer num : nums){
            add(num);
        }
    }
    
    public int showFirstUnique() {
        if(head == null){
            return -1;
        }else{
            return head.value;
        }
    }
    
    public void add(int value) {
        if(dSet.contains(value)){
            return;
        }else if(map.containsKey(value)){
            Node nodeVal = map.get(value); // remove from linked list
            removeNode(nodeVal);
            
            map.remove(value); // remove from map
            
            dSet.add(value); // add to duplicate set
            System.out.println("Val present: "+value);
        }else{
            Node newVal = new Node(value);
            map.put(value,newVal);
            if(head == null){
                head = newVal;
                tail = newVal;
            }else{
                tail.next = newVal;
                newVal.prev = tail;
                tail = newVal;
            }
        }
    }
    
    private void removeNode(Node node){
        if(node.prev == null){
            head = node.next;
        }else{
            node.prev.next = node.next;
        }
        
        if(node.next == null){
            tail = node.prev;
        }else{
            node.next.prev = node.prev;
        }
    }
    
    private class Node{
        int value;
        Node next;
        Node prev;
        
        public Node(int val){
            this.value = val;
        }
    }
}