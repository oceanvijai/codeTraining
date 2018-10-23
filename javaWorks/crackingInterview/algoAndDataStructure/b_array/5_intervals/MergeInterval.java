public class MergeInterval {

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     * 
     * For example:
     * 
     * Given [1,3],[2,6],[8,10],[15,18],
     * 
     * return [1,6],[8,10],[15,18]
     */

    /**
     * the interval needs to be SORTED based on start time
     * 
     * 1. take two pointer as start and end Initilize it with the first interval
     * start and end
     * 
     * 2. start iteration from the second interval
     * 
     * 3. If end > current interval start updated the end to MAX(end, current.end)
     * this way we are expanding the interval
     * 
     * 4. If the end < current.start Then the previour interval ends here. And new
     * interval start here
     * 
     * 5. continue this until the end of the input
     * 
     */

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        ArrayList<Interval> ans = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        int i = 1;
        while (i < intervals.size()) {
            Interval val = intervals.get(i);

            if (end < val.start) {
                ans.add(new Interval(start, end));
                start = val.start;
                end = val.end;
            } else if (end > val.start && end < val.end) {
                end = val.end;
            }
            i++;

        }

        ans.add(new Interval(start, end));

        return ans;

    }

}