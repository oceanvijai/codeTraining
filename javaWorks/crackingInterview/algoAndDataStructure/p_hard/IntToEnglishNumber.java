package CrackCode;

import java.util.LinkedList;

public class IntToEnglishNumber {
    /**
     * English Int: Given any integer, print an English phrase that describes the
     * integer (e.g ., "One Thousand, Two Hundred Thirty Four").
     */

    /**
     * Approach: break the numbers into chunks of thousands and form the string and
     * make sure which chunk belongs to which chunk
     */

    String[] bigs = { "", "Thousand", "Million", "Billion", "Trillion" };
    String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    String[] smalls = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };

    public String numberToWords(int num) {

        if (num == 0) {
            return "Zero";
        }

        int chunkNumber = 0;
        LinkedList<String> stack = new LinkedList<>();

        while (num != 0) {
            String s = chunkToString(num % 1000, bigs[chunkNumber]);
            System.out.println(s + " " + s.length());
            stack.addFirst(s);
            num = num / 1000;
            chunkNumber++;
        }

        String ans = "";
        while (!stack.isEmpty()) {
            String c = stack.pollFirst();
            if (c.length() > 0) {
                ans = ans + c + " ";
            }
        }

        return ans.trim();
    }

    private String chunkToString(int chunk, String sufix) {
        if (chunk == 0) {
            return "";
        }
        String hundred = "";
        String ten = "";
        String one = "";

        if (chunk >= 100) {
            int h = chunk / 100;
            hundred = smalls[h];
            hundred = hundred + " Hundred ";
            chunk = chunk % 100;
        }

        if (chunk >= 20) {
            int t = chunk / 10;
            ten = tens[t] + " ";
            chunk = chunk % 10;
        }

        if (chunk >= 1 && chunk <= 19) {
            one = smalls[chunk] + " ";
        }

        return (hundred + ten + one + sufix).trim();

    }

    public static void main(String[] args) {
        System.out.println(convert(19_523_146));
    }
}
