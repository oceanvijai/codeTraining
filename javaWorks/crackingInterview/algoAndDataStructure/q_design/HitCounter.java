public class HitCounter {
    int[] timeStamps;
    int[] hits;

    /** Initialize your data structure here. */
    public HitCounter() {
        timeStamps = new int[300];
        hits = new int[300];

    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        int tIndex = timestamp % 300;

        if (timeStamps[tIndex] == timestamp) { // got hit at the same time
            hits[tIndex]++;
        } else {
            hits[tIndex] = 1;
        }
        timeStamps[tIndex] = timestamp;

    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int hitCount = 0;

        for (int i = 0; i < 300; i++) {
            if (timestamp - timeStamps[i] < 300) {
                hitCount += hits[i];
            }
        }

        return hitCount;
    }
}