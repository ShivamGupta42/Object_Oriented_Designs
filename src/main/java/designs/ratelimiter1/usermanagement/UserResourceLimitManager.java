package designs.ratelimiter1.usermanagement;

import designs.ratelimiter1.ratelimiter.RateLimiter;
import designs.ratelimiter1.ratelimiter.TokenBucketRateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserResourceLimitManager {
    private static ConcurrentHashMap<String, RateLimiter> userResourceMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,Integer> resourcesTimeMap = new ConcurrentHashMap<>();

    static {

        resourcesTimeMap.put("res1",2);
        resourcesTimeMap.put("res2",3);
        resourcesTimeMap.put("res3",4);
    }

    public static void initResourcesMap(String userId) {
        for(Map.Entry<String,Integer> entry: resourcesTimeMap.entrySet()){
            userResourceMap.put(userId+entry.getKey(),new TokenBucketRateLimiter(entry.getValue()));
        }
    }

    public static boolean allowRequest(String userId, String resource, int curTimeStamp) {
        RateLimiter rateLimiter = userResourceMap.get(userId + resource);
        return rateLimiter.accept(curTimeStamp);
    }
}
