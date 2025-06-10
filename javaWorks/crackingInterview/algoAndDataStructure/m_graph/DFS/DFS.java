import java.util.Set;

public class DFS {

    public void doDFS(Graph g) {
        Set<Graph.Edge> visited = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>(); // To get the tree, forward, backward and cross edges

        int connectedComponents = 0; // Will tell who many components are there int the graph
        for (int i = 0; i < g.adjancyList.length; i++) {
            if (!visited.contains(i)) {
                parent.put(i, 0);
                traverseDFS(g, i, visited, map);
                connectedComponents++;
            }
        }
    }

    private void traverseDFS(Graph g, int startVertex, Set<Integer> visited, Map<Integer, Integer> map) {

        visited.add(startVertex);
        for (Graph.Edge edge : vtx.adjancyList) {
            if (!visited.contains(edge.endVertex)) {
                parent.put(edge.endVertex, startVertex);
                traverseDFS(g, edge.endVertex, visited);
            }
        }

    }

}
