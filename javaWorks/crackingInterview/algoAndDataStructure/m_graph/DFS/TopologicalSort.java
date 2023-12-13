public class TopologicalSort {


    public void doDFS(Graph g) {
        Set<Graph.Edge> visited = new LinkedList<>();
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < g.adjancyList.length; i++) {
            if (!visited.contains(i)) {
                traverseDFS(g,i,visited, stack);
            }
        }

        // After this the sort is in the reverse order
    }

    private void traverseDFS(Graph g, int startVertex, Set<Integer> visited, LinkedList<Integer> stack) {

        visited.add(startVertex);

        for (Graph.Edge edge : vtx.adjancyList) {
            // if we find a visited vertex here, then we have a cycle
            if (!visited.contains(edge.endVertex)) {
                traverseDFS(g, edge.endVertex, visited);
            }
        }

        stack.add(startVertex); // This is the only difference from the DFS
    }


    /**
        Another way to do this is by doing this.
        A vertex with indegree zero can be the starting point of the sort.
        There can multiple vertex with indegree zero

        So we take one vertex with indegree zero and delete it (delete all its edges). This will eventually produce one more vertex with indgree zero.
        We repeat the same.

    **/


    /**
        The second algo is can be used to solve some more problems like this.
        Suppose we need to aggrage the students in such a way the student hate replatiion is considered and we avoid problems.
        For this we can use the first DFS and solve it.

        But if suppose we are asked to do such that, they need to arranged in fews rows. Like the students who dont hate each other can be palced in the same row.
        For this we can use the second algo.
        Meaning all vertext with indregee zero can we placed in a same row. then we do the Second level deleteion and plan them.


    **/
}
