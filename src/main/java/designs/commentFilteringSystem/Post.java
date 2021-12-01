package designs.commentFilteringSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Post {

	List<Comment> comments;
	Predicate<Comment> filterChain;
	LocalDateTime postCreation;
	int likes;
	int dislikes;
	Person owner;
	String text;

	public Post(Person p, String text) {
		owner = p;
		this.text = text;
		postCreation = LocalDateTime.now();
		comments = new ArrayList<Comment>();
		makeFilterChain();

	}

	private void makeFilterChain() {
		filterChain = new AbusiveLanguage().and(new ReligiousFilter());

	}

	public void addComment(Comment c) {
		if (filterChain.test(c)) {
			comments.add(c);
		}
	}

	public void deleteComment(Comment c) {
		comments.remove(c);
	}

	public List<Comment> filterComments(UserFilter f) {
		return comments.parallelStream().sorted(f).collect(Collectors.toList());
	}

	public int totalComments() {
		return comments.size();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Predicate<Comment> getFilterChain() {
		return filterChain;
	}

	public void setFilterChain(Predicate<Comment> filterChain) {
		this.filterChain = filterChain;
	}

	public LocalDateTime getPostCreation() {
		return postCreation;
	}

	public void setPostCreation(LocalDateTime postCreation) {
		this.postCreation = postCreation;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
