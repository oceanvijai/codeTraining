public class AccountsMerge{
	
	/**

		Given a list of accounts where each element accounts[i] is a list of strings, 
		where the first element accounts[i][0] is a name, and the rest of the elements 
		are emails representing emails of the account.

		Now, we would like to merge these accounts. Two accounts definitely belong to the same 
		person if there is some common email to both accounts. Note that even if two accounts have the 
		same name, they may belong to different people as people could have the same name. A person can 
		have any number of accounts initially, but all of their accounts definitely have the same name.

		After merging the accounts, return the accounts in the following format: the first element of each 
		account is the name, and the rest of the elements are emails in sorted order. The accounts themselves 
		can be returned in any order

		Example 1:

		Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],
							["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
		Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],
				["John","johnnybravo@mail.com"]]
		Explanation:
		The first and third John's are the same person as they have the common email "johnsmith@mail.com".
		The second John and Mary are different people as none of their email addresses are used by other accounts.
		We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
		['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
		Example 2:

		Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
							["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
							["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
		Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
				["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
				["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]


	**/


	// Approach 1: DFS

	/**
		Create a graph and find the connected components. How to form the graph ?
		Take the first email of the list and add an edge to all other emails in the account

		Then Do, DFS and find the connected components

		O(ALogA) , where is the size of the biggest account in the output. Due to sorting
		or
		O(Sum(aloga)) where a is the length of each account

	**/

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> email2Name = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        
        // Build the graph
        for(List<String> account : accounts){
            String name = null;
            String firstEmail = null;
            for(String e : account){
                if(name == null){
                    name = e;
                    continue;
                }
                
                if(firstEmail == null){
                    firstEmail = e;
                }
                
                graph.computeIfAbsent(firstEmail, x-> new ArrayList<String>()).add(e);
                graph.computeIfAbsent(e, x-> new ArrayList<String>()).add(firstEmail);
                email2Name.put(e, name);
            }
            
        }
                
        // Do DFS and find connected components
        Map<String, List<String>> graphComponents = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for(String email : graph.keySet()){
            
            if(!visited.contains(email)){
                List<String> componentList = new ArrayList<>();    
                LinkedList<String> stack = new LinkedList<>();
                stack.addFirst(email);
                visited.add(email);
                while(!stack.isEmpty()){
                    String node = stack.pollFirst();
                    componentList.add(node);
                    
                    
                    for(String edge : graph.get(node)){
                        if(!visited.contains(edge)){
                            visited.add(edge);
                            stack.addFirst(edge);
                        }
                    }
                }
                graphComponents.put(email, componentList);
            }
        }
                
        // Collect the components
        List<List<String>> ans = new ArrayList<>();
        for(String email : graphComponents.keySet()){
            List<String> account = graphComponents.get(email);
            Collections.sort(account);
            account.add(0, email2Name.get(email));
            ans.add(account);
        }
        
        return ans;
    }


    // Approach 2: Union find

	/**
		Create a graph and find the connected components. How to form the graph ?
		Initially all are its own parents like we always start

		then do union find and path compression
		using the parent relation, find and connected components or disjoint sets

		Then just group them

	**/


	public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> email2Name = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        
        // Build the initial parent child map
        for(List<String> account : accounts){
            String name = null;
            for(String e : account){
                if(name == null){
                    name = e;
                    continue;
                }
                parent.put(e,e);
                email2Name.put(e, name);
            }
        }
                
        // do union find
        for(List<String> account : accounts){
            // make an edge with the first email with the rest of the list
            for(int i = 1; i < account.size(); i++){
                String firstEmail = account.get(1);
                String currentEmail = account.get(i);
                
                String parentOfFirstEmail = unionFind(firstEmail, parent);
                String parentOfCurrentEmail = unionFind(currentEmail, parent);
                
                if(rank.getOrDefault(parentOfFirstEmail, 1) >= rank.getOrDefault(parentOfCurrentEmail, 1)){
                    parent.put(parentOfCurrentEmail,parentOfFirstEmail);
                    rank.put(parentOfFirstEmail, rank.getOrDefault(parentOfFirstEmail, 1) + 1);
                }else{
                    parent.put(parentOfFirstEmail, parentOfCurrentEmail);
                    rank.put(parentOfCurrentEmail, rank.getOrDefault(parentOfCurrentEmail, 1) + 1);
                }
            }
        }
                
        // Get the conected components
        Map<String, List<String>> components = new HashMap<>();
        for(String currentEmail : parent.keySet()){
            String parentOfCurrentEmail = unionFind(currentEmail, parent);
            components.computeIfAbsent(parentOfCurrentEmail, x-> new ArrayList<String>()).add(currentEmail);
        }
        
                
        // Collect the components
        List<List<String>> ans = new ArrayList<>();
        for(String email : components.keySet()){
            List<String> account = components.get(email);
            Collections.sort(account);
            account.add(0, email2Name.get(email));
            ans.add(account);
        }
        
        return ans;
    }
    
    
    private String unionFind(String email, Map<String, String> parentMap){
         if(parentMap.get(email).equals(email)){
             return parentMap.get(email);
         }
        
        String parent = unionFind(parentMap.get(email), parentMap);
        parentMap.put(email, parent);
        
        return parent;
    }

}