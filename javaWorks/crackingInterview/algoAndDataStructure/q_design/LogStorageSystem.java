public class LogStorageSystem {

    /**
     * You are given several logs that each log contains a unique id and timestamp.
     * Timestamp is a string that has the following format:
     * Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All
     * domains are zero-padded decimal numbers.
     * 
     * Design a log storage system to implement the following functions:
     * 
     * void Put(int id, string timestamp): Given a log's unique id and timestamp,
     * store the log in your storage system.
     * 
     * 
     * int[] Retrieve(String start, String end, String granularity): Return the id
     * of logs whose timestamps are within the range from start to end. Start and
     * end all have the same format as timestamp. However, granularity means the
     * time level for consideration. For example, start = "2017:01:01:23:59:59", end
     * = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find
     * the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
     */

    private TreeMap<String, List<Integer>> map;
    private Map<String, Integer> indexes;
    private String min = "2000:01:01:00:00:00", max = "2017:12:31:23:59:59";

    public LogSystem() {
        map = new TreeMap<>();
        indexes = new HashMap<>();
        indexes.put("Year", 4);
        indexes.put("Month", 7);
        indexes.put("Day", 10);
        indexes.put("Hour", 13);
        indexes.put("Minute", 16);
        indexes.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        if (!map.containsKey(timestamp)) {
            map.put(timestamp, new ArrayList<>());
        }

        map.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> result = new ArrayList<>();

        Map<String, List<Integer>> subMap = map.subMap(
                s.substring(0, indexes.get(gra)) + min.substring(indexes.get(gra)), true,
                e.substring(0, indexes.get(gra)) + max.substring(indexes.get(gra)), true);

        return subMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * This solution is based on converting the given timestap into a number. This
     * can help because retrieval of Logs lying within a current range can be very
     * easily done if the range to be used can be represented in the form of a
     * single number. Let's look at the individual implementations directly.
     * 
     * put: Firstly, we split the given timestamp based on : and store the
     * individual components obtained into an stst array. Now, in order to put this
     * log's entry into the storage, firstly, we convert this timestamp, now
     * available as individual components in the stst array into a single number. To
     * obtain a number which is unique for each timestamp, the number chosen is such
     * that it represents the timestamp in terms of seconds. But, doing so for the
     * Year values can lead to very large numbers, which could lead to a potential
     * overflow. Since, we know that the Year's value can start from 2000 only, we
     * subtract 1999 from the Year's value before doing the conversion of the given
     * timestamp into seconds. We store this timestamp(in the form of a number now),
     * along with the Log's id, in s listlist, which stores data in the form
     * [converted\_timestamp, id][converted_timestamp,id].
     * 
     * retrieve: In order to retrieve the logs' ids lying within the timestamp ss
     * and ee, with a granularity gragra, firstly, we need to convert the given
     * timestamps into seconds. But, since, we need to take care of granularity,
     * before doing the conversion, we need to consider the effect of granularity.
     * Granularity, in a way, depicts the precision of the results. Thus, for a
     * granularity corresponding to a Day, we need to consider the portion of the
     * timestamp considering the precision upto Day only. The rest of the fields can
     * be assumed to be all 0's. After doing this for both the start and end
     * timestamp, we do the conversion of the timestamp with the required precision
     * into seconds. Once this has been done, we iterate over all the logs in the
     * listlist to obtain the ids of those logs which lie within the required range.
     * We keep on adding these ids to a resres list which is returned at the end of
     * this function call.
     */

    // TreeMap <Long, Integer> map;
    // HashMap <String, Integer> h;

    // public LogSystem() {
    // map = new TreeMap <Long, Integer> ();
    // h = new HashMap();
    // h.put("Year", 0);
    // h.put("Month", 1);
    // h.put("Day", 2);
    // h.put("Hour", 3);
    // h.put("Minute", 4);
    // h.put("Second", 5);
    // }

    // public void put(int id, String timestamp) {
    // int[] st =
    // Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
    // map.put(convert(st), id);
    // }

    // public List<Integer> retrieve(String s, String e, String gra) {
    // ArrayList <Integer> res = new ArrayList();
    // long start = granularity(s, gra, false);
    // long end = granularity(e, gra, true);
    // for (long key: map.tailMap(start).keySet()) {
    // if (key >= start && key < end)
    // res.add(map.get(key));
    // }
    // return res;
    // }

    // public long convert(int[] st) {
    // st[1] = st[1] - (st[1] == 0 ? 0 : 1);
    // st[2] = st[2] - (st[2] == 0 ? 0 : 1);
    // return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60
    // + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
    // }

    // public long granularity(String s, String gra, boolean end) {
    // String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
    // String[] st = s.split(":");
    // for (int i = 0; i <= h.get(gra); i++) {
    // res[i] = st[i];
    // }
    // int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
    // if (end)
    // t[h.get(gra)]++;
    // return convert(t);
    // }
}