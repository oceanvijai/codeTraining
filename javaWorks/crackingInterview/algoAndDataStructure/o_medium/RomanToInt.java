public class RomanToInt {

    
    public int convert(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && getNumber(s.charAt(i + 1)) > getNumber(s.charAt(i))) {
                ans = ans + getNumber(s.charAt(i + 1)) - getNumber(s.charAt(i));
                i++;
            } else {
                ans = ans + getNumber(s.charAt(i));
            }
        }

        return ans;

    }

    private int getNumber(char c) {
        int n = 0;
        switch (c) {
        case 'I':
            n = 1;
            break;
        case 'V':
            n = 5;
            break;
        case 'X':
            n = 10;
            break;
        case 'L':
            n = 50;
            break;
        case 'C':
            n = 100;
            break;
        case 'D':
            n = 500;
            break;
        case 'M':
            n = 1000;
            break;
        default:
            n = 0;
            break;
        }
        return n;
    }
}