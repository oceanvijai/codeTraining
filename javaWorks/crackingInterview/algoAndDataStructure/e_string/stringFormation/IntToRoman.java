public class IntToRoman {
    public String intToRoman(int num) {
        String[] Thousand = { "", "M", "MM", "MMM" };
        String[] hundred = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

        String thousandStr = Thousand[num / 1000];
        String hundredStr = hundred[(num % 1000) / 100];
        String tensStr = tens[(num % 100) / 10];
        String onesStr = ones[num % 10];

        return thousandStr + hundredStr + tensStr + onesStr;

    }
}