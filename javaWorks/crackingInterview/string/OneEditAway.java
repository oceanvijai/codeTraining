public class OneEditAway {
    public static void main(String[] args) {

    }

    /*
     * There are three types of edits that can be performed on strings: insert a
     * character, remove a character, or replace a character. Given two strings,
     * write a function to check if they are one edit (or zero edits) away. EXAMPLE
     * pale, ple -> true pales, pale -> true pale, bale -> true pale, bae -> false
     */

    boolean oneEditAway(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditlnsert(first, second);
        } else if (first.length() - 1 == second.length()) {
            return oneEditlnsert(second, first);
        }
        return false;
    }

    private boolean oneEditReplace(String str1, String str2) {
        int count = 1;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean oneEditlnsert(String str1, String str2) {
        int index1 = 0, index2 = 0;
        int count = 0;
        while (index1 < str1.length() && index2 < str2.length()) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if(index1 != index2){
                    return false;
                }
                
                index2++;
            }else{
                index1++;
                index2++;
            }
        }
        return true;
    }

}