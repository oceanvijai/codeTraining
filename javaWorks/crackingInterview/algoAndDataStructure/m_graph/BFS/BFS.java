import java.util.LinkedList;
import java.util.Set;

public class BFS {

    public void doBFS(Graph g) {
        Set<Graph.Edge> visited = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>();

        int connectedComponents = 0; // Will tell who many components are there int the graph
        for (int i = 0; i < g.adjancyList.length; i++) {
            if (!visited.contains(i)) {
                traverseBFS(g, i, visited, parent);
                connectedComponents++;
            }
        }
    }

    private void traverseBFS(Graph g, int startVertex, Set<Integer> visited, Map<Integer, Integer> parent) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.addFirst(startVertex);
        map.put(startVertex, 0); // parent

        while (!queue.isEmpty()) {
            int vtx = queue.pollFirst();
            visited.add(vtx);

            for (Graph.Edge edge : vtx.adjancyList) {
                if (!visited.contains(edge.endVertex)) {
                    queue.addLast(edge.endVertex);
                    map.put(edge.endVertex, vtx);
                }
            }

        }
    }
}