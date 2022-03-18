public class CombinationIterator{
	
	
	/**

		Design an Iterator class, which has:

   		A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
		A function next() that returns the next combination of length combinationLength in lexicographical order.
		A function hasNext() that returns True if and only if there exists a next combination.

	**/


	/**	

	CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

	iterator.next(); // returns "ab"
	iterator.hasNext(); // returns true
	iterator.next(); // returns "ac"
	iterator.hasNext(); // returns true
	iterator.next(); // returns "bc"
	iterator.hasNext(); // returns false


	**/


	/**

	approach:
	Generate all combinations as a preprocessing.
	Use bit masking to generate all the combinations.

	we will get each number and generate combination
     Ex:-   S = abc and len = 2
	 then we have list = [6, 5, 3]
	 Now, how can we generate string from number?
	 
	 Here, each bit in the number represents a character in S
	 1st call of next:-
	 So, num = 6 -> 110(Binary)
	 S = a b c
	 N = 1 1 0 (Simply add all the characters which have 1 as corresponding bit)
	 Generated combination is ab.
	 
	 2nd call of next:-
	 So, num = 5 -> 101
	 S = a b c
	 N = 1 0 1
	 Generated combination is ac (since bits at  positions of a and c are 1).
	 
	 3rd call of next:-
	 So, num = 3 -> 011
	 S = a b c
	 N = 0 1 1
	 Generated combination is bc.
	 
	 Now, we are done with all the combinations of length 2.
	 Now, any call hasNext returns false since there are no numbers to read.




	**/


	List<Integer> combs;
    String str;
    int k;
    int currentIndex;

    public CombinationIterator(String characters, int combinationLength) {
        currentIndex = 0;
        k = combinationLength;
        str = characters;
        combs = new ArrayList<Integer>();
        
        // Populate all combinations
        for(int i = (1 << characters.length()) -1; i > 0; i--){
            if(countBits(i) == k){
                combs.add(i);
            }
        }
    }
    
    
    private int countBits(int n){
        int count = 0;
        
        while(n > 0){
            n = n & (n-1);
            count++;
        }
        
        return count;
    }
    
    public String next() {
        StringBuilder sb = new StringBuilder();
        int bitmask = combs.get(currentIndex);
        currentIndex++;
        
        int index = str.length() - 1;
        while(bitmask > 0){
            if((bitmask & 1) != 0){
                sb.insert(0,str.charAt(index));
            }
            
            bitmask = bitmask >> 1;
            index--;
        }
        
        return sb.toString();
    }
    
    public boolean hasNext() {
        return this.currentIndex < this.combs.size();
    }
	

}