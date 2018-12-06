public class CourseSchedule {
    /**
     * There are a total of n courses you have to take, labeled from 0 to n-1.
     * 
     * Some courses may have prerequisites, for example to take course 0 you have to
     * first take course 1, which is expressed as a pair: [0,1]
     * 
     * Given the total number of courses and a list of prerequisite pairs, is it
     * possible for you to finish all courses?
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList();

        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!topologicalSort(graph, visited, i))
                return false;
        }
        return true;
    }

    private boolean topologicalSort(ArrayList[] graph, boolean[] visited, int course) {
        if (visited[course])
            return false;
        else
            visited[course] = true;
        ;

        for (int i = 0; i < graph[course].size(); i++) {
            if (!topologicalSort(graph, visited, (int) graph[course].get(i)))
                return false;
        }
        visited[course] = false;
        return true;
    }
}