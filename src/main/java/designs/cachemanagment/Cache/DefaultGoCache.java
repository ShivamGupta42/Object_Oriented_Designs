package designs.cachemanagment.Cache;

import designs.cachemanagment.EvictionStrategy.EvictionStrategy;
import designs.cachemanagment.EvictionStrategy.LRUEvictionStrategy;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DefaultGoCache <K,V> implements GoCache<K,V> {

    private final Deque<K> deque;
    private final Map<K, V> map;
    private EvictionStrategy<K,V> evictionStrategy;
    private final int MAX_CAPACITY;

    public DefaultGoCache(int maxCapacity) {
        MAX_CAPACITY=maxCapacity;
        this.deque = new LinkedList<>();
        this.map = new HashMap<>();
        evictionStrategy = new LRUEvictionStrategy<K,V>();
    }

    @Override
    public V get(K key) {
        return evictionStrategy.get(MAX_CAPACITY, key, deque,map);
    }

    @Override
    public void put(K key, V value) {
        evictionStrategy.put(MAX_CAPACITY, key,value, deque,map);
    }

    @Override
    public void setEvictionStrategy(EvictionStrategy evictionStrategy) {
        this.evictionStrategy = evictionStrategy;
    }
}
