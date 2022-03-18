public class EvaluateDivision{
	/**

	You are given equations in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

	The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

	Example 1:

	Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
	Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
	Explanation: 
	Given: a / b = 2.0, b / c = 3.0
	queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
	return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
	Example 2:

	Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
	Output: [3.75000,0.40000,5.00000,0.20000]
	Example 3:

	Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
	Output: [0.50000,2.00000,-1.00000,-1.00000]


	**/

	/**
		Approach: GRAPH DFS

		https://www.youtube.com/watch?v=UcDZM6Ap5P4


	**/


	class Node {
        String key;
        double val;

        public Node(String k, double v) {
            key = k;
            val = v;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Node>> g = buildGraph(equations, values); // build the graph

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++)
            result[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet(), g); // call dfs for each queries - 

        return result;
    }

    private double dfs(String s, String d, Set<String> visited, Map<String, List<Node>> g) {
        if (!(g.containsKey(s) && g.containsKey(d))) return -1.0; // if any vertex s, d is not in graph not path return -1;
        if (s.equals(d)) return 1.0; // if we reached on destination return 1; // if we reached on destination vertex

        visited.add(s); // mark visited
        for (Node node : g.get(s)) { // iterate all adjancent nodes
            if (!visited.contains(node.key)) {
                double ans = dfs(node.key, d, visited, g); // call dfs if not already visited
                if (ans != -1.0)
                    return ans * node.val;
            }
        }

        return -1;
    }
	
	// build graph from equarions variable and the values.
	private Map<String, List<Node>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, List<Node>> g = new HashMap();
        for (int i = 0; i < values.length; i++) {
            String src = equations.get(i).get(0);
            String dest = equations.get(i).get(1);
            g.computeIfAbsent(src, k -> new ArrayList()).add(new Node(dest, values[i])); 
            g.computeIfAbsent(dest, k -> new ArrayList()).add(new Node(src, 1 / values[i]));
        }
        return g;
    }
}