public class BabyNames {

    /**
     * Each year, the government releases a list of the 10,000 most common baby
     * names and their frequencies (the number of babies with that name). The only
     * problem with this is that some names have multiple spellings.
     * 
     * For example, "John" and "Jon" are essentially the same name but would be
     * listed separately in the list. Given two lists, one of names/frequencies and
     * the other of pairs of equivalent names, write an algorithm to print a new
     * list of the true frequency of each name.
     * 
     * Note that if John and Jon are synonyms, and Jon and Johnny are synonyms, then
     * John and Johnny are synonyms. (It is both transitive and symmetric.) In the
     * final list, any name can be used as the "real" name.
     */

    /**
     * EXAMPLE Input: Names: John (15), Jon (12), Chris (13), Kris (4),
     * Christopher(19)
     * 
     * Synonyms: (Jon, John), (John, Johnny), (Chris, Kris), (Chris, Christopher)
     * 
     * Output: John (27), Kris (36)
     */

    /**
     * Approach 1: we have to make a pool of synonymus names, so when we are asked
     * for a name we can return its count and then merge them all
     * 
     * How, can we create a pool ?
     * 
     * First start by the current pair and create a set and assign it to both
     * 
     * Then, also make sure their count is added and attached to the set (probably
     * new Object)
     * 
     * then when new pairs come in we merge them accordingly and update it with all
     * its pairs
     * 
     * time: O(n log n) - log n for the pair merge and n for the merge like the
     * merge sort
     */

    /**
     * Approach 2: Graph consider each pair as a edge and each name as a vertex
     * 
     * Now its like finding the number of connected components
     * 
     * First, start from a vertex/name and do DFS and collect the count and mark it as visited
     * 
     * Then, do it for all the vertex which are not visited and finally collect all the values
     * 
     */




    HashMap<String, Integer> trulyMostPopular(HashMap<String, Integer> names, String[][] synonyms) {
        // Create data.
        Graph graph = constructGraph(names);
        connectEdges(graph, synonyms);
        // Find components.
        HashMap<String, Integer> rootNames = getTrueFrequencies(graph);
        return rootNames;
    }

    // Add all names to graph as nodes. *1

    Graph constructGraph(HashMap<String, Integer> names) {
        Graph graph = new Graph();
        for (Entry<String, Integer> entry : names.entrySet()) {
            String name = entry.getKeY();
            int frequency = entry.getValue();
            graph.createNode(name, frequency);
        }
        return graph;
    }

    // Connect synonymous spellings.
    void connectEdges(Graph graph, String[][] synonyms) {
        for (String[] entry : synonyms) {
            String name1 = entry[0];
            String name2 = entry[l];
            graph.addEdge(name1, name2);
        }
    }

    // Do DFS of each component. If a node has been visited before, then its
    // component
    // has already been computed.
    HashMap<String, Integer> getTrueFrequencies(Graph graph) {
        HashMap<String, Integer> rootNames = new HashMap<String, Integer>();
        for (GraphNode node : graph.getNodes()) {
            if (!node.isVisited()) { // Already visited this component
                int frequency = getComponentFrequency(node);
                String name = node.getName();
                rootNames.put(name, frequencY);
            }
        }
        return rootNames;
    }

    // Do depth -first search to find the total frequency of this component, and
    // mark
    // * each node as visited. *1
    int getComponentFrequency(GraphNOde node) {
        if (node.isVisited())
            return 0; // Already vi ited

        node.setIsVisited(true);
        int sum = node.getFrequencY();

        for (GraphNOde child : node.getNeighbors()) {
            sum += getComponentFrequency(child);
        }
        return sum;
    }

}