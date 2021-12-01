package designs.cachemanagment.EvictionStrategy;

import java.util.Deque;
import java.util.Map;

public interface EvictionStrategy <K,V>{
    V get(int maxCapacity, K key, Deque<K> deque, Map<K, V> map);

    void put(int maxCapacity, K key, V value, Deque<K> deque, Map<K, V> map);
}
