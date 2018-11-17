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
            for(Map<K,V>.Entry e: distance.entrySet()){
                // The vertex should not he visited 
                // it should have the min values

                // set it to v
            }

        }

    }

}