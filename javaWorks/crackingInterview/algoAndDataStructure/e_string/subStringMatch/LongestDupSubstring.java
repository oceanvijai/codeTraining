class LongestDupSubstring {


    /**
        Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

        Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)

    **/

    /**

        Approch: The intution is, first fix a length we want to compare using binary search. And do substring search for that length

        If a substring match that length, then increase the length and try, otherwise decrease the length and compare.

        for comapre using rabin karp. Start from 0 create a hash of current length. Put it in hashSet. The remove the fist and add the next 
        to last and create a hash, and see if the hash is present. If not continue the same till we reach end.


        Time: Logn *( (n+m) ), where m is the length of the subsrting we compare
        so, O(nlogn)
    **/

    /**

    we can also do it using trie in O(n^2) as follows,

    https://www.youtube.com/watch?v=BMvotl5vHvM&feature=youtu.be
    
    **/

    


    public String solution(String s) {
        String ans = "";
        
        int left = 1;
        int right = S.length()-1;
        
        while(left <= right){
            int m = left + (right - left)/2;
            
            String dup = getDup(m,S);
            
            if(dup != null){
                ans = dup;
                left = m+1;
            }else{
                right = m-1;
            }
        }
        
        return ans;
    }
    
    private String getDup(int size, String s){
        long H = hash(s.substring(0,size));
        
        HashSet<Long> set = new HashSet();
        set.add(H);

        long pow = 1;
        for(int i=1;i<size;i++)
            pow = (pow * 31);
        
        int n = s.length();
        for(int i=size;i < n;i++){
            H = nextHash(size, pow, H, s.charAt(i-size), s.charAt(i));
            if(!set.add(H)){
                return s.substring(i-size+1, i+1);
            }
        }
        
        return null;
    }
    
    private long hash(String s){
        long h = 0;
        long a = 1;
        
        int n = s.length();
        for(int k=n;k>=1;k--){
            char ch = s.charAt(k-1);
            h += (ch - 'a' + 1) * a;
            a = (a*31);
        }
        
        return h;
    }
    
    private long nextHash(int k,long pow,long hash,char left,char right){
        return (hash - (left - 'a' + 1) * pow) * 31 + (right - 'a' + 1);
    }











}