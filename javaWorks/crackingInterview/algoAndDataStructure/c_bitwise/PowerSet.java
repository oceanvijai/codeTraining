public class PowerSet{

    /*
      This is simple. Given a string we need to return a set which has all its subsets
      
      We just use the numbers from 1 to the length of the string and use each number's bit location to decie if we need to pick the char or not
      
      Time: O(n2^n) - outer loop 2^n, inner n
    */

    private static Set<String> powerSet(String str){
         int n = str.length();
         long m = ((long)Math.pow(2,n))-1;
         
         Set<String> ans = new HashSet<>();
         for(long i=0; i <= m; i++){
             long num = i;
             String s = "";
             for(int j=0; j < n; j++){
                 if((num & 1) == 1){
                    s = s+str.charAt(j);
                 }
                 num = num >> 1;
             }
             ans.add(s);
         }
         
         return ans;
     }


}
