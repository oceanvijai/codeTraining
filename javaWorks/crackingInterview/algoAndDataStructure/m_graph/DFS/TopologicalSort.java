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
}