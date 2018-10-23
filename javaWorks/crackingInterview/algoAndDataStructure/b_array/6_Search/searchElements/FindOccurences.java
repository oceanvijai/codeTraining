public class FindOccurences {
    
    public int findCount(final List<Integer> A, int B) {

        int firstIndex = searchIndexOf(B, A, true);
        if (firstIndex == -1) {
            return 0;
        }
        int lastIndex = searchIndexOf(B, A, false);

        return lastIndex - firstIndex + 1;

    }

    private int searchIndexOf(int searchElement, List<Integer> A, boolean searchFirst) {

        int start = 0;
        int end = A.size() - 1;

        int ans = -1;

        while (start <= end) {
            int mid = (end + start) / 2;

            if (A.get(mid) == searchElement) {
                ans = mid; // basically saving the last time we saw searchElement
                if (searchFirst) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (A.get(mid) < searchElement) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }

        return ans;
    }
}