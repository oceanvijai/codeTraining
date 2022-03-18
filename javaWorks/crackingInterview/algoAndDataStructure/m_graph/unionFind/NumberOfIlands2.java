public class NumberOfIlands2 {

    /**
     * Approach : Using the disjoint set + union find algo for graph
     * This is further optimized using the path reduce and ranking
     * 
     * Steps: 
     * 
     * 1.   Add a iland to the graph
     * 2.   Now initilize it with rank 1 and parent as itself
     * 3.   Increment the connected components (number of ilands). this will be later reduced if required
     * 
     * 4.   Check if any of the four neighbours are an iland (rank >= 1)
     * 5.   If we discoved a new iland, then check the new index parent and the newly discoved iland's parent
     * 6.   If they are same, then no issues continue with the next neighbour
     * 7.   If they are dufferent, find their respective parents and union them
     * 
     * 8.   while findding the parents we can do an optimization called (path compression), which is directly 
     *      pointing to the root of the iland instead of jsut pointing to its leaf
     * 
     * 9.   Once, we found its parent, check which has a higher rank and make that as the root and improve the 
     *      rank of the selected root by adding up the rank of the least
     * 10.   decrement the connected components 
     * 
     */


    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        PathCompressedGraph2D graph = new PathCompressedGraph2D(m, n);

        for (int[] position : positions) {
            int x = position[0], y = position[1];
            graph.add(x, y);
            int p = graph.getIlands();
            ans.add(p);
        }

        return ans;
    }

    private class PathCompressedGraph2D {
        private int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
        int[] grid; // we store two things here, the rank if its a root, or '1' if its just an iland
        int[] parent;
        int r;
        int c;
        int connectedComponents = 0;

        public PathCompressedGraph2D(int rows, int columns) {
            // grid = int new [rows][columns];
            r = rows;
            c = columns;
            grid = new int[rows * columns + 1];
            parent = new int[rows * columns + 1];
        }

        public void add(int m, int n) {
            int index = getIndexFor(m, n);
            // Now do the noraml union Find
            grid[index] = 1;

            // this helps us to find its the parent of the set, only the root will retain it
            parent[index] = index;

            connectedComponents++; // this can be updated during UnionFind operations

            for (int[] d : dir) {
                int x = d[0] + m;
                int y = d[1] + n;
                if (x >= 0 && x < r && y >= 0 && y < c) {
                    int i = getIndexFor(x, y);
                    if (grid[i] > 0) {
                        doUnionFind(i, index);
                    }
                }
            }
        }

        private void doUnionFind(int p, int q) {
            int i1 = find(p);
            int i2 = find(q);

            if (i1 == i2) {
                return;
            }

            union(i1, i2);
            connectedComponents--;
        }

        private int find(int x) {
            int p = parent[x];
            if (x == p) {
                return x;
            } else {
                parent[x] = find(p); // This re-setting is path compression
            }

            return parent[x];
        }

        private void union(int i1, int i2) {
            int rank1 = grid[i1];
            int rank2 = grid[i2];

            if (rank1 > rank2) { // Union based on rank
                parent[i2] = i1;
                grid[i1] += rank2; // adding up the rank
            } else {
                parent[i1] = i2;
                grid[i2] += rank1; // adding up the rank
            }
        }

        public int getIlands() {
            return connectedComponents;
        }

        public int getIndexFor(int m, int n) {
            return (m * c) + n + 1;
        }

    }
}