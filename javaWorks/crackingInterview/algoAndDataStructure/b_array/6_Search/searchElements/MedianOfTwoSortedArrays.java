public class MedianOfTwoSortedArrays {
    /**
     * Here the we have two sorted arrays
     * 
     * Now we need to find it median, meaning if they are merged, them its middle
     * element
     * 
     * Simple way is to merge and fidn the middle which is O(m+n)
     * 
     * But, we can do it in O(log M), where m is the length of the smallest array
     */

    /**
     * Observation 1:
     * 
     * If we want to find the mid of one array, we have (length /2 ) as the mid If,
     * we have two arrays and we have a mid of one array and we want to find the
     * equivalent mid in the other array, how can we do it ?
     * 
     * Equivalent meaning, the elements in left of first array and elements in left
     * of second array, plus The elements in the right of both the arrays should sum
     * up to the total elements
     * 
     * In other words, the elements in left of both array and right of both array
     * must be equal
     * 
     * So, median(x) + median(y) = (x + y + 1) / 2; -- one is added so it is good
     * with both even and odd
     * 
     * Ex 1: x = 1, 2, 4, 6, 8, 9, 10, 11, 13, 15, 16 y = 3, 4, 7
     * 
     * then, the mid of (x + y + 1) is, (14 + 1)/ 2 = 7, Now the 7 is formed from, 5
     * from x (11/2) and 1 from y (3/2) and we add 1 always
     * 
     * Ex 2: x = 1, 2, 4, 6, 8, 9, 10, 11, 13, 15, 16 y = 3, 4, 7, 12
     * 
     * then, the mid of (x + y) is, (15 + 1)/ 2 = 8, Now the 8 is formed from, 5
     * from x (11/2) and 2 from y (3/2), the added one dosent count here
     */

    /**
     * Observation 2: At the median, the elements in left of both x and y will be
     * smaller than elements in the right of the both medians
     * 
     * So if, this dosent happen,we can move our pointers accordingly
     */

    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {

        int m = a.size(), n = b.size();
        if (m > n)
            return findMedianSortedArrays(b, a);
        int imin, imax, x, y;
        imin = 0;
        imax = m;
        while (imin <= imax) {
            x = (imin + imax) / 2;
            y = (m + n + 1) / 2 - x;
            if (y > 0 && x < m && b.get(y - 1) > a.get(x)) {
                imin = x + 1;
            } else if (x > 0 && y < n && a.get(x - 1) > b.get(y)) {
                imax = x - 1;
            } else {
                // Figure out median now.
                int median1 = 0, median2 = 0;
                if (x == 0) {
                    median1 = b.get(y - 1);
                } else if (y == 0) {
                    median1 = a.get(x - 1);
                } else {
                    median1 = Math.max(a.get(x - 1), b.get(y - 1));
                }

                if ((m + n) % 2 == 1) {
                    return 1.0 * median1;
                }
                
                if (x == m) {
                    median2 = b.get(y);
                } else if (y == n) {
                    median2 = a.get(x);
                } else {
                    median2 = Math.min(a.get(x), b.get(y));
                }
                return 1.0 * (median1 + median2) / 2.0;
            }
        }
        return -1.0;

    }

}