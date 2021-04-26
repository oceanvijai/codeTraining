public class ConnectedComponents{


	public int countConnected(int n, int[][] connections) {
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        int conectedCompoents = n;
        
        for(int[] connection: connections){
            int parentA = find(connection[0], parent);
            int parentB = find(connection[1], parent);
            
            if(parentA != parentB){
                parent[parentB] = parentA;
                conectedCompoents--;
            }
        }
        
        return conectedCompoents;
    }
    
    private int find(int node, int[] parent){
        if(node == parent[node]){
            return node;
        }
        
        parent[node] = find(parent[node], parent);
        return parent[node];
    }

}