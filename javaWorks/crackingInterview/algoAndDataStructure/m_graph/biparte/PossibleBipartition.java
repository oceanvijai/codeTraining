class  possibleBipartition{

    /**

        Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

        Each person may dislike some other people, and they should not go into the same group. 

        Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

        Return true if and only if it is possible to split everyone into two groups in this way.

    **/

    /**

        Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
        Output: true
        Explanation: group1 [1,4], group2 [2,3]


        Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
        Output: false

        Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
        Output: false

    **/



    public boolean solution(int N, int[][] dislikes) {
        
        // This is an application of biparte graph algo
        // Apply BFS to solve this
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] dislike : dislikes){
            List<Integer> temp = graph.containsKey(dislike[0]) ? graph.get(dislike[0]): new ArrayList<>();
            temp.add(dislike[1]);
            graph.put(dislike[0], temp);
            temp = graph.containsKey(dislike[1]) ? graph.get(dislike[1]): new ArrayList<>();
            temp.add(dislike[0]);
            graph.put(dislike[1], temp);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[N + 1];
        for(int i = 0; i < N; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                queue.offer(i);
                while(!queue.isEmpty()){
                    int cur = queue.poll();
                    List<Integer> temp = graph.get(cur);
                    if(temp == null) continue;
                    for(Integer next : temp){
                        if(visited[next] == visited[cur]) return false;
                        if(visited[next] == 0){
                            visited[next] = -visited[cur];
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private List<Integer> childFor(int p, int[][] dislikes){
        List<Integer> childs = new ArrayList<>();
        for(int i=0; i < dislikes.length; i++){
            if(dislikes[i][0] == p){
                childs.add(dislikes[i][1]);
            }
        }
        
        return childs;
    }
}