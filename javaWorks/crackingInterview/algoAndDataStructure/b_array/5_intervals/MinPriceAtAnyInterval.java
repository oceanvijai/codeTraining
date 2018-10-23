import java.util.List;

public class MinPriceAtAnyInterval {
    /**
     * Give an list of intervals with prices
     * 
     * return a list of intervals with the min price at that time interval
     */

    // We cannot use interval merge, cause the merging is too complicated
    // we cannot use two pointers, cause the end and start is coupled with the price
    // and many
    // overlapping intervals may smaller price

    // So we go for the third technique, Time line

    // We create a timeLine and then, apply the min for that interval comparing with
    // the existing one

    List<Interval> getIntervalList(List<Interval> input) {
        int maxTime = input.stream().map((i) -> {
            return i.end;
        }).max(Comparator.comparing(Integer::valueOf)).get();

        int[] timeLine = new int[maxTime+1]; // initlize it with -1

        for (Interval i : input) {
            mapToTimeLine(i, timeLine);
        }

        List<Interval> ans = new ArrayList<>();
        for(int i = 0; i < maxTime; i++){
            // collect the continious intervals
        }

        return ans;
    }

    private void mapToTimeLine(Interval interval, int[] timeLine) {
        for (int i = interval.start; i <= interval.end; i++) {
            timeLine[i] = Math.min(timeLine[i], interval.price);
        }
    }

    private class Interval {
        int start;
        int end;
        int price;
    }

}