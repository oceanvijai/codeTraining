class PeekingIterator implements Iterator<Integer> {
    
    // Google impl of this
    // https://github.com/google/guava/blob/6f22af40e1526b8c194e9e36d457bcd37680c6a3/guava/src/com/google/common/collect/Iterators.java#L1121
    
    
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