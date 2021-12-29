package designs.facebook.services;

import designs.facebook.entities.Post;
import designs.facebook.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Data
public class NewsFeed {

    private static final List<Post> allPosts = new ArrayList<>();
    private static final Comparator<Post> votesComparator = (a, b) -> compareRelativeLikes(a, b);
    private static final Comparator<Post> commentsComparator = (a, b) -> compareComments(a, b);
    private static final Comparator<Post> recencyComparator = (a, b) -> compareRecency(a, b);


    public static Post addAPost(User user, String text) {
        Post newPost = Post.builder()
                .id(UUID.randomUUID())
                .postText(text)
                .createdAt(LocalDateTime.now())
                .postOwner(user)
                .upVotes(new HashSet<>())
                .downVotes(new HashSet<>())
                .comments(new ArrayList<>())
                .build();

        allPosts.add(newPost);
        return newPost;
    }

    public static Post addComment(User user, Post post, String text) {
        Post newComment = Post.builder()
                .id(UUID.randomUUID())
                .postText(text)
                .createdAt(LocalDateTime.now())
                .postOwner(user)
                .upVotes(new HashSet<>())
                .downVotes(new HashSet<>())
                .build();

        post.addComment(newComment);
        return newComment;
    }

    public static void upVote(User user, Post post) {
        post.getUpVotes().add(user.getUserId());
    }

    public static void downVote(User user, Post post) {
        post.getDownVotes().add(user.getUserId());
    }

    public static Comparator<Post> getFollowComparator(User user) {
        if (user.getFollowing() == null || user.getFollowing().size() == 0) {
            return (a, b) -> 0;
        }
        return (a, b) -> user.getFollowing().contains(a.getPostOwner()) ? user.getFollowing().contains(b.getPostOwner()) ? 0 : -1 : 1;
    }

    public static String showNewsFeed(User user) {
        Comparator<Post> comparator = getFollowComparator(user).thenComparing(votesComparator).thenComparing(commentsComparator).thenComparing(recencyComparator);
        List<Post> copiedList = new ArrayList<>(allPosts);
        copiedList.sort(comparator);

        StringBuilder sb = new StringBuilder();
        for (Post p : copiedList) {
            sb.append(processPost(p));
        }
        return sb.toString();
    }

    private static String processPost(Post p) {

        StringBuilder sb = new StringBuilder();
        if (p != null) {
            sb.append(p.toString());

            if (p.getComments() != null) {
                for (Post comment : p.getComments()) {
                    sb.append("\t Comment:"+processPost(comment));
                }
            }
        }
        return sb.toString();
    }


    private static int compareRelativeLikes(Post a, Post b) {
        int relativeLikesA = a.getUpVotes().size() - a.getDownVotes().size();
        int relativeLikesB = b.getUpVotes().size() - b.getDownVotes().size();

        if (relativeLikesA > relativeLikesB) {
            return -1;
        } else if (relativeLikesA < relativeLikesB) {
            return 1;
        } else {
            return 0;
        }
    }


    private static int compareComments(Post a, Post b) {
        int totalCommentsA = a.getComments().size();
        int totalCommentsB = b.getComments().size();

        if (totalCommentsA > totalCommentsB) {
            return -1;
        } else if (totalCommentsA < totalCommentsB) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int compareRecency(Post a, Post b) {

        if (a.getCreatedAt().isAfter(b.getCreatedAt())) {
            return -1;
        } else {
            return 1;
        }
    }

}
