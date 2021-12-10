package designs.facebook.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class User {
    private UUID userId;
    private String userName;
    private Set<User> followers;
    private Set<User> following;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.userId, user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public void addFollower(User follower) {
        followers.add(follower);
        follower.addFollowing(this);
    }

    public void addFollowing(User user) {
        following.add(user);
    }
}
