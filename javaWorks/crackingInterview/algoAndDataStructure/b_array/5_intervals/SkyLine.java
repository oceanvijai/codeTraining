import java.util.PriorityQueue;

public class SkyLine {
    public List<int[]> getSkyline(int[][] buildings) {
        List<CriticalPoint> cPoints = new ArrayList<>();
        for (int[] point : buildings) {
            cPoints.add(new CriticalPoint(point[0], point[2], NATURE.START));
            cPoints.add(new CriticalPoint(point[1], point[2], NATURE.END));
        }

        Collections.sort(cPoints);
        
        for(CriticalPoint cp: cPoints){
            System.out.println("NAT:"+ cp.nature+ " val:"+cp.val+" point:"+cp.point);
        }
        
        System.out.println("-----------");

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        List<int[]> ans = new ArrayList<>();
        int currentHeight = 0;
        
        for (CriticalPoint cp : cPoints) {
            System.out.println("NAT:"+ cp.nature+ " val:"+cp.val);
            
            if(cp.nature == NATURE.START){
                queue.add(cp.val);
            }else{
                queue.remove(cp.val);
            }

            int h = queue.peek() != null ? queue.peek() : 0;

            if(currentHeight != h){
                ans.add(new int[]{cp.point,h});
                currentHeight = h;
            }

        }

        return ans;

    }
    
    private class CriticalPoint implements Comparable<CriticalPoint>{
        int point;
        NATURE nature;
        int val;

        public CriticalPoint(int point, int val, NATURE nature) {
            this.point = point;
            this.val = val;
            this.nature = nature;
        }

        public boolean equals(CriticalPoint a, CriticalPoint b) {
            return a.val == b.val;
        }
        
        @Override
        public int compareTo(CriticalPoint y) {
            if (this.point != y.point) {
                return this.point - y.point;
            } else if (this.nature != y.nature) { // meaning they are start and end, place the higher one
                if(this.nature == NATURE.START){
                    return -1;
                }else if(this.val - y.val != 0){
                    return -(this.val - y.val);
                }else{
                    return 1;
                }
            } else if (this.nature == NATURE.START) { // place the higher one first
                return -(this.val - y.val);
            } else { // place the smaller one first
                return (this.val - y.val);
            }
        }
    }

    private enum NATURE {
        START, END
    }
}