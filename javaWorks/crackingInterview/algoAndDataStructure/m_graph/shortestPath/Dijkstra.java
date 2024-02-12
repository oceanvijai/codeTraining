public class Dijkstra {

    private static void dijkstraAlgo(Graph1 g, int src, int dest, Integer parent[], Integer distance[],
            boolean visited[]) {

        int next = src;
        while (next != -1) {
            visited[next] = true;
            LinkedList<Edge> edges = g.adjancyList[next];
            for (Edge edge : edges) { 
                // rotuine updating the ditance
                if (distance[edge.endVertex] > distance[next] + edge.weight) {
                    distance[edge.endVertex] = distance[next] + edge.weight;
                    parent[edge.endVertex] = next;
                }
            }

            next = -1;
            int dist = Integer.MAX_VALUE;
            for (int v = 0; v <= g.adjancyList.length - 1; v++) { // rotuine to get the next edge
                if (!visited[v] && distance[v] < dist) {
                    next = v;
                    dist = distance[v];
                }
            }

        }

        System.out.println(parent);

    }


    

    private static void findShortestPath_dijkstraAlgo(Graph1 g, int src, int dest) {
        // do dfs
        // clean it on the way
        Integer parent[] = new Integer[g.adjancyList.length];

        for (int i = 0; i <= parent.length - 1; i++) {
            parent[i] = -1;
        }

        Integer distance[] = new Integer[g.adjancyList.length];
        for (int i = 0; i <= distance.length - 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[src] = 0;
        // g.adjancyList[src].forEach(edge -> {distance[edge.endVertex] =
        // edge.weight;});

        boolean visited[] = new boolean[g.adjancyList.length];
        // visited.add(src);

        dijkstraAlgo(g, src, dest, parent, distance, visited);

    }

    private static void findShortestPath_BellManFordAlgo(Graph1 g, int src, int dest) {
        Integer parent[] = new Integer[g.adjancyList.length];

        for (int i = 0; i <= parent.length - 1; i++) {
            parent[i] = -1;
        }

        Integer distance[] = new Integer[g.adjancyList.length];
        for (int i = 0; i <= distance.length - 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[src] = 0;

        boolean visited[] = new boolean[g.adjancyList.length];

        for (int v = 0; v <= g.adjancyList.length - 2; v++) { // Do it V-1 time for for V vertices
            BellManFordAlgo(g, parent, distance);
        }
        // So total O(V*E)
        System.out.println(parent);

    }

    private static void BellManFordAlgo(Graph1 g, Integer parent[], Integer distance[]) {
        // Get all the Edges and relax, E times. 
        for (int v = 0; v <= g.adjancyList.length - 1; v++) { // Here are getting all the edges, not iterating over the vertices
            for (Edge edge : g.adjancyList[v]) {
                if (distance[v] > distance[edge.endVertex] + edge.weight) {
                    distance[v] = distance[edge.endVertex] + edge.weight;
                    parent[v] = edge.endVertex;
                }
            }

        }
    }

    static class Edge {

        int endVertex;
        int weight;

        public Edge(int endVertex, int weight) {
            this.endVertex = endVertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{" + endVertex + "," + weight + '}';
        }
    }

    static class Graph1 {

        public LinkedList<Edge> adjancyList[];

        public Graph1(int vertices) {
            adjancyList = new LinkedList[vertices];
            for (int i = 0; i <= vertices - 1; i++) {
                adjancyList[i] = new LinkedList<Edge>();
            }
        }

        public void connect(int startVertex, int endVertex, int weight) {
            adjancyList[startVertex].add(0, new Edge(endVertex, weight));
        }

        public void printGraph() {
            for (LinkedList list : adjancyList) {
                System.out.println(list);
            }
        }

    }
}
