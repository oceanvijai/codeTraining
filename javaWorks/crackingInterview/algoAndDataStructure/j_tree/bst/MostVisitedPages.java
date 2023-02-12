public class MostVisitedPages{
  
  
  /**
       Write a function to read the next line from a log file, and a function to find the k most visited pages, 
       where k is an input to the function. Optimize performance for the situation where calls to the two functions are interleaved. 
       You can assume the set of distinct pages is small enough to fit in RAM
  **/
  
  
  /**
    As a concrete example, suppose the log file ids appear in the following order: g,a,t,t,a,a,a,g,t,c,t,a,t,i.e., 
    there are four pages with ids a,c,g,t. After the first 10 lines have been read, 
    the most common page is a with a count of 4, and the next most common page is t with a count of 3.
  **/
  
  
  /**
    First we need to keep the current count of all the nodes
    Need to udpate the count on the next line read
    Find the top k pages from the list
    
    Solution 1: Brute force: HasMap all the pages and counts. Iterate over them to find the k most visited. Time O(n).

    Solution 2: 
    We can have an hashmap with the pages count and a tree map to store the count & pages
    When we add a page we update both maps
    To get the top K, do head
    Time: M - disctinct pages
    O(M) for reading a log time (for inserting into the treeSet)
    O(M) For getting the top K elements at anytime
  
  **/
  
  
  Map<Character, PageVisit> pages = new HashMap<>();
  
  TreeSet<PageVisit> bst = new TreeSet<>((o1,o2) -> {
      // To make sure the tree set is in decending order
      if (o1.count < o2.count)
        return 1; 
        // For TreeSet euqals is not called, instead this compartor is use to equalte
        // If we two numbers are equal only if they have the same name
      else if(o1.name == o2.name && o1.count == o2.count)
        return 0;
      else
        return -1;
    });
    
    // read log line and populate map and set
    public void read(char[] logs){
      for(Character l : logs){
        if(!pages.containsKey(l)){
          pages.put(l, new PageVisit(l));
        }

        
        PageVisit vst = pages.get(l);
        bst.remove(vst);
        vst.increment();
        bst.add(vst);
      }
    }

    public String mostVisitedPages(){
      return bst.toString();
    }
  }

  private static class PageVisit{
     char name;
     int count;

    public PageVisit(char name){
      this.name = name;
      this.count = 0;
    }

    public void incrementCount(int i){
      this.count += i;
    }

    public void increment(){
      incrementCount(1);
    }

    @Override // Not useful for treeMap
    public boolean equals(Object obj){
      return ((PageVisit)obj).name == this.name;
    }

    @Override
    public int hashCode(){
      return Character.getNumericValue(this.name);
    }

    public String toString(){
      return this.name +","+String.valueOf(count);
    }
    
  }
  
  

  /**
    Lets solve this in O(1) for log read and O(K) for top K elements
    
    Since the value is incrementing only by one everytime, we have manage this by having a bucket of pages for every count in 
    the form of a linked list.
    Everytime we read a line, we can move the page to to next link in the linked list.
  **/



    public static void main(String[] args) {
    System.out.println("Hello world!");
    char[] logs = new char[]{'g','a','t','t','a','a','a','g','t','c','t','a','t'};

    Map<Character, BucketList> pages = new HashMap<>();
    BucketList head = null;
    
    BucketList tail = new BucketList();
    tail.count = 1;

    for(char c : logs){
        if(!pages.containsKey(c)){
          tail.bucket.add(c);
          pages.put(c,tail);
        }else{
          BucketList currentBucket = pages.get(c);
          currentBucket.bucket.remove(c);
          
          if(currentBucket.next == null){
            // If its at the head of the linked list
            currentBucket.next = new BucketList();
            currentBucket.next.bucket.add(c);
            pages.put(c,currentBucket.next);
            currentBucket.next.count = currentBucket.count+1;
            currentBucket.next.previous = currentBucket;
            // update to new head
            head = currentBucket.next;
          }else if(currentBucket.next.count > currentBucket.count+1){
            // If there is an interleaved list
            BucketList newBucket = new BucketList();
            newBucket.count = currentBucket.count+1;
            newBucket.bucket.add(c);
            pages.put(c,newBucket);
            // Insert between the middle and the next
            currentBucket.next.previous = newBucket;
            newBucket.next = currentBucket.next;
            newBucket.previous = currentBucket;
            currentBucket.next = newBucket;
          }else if(currentBucket.next.count == currentBucket.count+1){
            // if the next node is correct
            currentBucket.next.bucket.add(c);
            pages.put(c,currentBucket.next);
          }

          // Now if the current node is empty, lets delete it
          // If the tail dont delete it
          if(currentBucket.bucket.isEmpty()){
            if(currentBucket != tail){
              currentBucket.previous.next = currentBucket.next;
              if(currentBucket.next != null){
                currentBucket.next.previous = currentBucket.previous;
              }
            }
          }
        }
    }


    BucketList current = head;
    while(current != null){
        System.out.print(current.count);
        System.out.println(":"+current.bucket);
        current = current.previous;
    }
    
  }

  private static class BucketList{
    int count;
    Set<Character> bucket = new HashSet<>();
    BucketList next;
    BucketList previous;
  }


  

}
