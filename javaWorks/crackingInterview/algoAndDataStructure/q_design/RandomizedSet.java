public class RandomizedSet {
    
    Map<Integer,Integer> map;
    ArrayList<Integer> list;
    Random rdm;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rdm = new Random();
        System.out.println("init");
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        System.out.println("insert");
        if(map.containsKey(val)){
            return false;
        }
        
        list.add(val);
        map.put(val,list.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        System.out.println("remove");
        // SWAP the removed index with the last index and delete the last index

        if(map.containsKey(val)){
            int removeIndex = map.get(val);
            int lastIndexValue = list.get(list.size()-1);
            list.set(removeIndex,lastIndexValue);
            map.put(lastIndexValue,removeIndex);
            list.remove(list.size()-1);
            map.remove(val);
            return true;
        }else{
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        System.out.println("random");
        return list.get(rdm.nextInt(list.size()));
    }
}