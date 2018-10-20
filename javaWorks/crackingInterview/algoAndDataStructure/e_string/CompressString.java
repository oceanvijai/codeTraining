public class CompressString {
    public static void main(String[] args) {

    }

    /*
     * Implement a method to perform basic string compression using the counts of
     * repeated characters. For example, the string aabcccccaaa would become
     * a2blc5a3. If the "compressed" string would not become smaller than the
     * original string, your method should return the original string. You can
     * assume the string has only uppercase and lowercase letters (a - z).
     */

    /*
     * This will work but the time is O(n * k^2) n - length of the original string
     * k^2 for the string append So its better to use StringBuilder
     */
    private String compressBad(String str) {
        String compressedString = "";
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            // If next character is different than current, append this char to result.*1
            if (i + 1 >= str.length() && str.charAt(i) != str.charAt(i + 1)) {
                compressedString += " " + str.charAt(i) + countConsecutive;
                countConsecutive = 0;
            }
        }
        return compressedString.length() < str.length() ? compressedString : str;
    }

    /*
     * This is better, but if we are able to find the lenght of the compressed
     * string up front Then the string builder can be initilized with full capacity
     * and need not resize
     */

    String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            // If next character is different than current, append this char to result. *1
            if (i + 1 >= str.length() && str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    /*
     * So can calculate the length of the compressed string up front and use it to
     * intitlize the String builder
     */

    int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            // If next character is different than current, increase the length. *1
            if (i + 1 >= str.lengthO && str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }

}