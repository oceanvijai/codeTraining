public class JumpGame4{
	
	/**

		Given an array of integers arr, you are initially positioned at the first index of the array.

		In one step you can jump from index i to index:

		i + 1 where: i + 1 < arr.length.
		i - 1 where: i - 1 >= 0.
		j where: arr[i] == arr[j] and i != j.
		Return the minimum number of steps to reach the last index of the array.

		Notice that you can not jump outside of the array at any time.



		Example 1:

		Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
		Output: 3
		Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
		Example 2:

		Input: arr = [7]
		Output: 0
		Explanation: Start index is the last index. You don't need to jump.
		Example 3:

		Input: arr = [7,6,9,6,9,6,9,7]
		Output: 1
		Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
		Example 4:

		Input: arr = [6,1,9]
		Output: 2
		Example 5:

		Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
		Output: 3

	**/


	/**
		Approach 1: simple. Form a graph/tree and do bfs

		Approach 2: Optimization. Bidirectional search

	**/



	public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(!map.containsKey(arr[i])){
                List<Integer> lst = new ArrayList<>();
                lst.add(i);
                map.put(arr[i], lst);
            }else{
                map.get(arr[i]).add(i);
            }
        }
        
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addFirst(0);
        int levelCount = 1;
        int distance = 0;
        int lastIndex = arr.length-1;
        
        while(!queue.isEmpty()){
            int index = queue.pollFirst();
            levelCount--;
            visited.add(index);
            if(index == lastIndex){
                break;
            }
            
            if(index-1 >= 0 && !visited.contains(index-1)){
                queue.addLast(index-1);
            }
            
            if(index+1 <= lastIndex && !visited.contains(index+1)){
                queue.addLast(index+1);
            }
            
            if(map.containsKey(arr[index])){
                for(Integer i : map.get(arr[index])){
                    if(!visited.contains(i)){
                        queue.addLast(i);
                    }
                }
                // clear memory
                map.get(arr[index]).clear();
            }
            
            
            if(levelCount == 0){
                //System.out.println(queue);
                distance++;
                levelCount =  queue.size();
            }
        }
        
        return distance;
    }

}