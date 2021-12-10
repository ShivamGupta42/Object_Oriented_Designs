package designs.facebook.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
public class Post {
    private UUID id;
    private User postOwner;
    private String postText;
    private Set<UUID> upVotes;
    private Set<UUID> downVotes;
    private LocalDateTime createdAt;
    private List<Post> comments;

    public void addComment(Post comment) {
       comments.add(comment);
    }

    @Override
    public String toString() {
        return "id " + id + "\n"
                + "(" + upVotes.size() + " upVotes , " + downVotes.size() + " downVotes )\n"
                + "User " + postOwner.getUserName() + "\n"
                + postText + "\n"
                + createdAt + "\n\n";
    }
}
