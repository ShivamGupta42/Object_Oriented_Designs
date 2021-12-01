package designs.cachemanagment.Cache;

import designs.cachemanagment.EvictionStrategy.EvictionStrategy;

public interface GoCache <K,V> {
    V get(K obj);
    void put(K key, V value);
    void setEvictionStrategy(EvictionStrategy evictionStrategy);
}
