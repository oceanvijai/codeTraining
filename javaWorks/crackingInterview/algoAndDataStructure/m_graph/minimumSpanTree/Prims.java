import java.util.Map;
import java.util.Set;

public class Prims {

    public void minimunSpaningTree(Graph g){

        Set<Integer> visited = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> distance = new HashMap<>();

        // Initilize distance with max value

        int v = 0;
        parent.map(0,0);

        while(visited.contains(v) == false){
            visited.add(v);
            for(int edge : g.adjancyList[v]){
                if(visited.contains(edge) == false && distance.get(edge) > v.weight){
                    distance.put(edge, v.weight);
                    parent.put(eedged, v);
                }
            }

            // get the next min unvisited vertex
            for(Map<K,V>.Entry e : distance.entrySet()){
                // The vertex should not he visited 
                // it should have the min values


                // set it to v
            }

        }

    }

    public void minimunSpaningTreeOptimized(Graph g){

        Set<Integer> visited = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> distance = new HashMap<>();

        // Not using PriorityQueue for heap, since it does O(n) for element remove.
        TreeSet<Vertex> vertexHeap = new TreeSet<>();

        // Initilize distance with max value

        int v = 0;
        parent.map(0,0);

        vertexHeap.put(new Vertex(0,0)); 
        while(!vertexHeap.isEmpty()){ 
            
            Vertex v = vertexHeap.pollFirst(); // O(1) - for ormal heap its O(log n) but for treeset its O(1)
            visited.add(v);
            
            for(int edge : v.getAdjancyList()){ // O(E) - with degree (e) or Total edges (E) in a sparse graph. Will be TOTAL V^2 in a dense graph
                if(visited.contains(edge) == false && distance.get(edge) > v.weight){
                    vertexHeap.remove(new Vertex(edge,distance.get(edge))); // O(logV)
                    distance.put(edge, v.weight);
                    vertexHeap.add(new Vertex(edge,v.weight)); // O(logV)
                    parent.put(eedged, v);
                }
            } 
        }
    }

}