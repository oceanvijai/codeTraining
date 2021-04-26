public class SatisfyEqualityEquations{

	/**
		Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

		Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

		Example 1:

		Input: ["a==b","b!=a"]
		Output: false
		Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
		Example 2:

		Input: ["b==a","a==b"]
		Output: true
		Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
		Example 3:

		Input: ["a==b","b==c","a==c"]
		Output: true
		Example 4:

		Input: ["a==b","b!=c","c==a"]
		Output: false
		Example 5:

		Input: ["c==c","b==d","x!=z"]
		Output: true

	**/


    /** 
        Approach 1: DFS: We can apply DFS also for this.

        1. Construct the graph, by creating a adjaceny list
        2. Then find the connected components. Also color each connected component with a different color
        3. The whenere we see a "!=" comparion, see if they have the same color. If so they, then return false,
            since two unqual literals cannot be in the same component

    **/



    public boolean equationsPossible(String[] equations) {

        // Construct the graph
        ArrayList<Integer>[] graph = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            graph[i] = new ArrayList();

        for (String eqn: equations) {
            if (eqn.charAt(1) == '=') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                graph[x].add(y);
                graph[y].add(x);
            }
        }


        // Find the connected components and color them differently
        int[] color = new int[26];
        int t = 0;
        for (int start = 0; start < 26; ++start) {
            if (color[start] == 0) {
                t++;
                Stack<Integer> stack = new Stack();
                stack.push(start);
                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == 0) {
                            color[nei] = t;
                            stack.push(nei);
                        }
                    }
                }
            }
        }

        // Check if when we get a "!=" has the same color
        for (String eqn: equations) {
            if (eqn.charAt(1) == '!') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                if (x == y || color[x] != 0 && color[x] == color[y])
                    return false;
            }
        }

        return true;
    }


	/**
		Approch 2: Union find

		Do two iterations.
		1. To union all the "==" literals
		2. Then verify if all "!=" holds true


	**/


	public boolean equationsPossible(String[] equations) {
        
        // First connect all "==" in one set
        Map<Character,Character> parent = new HashMap<>();
        for(String equation : equations){
            char[] eqArray = equation.toCharArray();
            // consider only "==" operators
            if(eqArray[1] == '!'){
                continue;
            }
            
            char f = eqArray[0];
            char s = eqArray[3];
            
            char parentOfF = find(f, parent);
            char parentOfS = find(s, parent);
            
            if(parentOfF != parentOfS){
                parent.put(parentOfS, parentOfF);
            }
        }
        
        // Now check if the "!=" condition holds true
        for(String equation : equations){
            char[] eqArray = equation.toCharArray();
            // consider only "!=" operators
            if(eqArray[1] == '='){
                continue;
             }
            
            char f = eqArray[0];
            char s = eqArray[3];
            
            char parentOfF = find(f, parent);
            char parentOfS = find(s, parent);
            
            if(parentOfF == parentOfS){
                System.out.println(equation+" parentOfF:"+parentOfF+" parentOfS:"+parentOfS);
                return false;
            }
        }
        
        return true;
    }
    
    private char find(char c, Map<Character,Character> parent){
        if(!parent.containsKey(c)){
            parent.put(c,c);
            return c;
        }
        
        if(parent.get(c) == c){
            return c;
        }
        
        parent.put(c, find(parent.get(c), parent));
        
        return parent.get(c);
    }
}