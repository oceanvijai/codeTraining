public class MaxQueue {
    LinkedList<Integer> maxQueue = new LinkedList<>();
    LinkedList<Integer> queue = new LinkedList<>();
    
    public void add(int x){
        queue.addLast(x);
        while(!maxQueue.isEmpty() && maxQueue.peekLast() < x){
            maxQueue.pollLast();
        }
        
        maxQueue.addLast(x);
    }
    
    public void remove(){
        if(queue.isEmpty()){
            return;
        }
        
        if(queue.peekFirst() == maxQueue.peekFirst()){
            maxQueue.pollFirst();
        }
        
        queue.pollFirst();
    }
    
    public int getMax(){
        if(maxQueue.isEmpty()){
            return -1;
        }
        
        return maxQueue.peekFirst();
    }
    
    
}