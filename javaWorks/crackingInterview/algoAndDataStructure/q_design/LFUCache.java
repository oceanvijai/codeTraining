class LFUCache {
    
    HashMap<Integer, Integer> cache;
    HashMap<Integer, Integer> frequenceCountMap;
    HashMap<Integer, LinkedHashSet<Integer>> groupMap;
    
    int capacity;
    int minCount;
    
    // Ok idea here is use 3 maps
    // 1.   for storing the key and val
    // 2.   for storing the key and their count  
    // 3.   for grouping the keys with similar count in LRU order
    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        frequenceCountMap = new HashMap<>(capacity);
        groupMap = new HashMap<>(capacity);
        this.minCount = 0;
    }
    
    public int get(int key) {
        if(this.capacity == 0 || !cache.containsKey(key)){
            return -1;
        }        
        
        // Setp 1: get the count of the given key
        int count = frequenceCountMap.get(key);
        
        // Step 2: remove the node from it corresponding groupMap
        if(count != 0){
            groupMap.get(count).remove(key);
        }
        
        // Additionally update the mincount so far, which will be used during the set       
        
        if(count == this.minCount && (groupMap.get(count) == null || groupMap.get(count).size() == 0)){
            this.minCount++; 
        }
        
        
        // Step 3: now lets place it in the correct group
        count++;
        LinkedHashSet group = groupMap.getOrDefault(count,new LinkedHashSet<>());
        group.add(key);
        groupMap.put(count, group);
        frequenceCountMap.put(key,count);
        
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        if(this.capacity <= 0){
            return;
        }
        
        // We they key is already there, then just update it
        if(cache.containsKey(key)){
            cache.put(key,value);
            get(key); // reuse the get to update its status
            return;
        }
        
        // Else a new append is required
        // If we reached the capacity, lests evist the LFU item
        if(cache.size() >= this.capacity){
            int toEvictKey = groupMap.get(minCount).iterator().next();
            groupMap.get(minCount).remove(toEvictKey);
            frequenceCountMap.remove(toEvictKey);
            cache.remove(toEvictKey);
        }
        
        // finally put it int the cache
        cache.put(key, value);
        frequenceCountMap.put(key,1);
        this.minCount = 1;
        
        LinkedHashSet group = groupMap.getOrDefault(1,new LinkedHashSet());
        group.add(key);
        groupMap.put(1, group);
        
    }
}