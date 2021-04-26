import java.util.*;

public class AllPathGraph {

    private static void findAllPath(Graph1 g, int src, int dest) {
        // do dfs
        // clean it on the way
        ArrayList<Integer> path = new ArrayList<Integer>();
        Set<Integer> visited = new HashSet<>();
        //visited.add(src);

        path.add(src);

        findPath(g, src, dest, path, visited);

    }

    private static void findPath(Graph1 g, int src, int dest, ArrayList<Integer> path, Set<Integer> visited) {

        visited.add(src);

        if (src == dest) {
            System.out.println(path);
        }

        for (Edge edge : g.adjancyList[src]) {
            if (!visited.contains(edge.endVertex)) {
                path.add(new Integer(edge.endVertex));
                findPath(g, edge.endVertex, dest, path, visited);
                path.remove(new Integer(edge.endVertex));
            }
        }

        visited.remove(src);
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


    public static void main(String[] args) {
        // Create a sample graph
        Graph1 g = new Graph1(4);
        g.connect(0, 1, 0);
        g.connect(0, 2, 0);
        g.connect(0, 3, 0);
        g.connect(2, 0, 0);
        g.connect(2, 1, 0);
        g.connect(1, 3, 0);


        System.out.println("Following are all different paths from " + 1 + " to " + 2);
        findAllPath(g, 2, 3);
    }
}