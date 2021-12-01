package designs.cachemanagment.EvictionStrategy;

import java.util.Deque;
import java.util.Map;

public class LRUEvictionStrategy <K,V> implements EvictionStrategy<K,V> {


    @Override
    public V get(int maxCapacity, K key, Deque<K> deque, Map<K, V> map) {
        if (!map.containsKey(key)) {
            return null;
        } else {
            deque.remove(key);
            deque.addFirst(key);
            return map.get(key);
        }
    }


    @Override
    public void put(int maxCapacity, K key, V value, Deque<K> deque, Map<K, V> map) {
        if (deque.size() == maxCapacity) {
            if (!map.containsKey(key)) {
                Object lastObject = deque.removeLast();
                map.remove(lastObject);
            }
        } else {
            deque.remove(key);
        }
        deque.addFirst(key);
        map.put(key, value);
    }
}
