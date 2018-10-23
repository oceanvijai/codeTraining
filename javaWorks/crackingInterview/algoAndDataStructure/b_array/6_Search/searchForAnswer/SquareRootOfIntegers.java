public class SquareRootOfIntegers {

    public int sqrt(int a) {

        if (a == 0 || a == 1)
            return a;

        long start = 1;
        long end = (a / 2) + 1;
        long ans = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (mid * mid == a) {
                return (int) mid;
            }

            if (mid * mid < a) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return (int) ans;
    }
}