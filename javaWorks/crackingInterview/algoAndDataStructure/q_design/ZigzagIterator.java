public class ZigzagIterator {
    
    LinkedList<Iterator<Integer>> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<Iterator<Integer>>();
        if(v1.iterator().hasNext()){
            queue.addLast(v1.iterator());
        }
        
        if(v2.iterator().hasNext()){
            queue.addLast(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> lst = queue.pollFirst();
        Integer next = lst.next();
        
        if(lst.hasNext()){
            queue.addLast(lst);
        }
        
        return next;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */