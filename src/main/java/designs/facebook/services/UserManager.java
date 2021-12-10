package designs.facebook.services;

import entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class UserManager {

    private final Map<UUID, User> userMap;
    private static final String WELCOME_MSG="Welcome to the Social Network !!\n" +
            "There are no posts yet ..\n";

    public UserManager() {
        userMap = new ConcurrentHashMap<>();
    }

    public User signUpUser(String userName) {
        UUID newUserId = UUID.randomUUID();
        User newUser = User.builder().userId(newUserId)
                .userName(userName)
                .createdAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .build();
        userMap.put(newUserId, newUser);
        return newUser;
    }

    public String loginUser(UUID userId) {
        if (userMap.containsKey(userId)) {
            String newsFeedResult = NewsFeed.showNewsFeed(userMap.get(userId));
            return newsFeedResult==null||newsFeedResult.trim().equals("")? WELCOME_MSG: newsFeedResult;
        }

        //Or Throw Exception
        return "";
    }


}
