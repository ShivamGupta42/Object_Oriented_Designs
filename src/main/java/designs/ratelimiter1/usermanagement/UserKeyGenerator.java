package designs.ratelimiter1.usermanagement;

public class UserKeyGenerator {

    public static void  generateUserId(String name){
        initializeBasicResourceFilters(name);
    }


    private static void initializeBasicResourceFilters(String id) {
        UserResourceLimitManager.initResourcesMap(id);
    }
}
