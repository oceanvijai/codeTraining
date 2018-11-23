public class AutoComplete{
    
        Trie trie;
        String typedSoFar = "";
        
        public AutoComplete(String[] sentences, int[] times) {
            trie = new Trie();
            for(int i = 0; i < sentences.length; i++){
                trie.add(sentences[i], times[i]);
            }
        }
        
        public List<String> input(char c) {
            List < String > res = new ArrayList < > ();         
            if(c == '#'){
                trie.add(typedSoFar,1);
                typedSoFar = "";
            }else{
                typedSoFar = typedSoFar + c;
                List<TrieNode> suggestions = trie.lookUp(typedSoFar);
    
                // Sort them based on their times and send only first 3
                Collections.sort(suggestions,(a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
                for (int i = 0; i < Math.min(3, suggestions.size()); i++)
                    res.add(suggestions.get(i).sentence);
            }
            return res;
        }
        
        
        private class Trie{
            private TrieNode root;
            
            public Trie(){
                root = new TrieNode('*');
            }
            
            public void add(String sentence, int times){
                TrieNode current = root;
                for(int i =0; i < sentence.length(); i++){
                    if(current.map.get(sentence.charAt(i)) != null){
                        current = current.map.get(sentence.charAt(i));
                    }else{
                        TrieNode newNode = new TrieNode(sentence.charAt(i));
                        current.map.put(sentence.charAt(i),newNode);
                        current = newNode;
                    }
                }
                
                current.end = true;
                current.times = current.times + times;
            }
            
            public List<TrieNode> lookUp(String input){
                 StringBuilder sb = new StringBuilder("");
                List<TrieNode> ans = new ArrayList<>();
                
                // First traverse till the given input prefix before DFS
                TrieNode current = root;
                for(int i =0; i < input.length(); i++){
                    if(current.map.get(input.charAt(i)) != null){
                        sb.append(current.c != '*' ? current.c : "");
                        current = current.map.get(input.charAt(i));
                    }else{
                        // return empty list
                        return ans;
                    }
                }
                
                // Now lets start getting all possible answers
               
                DFS(current,sb,ans);
                
                return ans;
                
            }
            
            private void DFS(TrieNode root, StringBuilder sb, List<TrieNode> ans){
                sb.append(root.c);
                if(root.end == true){
                    ans.add(new TrieNode(sb.toString(),root.times));
                }
                
                
                for(Map.Entry<Character,TrieNode> e :root.map.entrySet()){
                    DFS(e.getValue(),sb,ans);
                }
                sb.deleteCharAt(sb.length()-1);
            }
            
        }
        
        private class TrieNode{
            char c;
            Map<Character,TrieNode> map;
            boolean end;
            int times;
            String sentence; // to gather the lookup sentence
            
            public TrieNode(char cb){
                c = cb;
                map = new HashMap<>();
            }
            
            public TrieNode(String s, int t){
                sentence = s;
                times = t;
            }
            
        }
    }
    
    /**
     * Your AutocompleteSystem object will be instantiated and called as such:
     * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
     * List<String> param_1 = obj.input(c);
     */