package designs.lru;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    Deque<Integer> q;
    Map<Integer,Integer> map;
    private final int capacity;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        q=new LinkedList<>();
        map=new HashMap<>();
    }

    public int get(int key) {

        if(!map.containsKey(key)){
            return -1;
        }else{
            q.remove(key);
            q.addFirst(key);
            return map.get(key);
        }
    }

    public void put(int key, int value) {
        if(!q.contains(key)){
            if(q.size()==capacity){
                int dataToRemove = q.pollLast();
                map.remove(dataToRemove);
            }
        }else{
            q.remove(key);
        }
        q.addFirst(key);
        map.put(key,value);
    }

    public static void main(String[] args) {

//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        System.out.println(lRUCache.get(1));    // return 1
//        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//        System.out.println(lRUCache.get(2));    // returns -1 (not found)
//        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//        System.out.println(lRUCache.get(1));    // return -1 (not found)
//        System.out.println(lRUCache.get(3));    // return 3


        LRUCache lRUCache2 = new LRUCache(2);
        System.out.println(lRUCache2.get(2));
        lRUCache2.put(2, 6);
        System.out.println(lRUCache2.get(1));

        lRUCache2.put(1, 5);
        lRUCache2.put(1, 2);
        System.out.println(lRUCache2.get(1));
        System.out.println(lRUCache2.get(2));


    }




}
