package designs.cachemanagment.Coordinator;

import designs.cachemanagment.Cache.DefaultGoCache;
import designs.cachemanagment.Cache.GoCache;

public class CacheFactory {

    public static GoCache getCache(String cacheName, int maxCapacity) {

        switch (cacheName) {
            default:
                return new DefaultGoCache(maxCapacity);
        }
    }

}
