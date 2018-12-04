public class MyHashSet {

    /**
     * 
     * Design a HashSet without using any built-in hash table libraries.
     * 
     * To be specific, your design should include these functions:
     * 
     * add(value): Insert a value into the HashSet.
     * 
     * contains(value) : Return whether the value exists in the HashSet or not.
     * 
     * remove(value): Remove a value in the HashSet. If the value does not exist in
     * the HashSet, do nothing.
     */

    private int buckets = 1000;
    private int itemsPerBucket = 1001;
    private boolean[][] table;

    /** Initialize your data structure here. */
    public MyHashSet() {
        table = new boolean[buckets][];
    }

    public int hash(int key) {
        return key % buckets;
    }

    public int pos(int key) {
        return key / buckets;
    }

    public void add(int key) {
        int hashkey = hash(key);

        if (table[hashkey] == null) {
            table[hashkey] = new boolean[itemsPerBucket];
        }
        table[hashkey][pos(key)] = true;
    }

    public void remove(int key) {
        int hashkey = hash(key);

        if (table[hashkey] != null)
            table[hashkey][pos(key)] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hashkey = hash(key);
        return table[hashkey] != null && table[hashkey][pos(key)];
    }
}