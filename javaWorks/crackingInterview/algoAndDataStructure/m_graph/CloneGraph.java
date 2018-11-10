public class CloneGraph {

    private class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };

    Map<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) {
            return null;
        }
        return doDFS(node);

        // UndirectedGraphNode coloned = new UndirectedGraphNode(node.label);
        // map.put(node.label,coloned);
        // doDFS(node,coloned);
        // return coloned;
    }

    private UndirectedGraphNode doDFS(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        if (map.get(node.label) != null) {
            return map.get(node.label);
        }

        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        map.put(node.label, cloned);

        for (UndirectedGraphNode edge : node.neighbors) {
            UndirectedGraphNode newEdge = doDFS(edge);
            cloned.neighbors.add(newEdge);
        }

        return cloned;

    }

    private void doDFS(UndirectedGraphNode node, UndirectedGraphNode coloned) {

        visited.add(node.label);

        for (UndirectedGraphNode edge : node.neighbors) {
            if (visited.contains(edge.label) == false) {
                UndirectedGraphNode newEdge = null;
                if (map.get(edge.label) == null) {
                    newEdge = new UndirectedGraphNode(edge.label);
                    map.put(edge.label, newEdge);
                } else {
                    newEdge = map.get(edge.label);
                }

                coloned.neighbors.add(newEdge);

                doDFS(edge, newEdge);
            } else {
                coloned.neighbors.add(map.get(edge.label));
            }
        }

    }
}