public class NetworkDelayTime {

    /**
        You are given a network of n nodes, labeled from 1 to n. You are also given times, 
        a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

        We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. 
        If it is impossible for all the n nodes to receive the signal, return -1.
        

        Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
        Output: 2

    **/


    public int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, k});
        Map<Integer, Integer> dist = new HashMap();

        int ans = 0;
        while (!heap.isEmpty()) {
            int[] vertex = heap.poll();
            int d = vertex[0], node = vertex[1];
            if (dist.containsKey(node)) continue;
            ans = Math.max(ans, d);
            
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }
        
        if (dist.size() != n) return -1;
                    
        return ans;
    }
}