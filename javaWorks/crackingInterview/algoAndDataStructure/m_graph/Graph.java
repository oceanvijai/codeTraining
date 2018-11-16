import java.util.LinkedList;

public class Graph {
    
    LinkedList<Edge>[] adjancyList;

    public Graph(int vertices) {
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

    
    public static class Edge {

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


}