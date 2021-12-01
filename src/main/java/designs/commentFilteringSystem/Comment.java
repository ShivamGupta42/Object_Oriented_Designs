package designs.commentFilteringSystem;

import java.time.LocalDateTime;

public class Comment extends Post {

	public Comment(Person p, String text) {
		super(p, text);
		postCreation = LocalDateTime.now();
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
