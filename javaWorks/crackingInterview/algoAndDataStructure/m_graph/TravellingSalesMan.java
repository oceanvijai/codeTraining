public class TravellingSalesMan{
	
	/**
		The problem is that we visit every city with min cost


	**/

	/**
		Approach 1: Recursion
		So, we start from origin, then we try out each possible routes
		
		Each recusrion tress n , then n-1, then n-2 in each path
		So O(n!) nodes in the recursion tree.

	**/


	public static void main(String args[]) {
        System.out.println("Hello World!");
        int[][] adjencyMatrix = {{0,1,15,6}, {2,0,7,3},{9,6,0,12}, {10,4,8,0}};
        Set<Integer> unVisitedCities = new HashSet<>();
        unVisitedCities.add(1);
        unVisitedCities.add(2);
        unVisitedCities.add(3);
        System.out.println(travelingSalesman_recursion(adjencyMatrix, 0,unVisitedCities));
    }

    private static int travelingSalesman_recursion(int[][] adjencyMatrix, int from ,Set<Integer> unVisitedCities){

    	// When we visited all cities, now we have to go back to 0
        if(unVisitedCities.size() == 0){
            return adjencyMatrix[from][0];
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= adjencyMatrix.length; i++){
            if(unVisitedCities.contains(i)){
                unVisitedCities.remove(i);
                min = Math.min(min, adjencyMatrix[from][i]+ travelingSalesman_recursion(adjencyMatrix, i, unVisitedCities));
                unVisitedCities.add(i);
            }
        }
        return min;
    }


    /**
		Approach 2: HeldKarp DP O(2^n n^2)

		Step 1: Generate all combinations of the vertices (excluding origin). There will be 2^n combinations and will take 2^n time to generate it

		Step 2: For each combination, lets say vertices set {1,4,5}, try all other vertices as starting point

		Step 3: From each starting point, we need to try alll the vertices in the set as the next point

		Step 4: If we find a already tried out starting and set combination, pick the cost direclty

		There are 2^n set combinations.
		For each combination we try n vertices
		And for each set and vertex, we try n-1 next vertices

    **/




	private static int travelingSalesman_dp_HeldKarp(int[][] adjencyMatrix){
        int n = adjencyMatrix.length;

        // Generate the combination set
        List<Set<Integer>> allSets = new ArrayList<Set<Integer>>();
        generateCombinations(1, n, allSets, new HashSet<Integer>());

        // DP
        Map<SetIdentifier, Integer> dp = new HashMap<>();
        

        // sort the set in the order of the size
        allSets.sort((set1, set2) -> set1.size() - set2.size());
        //System.out.println(allSets);

        // iterate the set
        for(Set<Integer> set : allSets){
            // Try every vertex when is not in the set a starting point
            for(int startVertex = 1; startVertex < n; startVertex++){
                int min = Integer.MAX_VALUE;
                // The the vertex is already in the set, Ignore.
                if(set.contains(startVertex)){
                    continue;
                }            
                // calculate the min cost for these set
                if(set.isEmpty()){
                    dp.put(new SetIdentifier(startVertex, set), adjencyMatrix[startVertex][0]);
                    continue;
                }
                Set<Integer> s = new HashSet<>(set);
                for(Integer vertex : set){
                    int cost = adjencyMatrix[startVertex][vertex];
                    cost += getCost(vertex, s,dp);
                    min = Math.min(min, cost);
                }
                // Save it to DP
                dp.put(new SetIdentifier(startVertex, set), min);
            }
        }

        //System.out.println(dp);
        // get the cost of the set will all the vertices
        Set<Integer> allVertexSet = new HashSet<>();
        for(int i=1; i < adjencyMatrix.length; i++) {
            allVertexSet.add(i);
        }
        // Since so far we didnt inlcude the cost from the origin
        int ans = Integer.MAX_VALUE;
        Set<Integer> tempSet = new HashSet<>(allVertexSet);
        for(int k : allVertexSet){
            int cost = adjencyMatrix[0][k] + getCost(k, tempSet,dp);
            ans = Math.min(ans, cost);
        }
        return ans;
    }

    private static int getCost(int startIndex, Set<Integer> verticesSet, Map<SetIdentifier, Integer> dp){
        verticesSet.remove(startIndex);
        int cost = dp.get(new SetIdentifier(startIndex, verticesSet));
        verticesSet.add(startIndex);
        return cost;
    }

    private static void generateCombinations(int start, int n, List<Set<Integer>> allSets, Set<Integer> currentSet){
        if(start == n+1){
            return;
        }

        allSets.add(new HashSet<>(currentSet));
        for(int i = start; i < n; i++){
            currentSet.add(i);
            generateCombinations(i+1, n, allSets, currentSet);
            currentSet.remove(i);
        }
    }

    private static class SetIdentifier{
        public int startIndex;
        public Set<Integer> set;

        public SetIdentifier(int startIndex, Set<Integer> set){
            this.startIndex = startIndex;
            this.set = set;
        }

        @Override
        public boolean equals(Object anotherSet){

            return this.startIndex == ((SetIdentifier)anotherSet).startIndex && this.set.equals(((SetIdentifier)anotherSet).set);
        }

        @Override
        public int hashCode(){
            return this.startIndex + set.hashCode();
        }

        public String toString(){
            return startIndex + "->" + set.toString();
        }
    }

}