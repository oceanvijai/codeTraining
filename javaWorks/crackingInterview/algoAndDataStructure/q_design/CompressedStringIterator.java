public class CompressedStringIterator {

    /**
     * Design and implement a data structure for a compressed string iterator. It
     * should support the following operations: next and hasNext.
     * 
     * The given compressed string will be in the form of each letter followed by a
     * positive integer representing the number of this letter existing in the
     * original uncompressed string.
     * 
     * next() - if the original string still has uncompressed characters, return the
     * next letter; Otherwise return a white space. hasNext() - Judge whether there
     * is any letter needs to be uncompressed.
     * 
     * Note: Please remember to RESET your class variables declared in
     * StringIterator, as static/class variables are persisted across multiple test
     * cases. Please see here for more details.
     * 
     * 
     */

    int currentIndex = 0;
    char currentChar;
    int repeatCount;

    String compressedString;

    public StringIterator(String compressedString) {
        this.compressedString = compressedString;
        setNextChar();
    }

    public char next() {
        if (repeatCount <= 0) {
            setNextChar();
        }

        repeatCount--;
        return currentChar;
    }

    public boolean hasNext() {
        return repeatCount > 0 || currentIndex < compressedString.length() - 1;
    }

    private void setNextChar() {
        if (currentIndex < compressedString.length() - 1) {
            this.currentChar = compressedString.charAt(currentIndex);
            currentIndex++;
            int j = currentIndex;
            while (j < compressedString.length() && Character.isDigit(compressedString.charAt(j))) {
                j++;
            }
            String num = compressedString.substring(currentIndex, j);

            repeatCount = Integer.parseInt(num);
            currentIndex = j;
        } else {
            this.currentChar = ' ';
        }
    }
}