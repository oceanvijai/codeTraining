public class SumOfDistances{

  /**
      https://leetcode.com/problems/sum-of-distances-in-tree/description/
      
      There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
      You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
      Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

      Example 1:
        Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
        Output: [8,12,6,10,10,10]
        Explanation: The tree is shown above.
        We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
        equals 1 + 1 + 2 + 2 + 2 = 8.
        Hence, answer[0] = 8, and so on.

      Example 2:
        Input: n = 1, edges = []
        Output: [0]

      Example 3:
        Input: n = 2, edges = [[1,0]]
        Output: [1,1]


  **/

  /**
    Approach 1: We can do a all path shortest path since tree is after all a graph, O(n^3) + O(N^2)

    Aprroach 2: Build the tree and do DFS from every node. O(N^2)

    Approach 1: We take advantage of these facts
      1. The distance between each node/vertex is one
      2. When we move from one node to another,we get closer to few node and far away from few node by a distance of 1
      3. Lets sasy we calculate the answer a the root node which can be any node, lets assume its always 0, say ans[0] / currentRootValue
      4. When we do a DFS from the root, we visit all its invisited children.
      5. All we need to know is, when we move from parent to the childen which is becoming a new root, which is the change of currentRootValue
      6. When we move to close to lets say 'c' nodes, we can subract it from the currentRootValue. Which is equal to the number of children of the new child/root
      7. We are also moving away from all the nodes in the tree by a value of 1, except from those we are moving closer
      8. We need to find what is the child count of each nodes to do step 6 and step 7.
      9. So, newRootValue = previousRootValue - nodesWentCloser + nodesWentFarther

      So we can do a DFS 1 to calculate 2 things, answer for root and child count of each nodes. O(n)
      Then we do DFS 2 from rooot to all the nodes which see at each step, how the root node value is changing based on the nodes we move away and towards. O(n)
      total O(n)

  **/


  int[] ans;
    int[] childcountDP;
    int[] sumDP;
    int[] visited;
    Map<Integer, List<Integer>> graph;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        // Build graph
        graph = buildGraph(edges);
        
        if(graph.isEmpty()){
            return new int[]{0};
        }
        

        // DFS 1 - Find the value for the root alone. In our case we can start with '0'
        // We also count how many children each vertex has
        childcountDP = new int[n];
        sumDP = new int[n];
        visited = new int[n];
        Arrays.fill(visited, -1);
        DFS1(0);
        

        // DFS 2 - Navigate the graph and 
        ans = new int[n];
        visited = new int[n];
        Arrays.fill(visited, -1);
        DFS2(n, 0, sumDP[0], 0, 0);

        return ans;
    }

    private void DFS2(int n, int root, int rootValue, int movedAwayFrom, int movedCloseTo){
        // Since we moved to a new root, we can find the value of the current count
        // remove the number of nodes which got 1 set closer
        // Add the number of node which went far away 2 step
        int currentRootValue = rootValue - movedCloseTo + movedAwayFrom;
        ans[root] = currentRootValue;
        visited[root] = 1;
        for(Integer vertex : graph.get(root)){
            if(visited[vertex] == -1){
                // Moving away from all the nodes except to the ones which got closer
                int away = (n-1) - childcountDP[vertex]-1;
                // Moving towards to the childern of the vertex we are visiting
                int towards = childcountDP[vertex];
                DFS2(n, vertex, currentRootValue, away, towards);
            }
        }
    }

    private int DFS1(int root){
        int count = 0;
        int sum = 0;
        visited[root] = 1;
        for(Integer vertex : graph.get(root)){
            if(visited[vertex] == -1){
               int c = DFS1(vertex);
               // Adding the current root, so add c(childs) to the vertex sum
               sum += sumDP[vertex] + c;
               // add to the count of the current root
               count += c;
            }
        }
        childcountDP[root] = count;
        sumDP[root] = sum;
        return count+1;
    }

     private Map<Integer, List<Integer>> buildGraph(int[][] edges){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges){
            if(!graph.containsKey(edge[0])){
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(edge[1]);

            if(!graph.containsKey(edge[1])){
                graph.put(edge[1], new ArrayList<>());
            }
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
     }


  
}
