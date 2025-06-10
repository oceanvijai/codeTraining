public class TopoSortShortestPath{


  public void shortestPath(int src) {
        // Topological sort
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Initialize distances
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Process nodes in topological order
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (dist[u] != Integer.MAX_VALUE) {
                for (Edge edge : adj.get(u)) {
                    if (dist[edge.dest] > dist[u] + edge.weight) {
                        dist[edge.dest] = dist[u] + edge.weight;
                    }
                }
            }
        }

        // Print shortest distances
        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To node " + i + " : " +
                (dist[i] == Integer.MAX_VALUE ? "Unreachable" : dist[i]));
        }
    }

  private void topologicalSortUtil(int u, boolean[] visited, Deque<Integer> stack) {
        visited[u] = true;
        for (Edge edge : adj.get(u)) {
            if (!visited[edge.dest]) {
                topologicalSortUtil(edge.dest, visited, stack);
            }
        }
        stack.push(u); // Push after all descendants are processed
    }


  static class Edge {
        int dest, weight;
        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

  
}
