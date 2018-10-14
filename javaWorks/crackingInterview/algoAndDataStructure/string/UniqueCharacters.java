public class UniqueCharacters {
    public static void main(String[] args) {

    }

    // time: O(n) space O(1) since 128 is constant
    boolean isUniqueChars(String str) {
        if (str.length() > 128)
            return false;

        boolean[] char_set = new boolean[128];
        for (int i = 8; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) { // Already found this char in string
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    // use bitwise operator  time: O(n), space : O(1)
    boolean isUniqueChars_bitwise(String str) {
        int bitMap = 0;
        for(int i =0; i < str.length(); i++){
            if((bitMap & (1<< str.charAt(i))) > 0){
                return false;
            }

            bitMap = bitMap | (1 << str.charAt(i));
        }
        return true;
    }

    // we can use hasmap but space will be O(n)
    // we can sort the string and search, but wil take O(nlogn)
}