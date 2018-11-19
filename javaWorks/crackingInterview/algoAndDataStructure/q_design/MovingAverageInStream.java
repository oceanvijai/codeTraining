public class MovingAverageInStream {
    int insertIndex;
    int[] bucket;
    int n, maxSize;
    double sum = 0;

    /** Initialize your data structure here. */
        public MovingAverage(int size) {
            bucket = new int[size];
            maxSize = size;
        }

    public double next(int val) {
        if (n < maxSize) {
            bucket[insertIndex] = val;
            sum = sum + val;
            insertIndex++;
            n++;
        } else {
            insertIndex = insertIndex % maxSize;
            sum = sum - bucket[insertIndex];
            sum = sum + val;
            bucket[insertIndex] = val;
            insertIndex++;
        }
        return (double) (sum / n);
    }
}