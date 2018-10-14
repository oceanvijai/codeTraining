public class IsRotatedString {

    /**
     * Assume you have a method iSSubstring which checks if one word is a substring
     * of another. Given two strings, s1 and s2, write code to check if s2 is a
     * rotation of s1 using only one call to isSubstring 
     * (e.g., Uwaterbottleu is a rotation ofuerbottlewatU).
     */

    /**
     * The trick here is to see how the strings are rotated
     * s1 = xy = waterbottle
     * x = wat 
     * y = erbottle
     * so s2 = yx = erbottlewat
     * 
     * so, xy = s1 and yx = s2
     * 
     * Note that xyxy has yx in the middle
     * So s1s1 will somewhere has s2 in the middle
     * 
     * time: O(n)
     */

    public boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        return isSubString(s1 + s1, s2);
    }

    private boolean isSubString(String s1, String s2) {
        // Some logic
        return true;
    }
}