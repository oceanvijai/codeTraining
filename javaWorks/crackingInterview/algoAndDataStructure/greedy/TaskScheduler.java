public class TaskScheduler{
	
	
	/**

		You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter represents a different task. Tasks could be done without the original order of the array. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

		However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

		You need to return the least number of units of times that the CPU will take to finish all the given tasks.


		Input: tasks = ["A","A","A","B","B","B"], n = 2
		Output: 8
		Explanation: 
		A -> B -> idle -> A -> B -> idle -> A -> B
		There is at least 2 units of time between any two same tasks.


		Input: tasks = ["A","A","A","B","B","B"], n = 0
		Output: 6
		Explanation: On this case any permutation of size 6 would work since n = 0.
		["A","A","A","B","B","B"]
		["A","B","A","B","A","B"]
		["B","B","B","A","A","A"]
		...
		And so on.


		Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
		Output: 16
		Explanation: 
		One possible solution is
		A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

	**/

	/**
	ans: 	https://www.youtube.com/watch?v=sTEPmlWBmJU
	**/




	public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
			return 0;
		}
		int m = tasks.length;
		int[] cnt = new int[26];
		for (char c: tasks) {
			cnt[c - 'A']++;
		}
		Arrays.sort(cnt);
        int max = cnt[25]-1;
		int spaces = max * n;
		
		for (int i = 24; i >= 0; i--) {
			spaces -= Math.min(max, cnt[i]);
		}
		spaces = Math.max(0, spaces);
		return tasks.length + spaces;
    }

}