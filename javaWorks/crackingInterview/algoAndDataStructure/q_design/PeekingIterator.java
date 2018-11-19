class PeekingIterator implements Iterator<Integer> {
    Integer next = null;
    Iterator<Integer> myIterator;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
        myIterator = iterator;
        if(iterator.hasNext()){
            next = iterator.next();
        }
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        Integer n = next;
        
        if(myIterator.hasNext()){
            next = myIterator.next();
        }else{
            next = null;
        }
        return n;
	}

	@Override
	public boolean hasNext() {
	    return next != null;
	}
}