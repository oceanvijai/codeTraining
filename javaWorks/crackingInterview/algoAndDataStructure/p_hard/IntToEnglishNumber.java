package CrackCode;

import java.util.LinkedList;

public class IntToEnglishNumber {
    /**
     * English Int: Given any integer, print an English phrase that describes the integer (e.g ., "One
     * Thousand, Two Hundred Thirty Four").
     */

    /**
     * Approach: break the numbers into chunks of thousands and form the string and make sure
     * which chunk belongs to which chunk
     */

    static String[] smalls = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static String[] bigs = {"", "Thousand", "Million", "Billion"};
    static String hundredString = "Hundred" ;
    static String negative = "Negative" ;

    static String convert(int num) {
        LinkedList<String> ansList = new LinkedList<>();
        int chunkIndex = 0; // chunks of thousands which will help decide the bigs
        while (num != 0) {
            int val = num % 1000;
            convert(val, chunkIndex, ansList);
            num = num / 1000;
            chunkIndex++;
        }
        return convertToString(ansList);
    }

    private static void convert(int val, int chunkIndex, LinkedList<String> ansList) {
        String big = bigs[chunkIndex];
        String hundred = "" ;
        String ten = "" ;
        String small = "" ;
        String chunkInString = "" ;

        // Hundred place
        if (val >= 100) {
            int h = val / 100;
            hundred = smalls[h]; // which will be from one to 9
            hundred = hundred + hundredString;
            val = val % 100;
        }

        // Tens place
        if (val >= 10 && val <= 19) {
            ten = smalls[val]; // which will be 10 to 19 =, look in smalls
        } else if (val > 20) {
            int t = val / 10;
            ten = tens[t]; // which will be after 20 look in tens
            val = val % 10;
        }

        //Ones place
        if (val >= 1 && val <= 9) {
            small = smalls[val % 10];
        }

        ansList.add(hundred + ten + small + big);
    }


    private static String convertToString(LinkedList<String> number) {
        String ans = "" ;
        while (!number.isEmpty()) {
            ans = ans + number.pollLast() + " " ;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(convert(19_523_146));
    }
}
