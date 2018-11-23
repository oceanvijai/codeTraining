public class Flatten2DVector {
    /**
     * Implement an iterator to flatten a 2d vector.
     * 
     * 
     * Input: 2d vector = [ [1,2], [3], [4,5,6] ] Output: [1,2,3,4,5,6] Explanation:
     * By calling next repeatedly until hasNext returns false, the order of elements
     * returned by next should be: [1,2,3,4,5,6].
     * 
     * As an added challenge, try to code it using only iterators in C++ or
     * iterators in Java.
     */

    List<List<Integer>> input;
    Iterator<Integer> currentIterator;

    public Vector2D(List<List<Integer>> vec2d) {
        this.input = vec2d;
        // List<Integer> lst = this.input.get(0);
        // this.input.remove(0);
        // this.currentIterator = lst.iterator();
        updateNextIterator();
    }

    @Override
    public Integer next() {
        Integer val = null;
        if (this.currentIterator != null) {
            if (this.currentIterator.hasNext()) {
                val = this.currentIterator.next();
            } else {
                updateNextIterator();
                val = this.currentIterator.next();
            }

            updateNextIterator();
        }

        return val;
    }

    private void updateNextIterator() {

        if (this.currentIterator != null && this.currentIterator.hasNext()) {
            return;
        }

        if (this.input.size() != 0) {
            while (this.input.size() > 0) {
                List<Integer> lst = this.input.get(0);
                this.input.remove(0);
                this.currentIterator = lst.iterator();
                if (this.currentIterator.hasNext()) {
                    break;
                }
                this.currentIterator = null;
            }
        }
    }

    @Override
    public boolean hasNext() {
        if (this.currentIterator != null) {
            return this.currentIterator.hasNext();
        }

        return false;
    }
}