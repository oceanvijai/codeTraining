import java.util.LinkedList;
import java.util.Queue;

public class KthMultiple {

    /**
     * Design an algorithm to find the kth number such that the only prime factors
     * are 3, 5, and 7. Note that 3,5, and 7 do not have to be factors, but it
     * should not have any other prime factors.
     * 
     * For example, the first several multiples would be (in order) 1,3, 5, 7, 9,
     * 15,21.
     */

    /**
     * So this can be solved by having 3 queues one for 3, 5 and 7
     * 
     * So at every point we have pick the least from the list and multiply it with other 
     * element left and then add it to the ans
     * 
     * then repeat the same, until we can do it till k.
     * 
     * Also not that we need not look for the least item every time in all the queues
     * We just need to look at the heads of the queue
     * 
     * also, we can avoid 3 * 5 and 5 * 3 by making sure when we pick a element,
     * we multiply it only with the elements greater than queue.
     * 
     * i,e elements from 3 can be multiplied to 3, 5 and 7
     * elements from 5 can be multiplied from only 5 and 7 
     * Seven only 7
     * 
     */

    static Queue<Integer> queue3 = new LinkedList<>();
    static Queue<Integer> queue5 = new LinkedList<>();
    static Queue<Integer> queue7 = new LinkedList<>();

    int getKthMagicNumber(int k) {
        if (k < 0) {
            return 0;
        }
        queue3.add(1);

        int nextMin = 1;
        for (int i = 0; i <= k; i++) {
            nextMin = getNextItem();
        }

        return nextMin;
    }

    private int getNextItem() {
        int val = Math.min(Math.min(queue3.peek(), queue5.peek()), queue7.peek());

        if (val == queue3.peek()) {
            queue3.remove();
            queue3.add(3 * val);
            queue5.add(5 * val);
            queue7.add(7 * val);
        } else if (val == queue5.peek()) {
            queue5.remove();
            queue5.add(5 * val);
            queue7.add(7 * val);
        } else {
            queue7.remove();
            queue7.add(7 * val);
        }

        return val;
    }
}