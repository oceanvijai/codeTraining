public class CheapestFlights {
    /**
     * There are n cities connected by m flights. Each fight starts from city u and
     * arrives at v with a price w.
     * 
     * Now given all the cities and flights, together with starting city src and the
     * destination dst, your task is to find the cheapest price from src to dst with
     * up to k stops. If there is no such route, output -1.
     */

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        // create a graph
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] f : flights) {
            if (!graph.containsKey(f[0]))
                graph.put(f[0], new HashMap<>());
            graph.get(f[0]).put(f[1], f[2]);
        }

        // heap to sort edges based on the cost
        // in this array, index 0 is cost, index 1 is src and index 2 is number of stops
        // to get to this src
        Queue<int[]> heap = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        heap.add(new int[] { 0, src, K + 1 });

        // do the Dijkstra
        while (!heap.isEmpty()) {
            int[] top = heap.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];

            if (city == dst) {
                return price;
            }

            if (stops > 0) {
                Map<Integer, Integer> edges = graph.getOrDefault(city, new HashMap<>());
                for (int e : edges.keySet()) {
                    // here we are just adding the rount we found so far to the heap
                    // not checking if we have found a rout so far
                    heap.add(new int[] { price + edges.get(e), e, stops - 1 });
                }

            }
        }

        // We were not able to reach the destination
        return -1;
    }
}