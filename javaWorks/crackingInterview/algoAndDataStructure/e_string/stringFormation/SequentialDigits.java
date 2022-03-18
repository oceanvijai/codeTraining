public class SequentialDigits{
	/**

	An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

	Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

	**/

	/**

	Example 1:

	Input: low = 100, high = 300
	Output: [123,234]
	Example 2:

	Input: low = 1000, high = 13000
	Output: [1234,2345,3456,4567,5678,6789,12345]

	**/

	public List<Integer> sequentialDigits(int low, int high) {
        String mask = "123456789";
        List<Integer> ans = new ArrayList<>();
        for(int size = 2; size < 10; size++){
            for(int start =0; start+size < 10; start++){
                int val = Integer.parseInt(mask.substring(start, start+size));
                if(val >= low){
                    if(val < high){
                        ans.add(val);
                    }else{
                        return ans;
                    }
                    
                }
            }
        }
        
        return ans;
    }
}