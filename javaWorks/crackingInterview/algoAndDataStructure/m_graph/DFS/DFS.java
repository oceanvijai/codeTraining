import java.util.Set;

public class DFS {

    public void doDFS(Graph g) {
        Set<Graph.Edge> visited = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>(); // To get the tree, forward, backward and cross edges

        int connectedComponents = 0; // Will tell who many components are there int the graph
        for (int i = 0; i < g.adjancyList.length; i++) {
            if (!visited.contains(i)) {
                traverseDFS(g,i,visited);
                connectedComponents++;
            }
        }
    }

    private void traverseDFS(Graph g, int startVertex, Set<Integer> visited) {

        visited.add(startVertex);
        for (Graph.Edge edge : vtx.adjancyList) {
            // if we find a visited vertex here, then we have a cycle
            if (!visited.contains(edge.endVertex)) {
                traverseDFS(g, edge.endVertex, visited);
            }
        }

    }

}