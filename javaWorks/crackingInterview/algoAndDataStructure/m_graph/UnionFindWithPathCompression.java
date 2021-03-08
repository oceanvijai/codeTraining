public class UnionFindWithPathCompression {

    static int[] rank; // the rank if its a root
    static int[] parent;


    public static void main(String[] args) {
        int[][] edgeList = new int[9][2];
        rank = new int[9];
        parent = new int[9];

        edgeList[0] = new int[]{1,2};
        edgeList[1] = new int[]{3,4};
        edgeList[2] = new int[]{5,6};
        edgeList[3] = new int[]{7,8};
        edgeList[4] = new int[]{2,4};
        edgeList[5] = new int[]{2,5};
        edgeList[6] = new int[]{1,3};
        edgeList[7] = new int[]{6,8};
        edgeList[8] = new int[]{5,7};

        connectedComponents(edgeList);

        System.out.println(parent);
    }

    private static void connectedComponents(int[][] edgeList){
        for(int[] edge : edgeList){
            unionFind(edge[0], edge[1]);
        }
    }

    private static void unionFind(int vertex1, int vertex2) {
        int parentOfVertex1 = find(vertex1);
        int parentOfVertex2 = find(vertex2);

        if(parentOfVertex1 == parentOfVertex2){
            // Its a cycle
            return;
        }

        union(parentOfVertex1, parentOfVertex2);
    }

    private static void union(int parentOfVertex1, int parentOfVertex2) {
        int rankOfParent1 = rank[parentOfVertex1];
        int rankOfParent2 = rank[parentOfVertex2];

        // Set the parent with the higher rank as root
        if(rankOfParent1 >= rankOfParent2){
            parent[parentOfVertex2] = parentOfVertex1;
            rank[rankOfParent1] += rank[rankOfParent2] + 1;
        }else{
            parent[parentOfVertex1] = parentOfVertex2;
            rank[rankOfParent2] += rank[rankOfParent1] + 1;
        }

    }

    private static int find(int vertex) {
        if(parent[vertex] == 0){
            return vertex;
        }

        int p =  find(parent[vertex]);
        parent[vertex] = p; // Here is where path compression happens
        return p;
    }


}
