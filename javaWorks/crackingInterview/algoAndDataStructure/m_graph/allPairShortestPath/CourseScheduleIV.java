public class CourseScheduleIV{
	/**

		There are a total of n courses you have to take, labeled from 0 to n-1.

		Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]

		Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.

		You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.

		Return a list of boolean, the answers to the given queries.

		Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.

	**/


	/**
		Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
		Output: [false,true]
		Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.


		Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
		Output: [false,false]
		Explanation: There are no prerequisites and each course is independent.

		Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
		Output: [true,true]

		Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
		Output: [false,true]

		Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
		Output: [true,false,true,false]

	**/


	/**
		Approach : This looks like Topological sorting for all the queries
		But instead we can use the floyd warshell all pair SP algo, only a bit modified

		And answer the queries based on that computation

	**/


	public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {   
        boolean[][] graph = new boolean[n][n];
        
        for(int[] prerequisite : prerequisites){
            graph[prerequisite[0]][prerequisite[1]] = true;
        }
        
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(k != i && k != j){
                        graph[i][j] = graph[i][j] || (graph[i][k] & graph[k][j]); 
                    }
                }
            }
        }
        
        List<Boolean> ans = new ArrayList<>();
        int i = 0;
        for(int[] query : queries){
            ans.add(graph[query[0]][query[1]]);
        }
        
        return ans;
    }
}