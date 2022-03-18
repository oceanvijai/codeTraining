public class StringCombinations{
	
	/**
		Given a string and an integer k find all combinations we can make from the chanracters of the string
		with length k
	**/

	ArrayList<String> comb = new ArrayList<>();

	psvm(){
		combinations(characters, 0, "");
	}

	public void combinations(String s, int index, String com){
        if(com.length() == this.cLen){
            this.comb.add(com);
            return ;
        }
        if(index >= s.length()) return ;
        for(int i = index; i < s.length(); i++)
            combinations(s, i + 1, com + String.valueOf(s.charAt(i)));
    }


    /**

	Now, in next function, we will get each number and generate combination
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



	 public CombinationIterator(String characters, int combinationLength) {
        this.comb = new ArrayList<>();
        this.pointer = 0;
        this.s = characters;
        int n = characters.length();
        for(int i = (1 << n) - 1; i > 0; i--)
            if(countBits(i) == combinationLength) comb.add(i);
    }
    public int countBits(int n){
        int count = 0;
        while(n > 0){
            n &= (n - 1);
            count++;
        }
        return count;
    }


}