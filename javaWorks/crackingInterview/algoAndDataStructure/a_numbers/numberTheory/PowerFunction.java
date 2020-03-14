public class PowerFunction {

    int power_recursive(int x, int n) {
        int m;
        if (n == 0)
            return 1;
        if (n % 2 == 0) {
            m = power_recursive(x, n / 2);
            return m * m;
        } else
            return x * power_recursive(x, n - 1);
    }

    public int pow(int x, int n) {

        if (x == 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        long ans = 1;
        long a = x;

        while (n != 0) {
            if (n % 2 != 0) { // This will happen always atleast once
                ans = (ans * a);
            }

            a = (a * a) % d;
            n = n / 2;
        }

        return (int) ans;
    }
}