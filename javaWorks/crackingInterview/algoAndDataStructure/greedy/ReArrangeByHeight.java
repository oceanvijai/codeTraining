public class ReArrangeByHeight{
	/** 

	Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

	Input:
	[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

	Output:
	[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
	**/


	/**


	Sort based on height in descending, and for persons with same height, sort in ascending based on k.
	Sorted Values:
	[[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]

	Then, construct queue with sorted list.

	Put [7, 0] at position 0 => [[7,0]]
	Put [7, 1] at position 1 => [[7,0], [7,1]]
	Put [6, 1] at position 1 => [[7,0], [6, 1, [7,1]]
	Put [5,0] at position 0 => [[5,0], [7,0], [6, 1], [7,1]]
	Put [5, 2] at position 2 => [[5,0], [7,0], [5, 2], [6, 1], [7,1]]
	Put [4, 4] at position 4 => [[5,0], [7,0], [5, 2], [6, 1],[4, 4], [7,1]]
	And it matches with our output.


	 **/


	public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(int[] a, int[] b)-> {return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];});
        List<Object> lst = new ArrayList<>();
        for(int[] p : people){
            lst.add(p[1],p);
        }
        
        return lst.toArray(new int[lst.size()][]);
    }
}