import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Hack {

    public static void main(String args[]) throws Exception {
     

        
        int totalDistance = 21;
        int x = 5;
        int n = 2;

        Map<Integer, Integer> constraints = new TreeMap<>();
        constraints.put(2,4);
        constraints.put(4,8);

        Integer distnace = 0;
        Integer days = 0;
        Integer start = 0;
        Integer end = 0;

        for (Map.Entry<Integer, Integer> c : constraints.entrySet()) {
            end = c.getKey() - 1;
            int d = (end - start) * x;
            if (d + distnace > totalDistance) {
                break;
            } else {
                days = days + ((d) / x);
                distnace = d + distnace;
            }
            Integer val = c.getValue();
            if (distnace < totalDistance) {
                distnace = val + distnace;
                days++;
            } else {
                break;
            }
            start = c.getKey();
        }

        while (distnace < totalDistance) {
            days++;
            distnace = distnace + x;
        }

        // return days;
        System.out.println(days);
    }
}