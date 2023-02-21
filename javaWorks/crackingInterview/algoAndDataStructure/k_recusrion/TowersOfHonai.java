public class TowersOfHonai{

  /**
      We know the problem statement
  
      Approach: We literally break down the problem into smaller parts.
      If you are given n rings. Fist solve n-1 rings. Like first move the n-1 rings to an intermedaite tower.
      So the left out is the largest ring.
      Now transfer the largest ring to the targeted tower.
      Now start over again. Do the same for the remaining n-1 rings
  
  **/

  
  private static void solveTower(int n, List<LinkedList<Integer>> towers, int fromT, int toT, int auxT){
      // base case
      if(n == 0){
        return;
      }

      // First move n-1 towers from fromT to auxT using toT
      solveTower(n-1, towers, fromT, auxT, toT);

      // Now now n is left in the fromT. Now move this to toT 
      towers.get(toT).addFirst(towers.get(fromT).pollFirst());
      System.out.println("move "+n+" from:"+fromT+" to:"+toT);

      // Now move the remaining n-1 towers from auxT to toT
      solveTower(n-1, towers, auxT, toT, fromT);
  }
  
  
  
  public static void main(String[] args) {
      // towers of hanoi

    List<LinkedList<Integer>> towers = new ArrayList<>();
    int n = 3;
    LinkedList<Integer> tower = new LinkedList<>();
    for(int i=n; i > 0; i--){
        tower.addFirst(i);
    }
    towers.add(tower);
    towers.add(new LinkedList<>());
    towers.add(new LinkedList<>());

    solveTower(n, towers,0,2,1);
    
  }






}
