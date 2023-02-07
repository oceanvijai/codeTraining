public class CreditServer {
  
  /**
    Design a data structure that implements the following methods:
      • Insert: add a client with specified credit, replacing any existing entry for the
      client.
      • Remove: delete the specified client.
      • Lookup: return the number of credits associated with the specified client.
      • Add-to-all: increment the credit count for all current clients by the specified
      amount.
      • Max: return a client with the highest number of credits.
  **/
  
  /**
      Naturally we tend to use only hashMap, but the hashMap has O(n) for get max
      
      So we use tree map and hashMAp together. We use treeMap as a blanced binary search tree BST.
  
  **/
  
  
  Map<String, Integer> clientBook = new HashMap<>();
    TreeMap<Integer, Set<String>> creditBook = new TreeMap<>();
    private int globalCreditVal = 0;

    public void addClient(String client, int credit){
        if(clientBook.containsKey(client)){
          return;
        }
      
        clientBook.put(client, credit-globalCreditVal);
        if(!creditBook.containsKey(credit-globalCreditVal)){
          creditBook.put(credit-globalCreditVal, new HashSet<>());
        }
        creditBook.get(credit-globalCreditVal).add(client);
    }

    public void removeClient(String client){
      if(!clientBook.containsKey(client)){
          return;
      }

      Integer creditValue = clientBook.get(client);
      clientBook.remove(client);
      creditBook.get(creditValue).remove(client);
    }

    public int viewClient(String client){
      return clientBook.get(client)+globalCreditVal;
    }

    public int getMaxCredit(){
      return creditBook.isEmpty() ? 0 : creditBook.lastKey()+globalCreditVal;
    }

    public void addCreditToAll(int credit){
      this.globalCreditVal += credit;
    }
    
  
  
  
  public static void main(String[] args) {
    System.out.println("Hello world!");
    CreditServer server = new CreditServer();
    System.out.println(server.getMaxCredit());
    server.addCreditToAll(5);
    System.out.println(server.getMaxCredit());
    server.addClient("vijai",2);
    System.out.println(server.getMaxCredit()); // 2
    System.out.println(server.viewClient("vijai")); //2

    server.addClient("anand",7);
    System.out.println(server.getMaxCredit()); // 7
    System.out.println(server.viewClient("anand")); // 7 

    server.addCreditToAll(1);
    System.out.println(server.getMaxCredit()); // 8
    System.out.println(server.viewClient("vijai")); //3
    System.out.println(server.viewClient("anand")); // 8 
    
  }
  
  
  


}
