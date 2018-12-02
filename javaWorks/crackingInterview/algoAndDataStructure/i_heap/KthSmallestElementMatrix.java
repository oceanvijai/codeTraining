public class KthSmallestElementMatrix {

     /**
     * Given a n x n matrix where each of the rows and columns are sorted in
     * ascending order, find the kth smallest element in the matrix.
     * 
     * Note that it is the kth smallest element in the sorted order, not the kth
     * distinct element.
     * 
     * 
     * matrix = [
        [ 1,  5,  9],
        [10, 11, 13],
        [12, 13, 15]
        ],
        k = 8,

        return 13.
     */
    
    /**
     * The approch is same as the KthSmallestPairs. Here instead of another array,
     * we use the columns of the elements (like multiple arrays in matrix)
     */

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Element> pq = new PriorityQueue<Element>();
        for (int j = 0; j <= n - 1; j++) {
            pq.offer(new Element(0, j, matrix[0][j]));
        }

        for (int i = 0; i < k - 1; i++) {
            Element e = pq.poll();
            if (e.x >= n - 1) {
                continue; // end of the column (end of rows)
            }

            pq.offer(new Element(e.x + 1, e.y, matrix[e.x + 1][e.y]));
        }

        return pq.peek().val;

    }

    class Element implements Comparable<Element> {
        int x, y, val;

        public Element(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Element that) {
            return this.val - that.val;
        }
    }
}