public class MyHashMap {
    Entry[] buckets;

    /** Initialize your data structure here. */
    public MyHashMap() {
        buckets = new Entry[10000];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = getBucketIndex(hash(key));

        if (buckets[i] == null) {
            buckets[i] = new Entry(-1, -1);
        }

        Entry prev = find(buckets[i], key);

        if (prev.next == null)
            prev.next = new Entry(key, value);
        else
            prev.next.val = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping for the key
     */
    public int get(int key) {
        int i = getBucketIndex(hash(key));
        if (buckets[i] == null)
            return -1;
        Entry node = find(buckets[i], key);
        return node.next == null ? -1 : node.next.val;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        int i = getBucketIndex(hash(key));
        if (buckets[i] == null)
            return;
        Entry prev = find(buckets[i], key);
        if (prev.next == null)
            return;
        prev.next = prev.next.next;
    }

    Entry find(Entry bucket, int key) {
        Entry node = bucket, prev = null;
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        return prev;
    }

    public int hash(int key) {
        return Integer.hashCode(key);
    }

    private int getBucketIndex(int hashValue) {
        return hashValue % buckets.length;
    }

    static class Entry {
        int key;
        int val;
        Entry next;

        Entry(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}