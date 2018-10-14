public class MissingInt {

    /**
     * Given an input file with four billion non-negative integers, provide an
     * algorithm to generate an integer that is not contained in the file. Assume
     * you have 1 GB of memory available for this task.
     */

    /**
     * FOLLOW UP: What if you have only 10MB of memory? Assume that all the values
     * are distinct and we now have no more than one billion non-negative integers.
     */

    // We have 1 GB of memory, or 8 billion bits. Thus, with 8 billion bits, 
    // but we need to map only 4 billion bits

    /**
     *  1: create a bit map/vector using byte array (smallest) or use an boolean array
     *  2: update the rspective position with 1 
     *  3: The look for a position with 0
     */

    public int firstMissingPositive(ArrayList<Integer> a) {
	    int[] bitmap = new int[a.size() / 32 + 1];
	    for (int x : a) {
	        x = x - 1;
	        int i = x / 32;
	        if (i < bitmap.length) {
	            int mask = 1 << (x % 32);
	            bitmap[i] |= mask;
	        }
	    }
	    for (int i = 0; i < bitmap.length; i++) {
	        if (bitmap[i] != -1) {
	            int shift = 0;
	            while ((bitmap[i] & (1 << shift)) != 0) {
	                shift++;
	            }
	            return i * 32 + shift + 1;
	        }
	    }
	    return -1;
	}

     

    //FOLLOW UP:

    /**
     *  We have 1 billion numbers and 10 MB memory
     *  1: We can fix a rangeSize like 1 to 1000, 1001 - 2000 etc
     *  2. Then parse the file on how many intergers are there in each range
     *  3. If some ranage has lesser count we concentrate on that range only 
     *  4. So we take that range and create a bi map for that range, ignoring number of of that range
     *  5. Finally we appy the above technique to get the result
     * 
     *  The real question here is hoe to decide the rangeSize cause, if we those it small we will end up 
     *  not having space to count in the frist part
     * 
     *  Then we also need to have enought space to create a bit vector 
     *  of the size of rangeSize to map it in the second pass.
     */


}