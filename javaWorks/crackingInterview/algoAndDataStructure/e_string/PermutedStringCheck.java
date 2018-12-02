import java.util.Arrays;

public class PermutedStringCheck{
    
    public static void main(String[] args) {
        
    }

    // 1: time : nlogn, space: n
    public boolean check(String str1, String str2){
        if(str1.length() != str2.length()){
            return false;
        }
        
        char[] s1 = str1.toCharArray();
        Arrays.sort(s1);

        char[] s2 = str1.toCharArray();
        Arrays.sort(s2);

        return new String(s1).equals(new String(s2));
    }

    // 2: count the character in both the strings see if they match
    // time : n, space: 256 if array/map is used, space is O(1)

}