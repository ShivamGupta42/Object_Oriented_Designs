package designs.commentFilteringSystem;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCommentSystem {
	Post post;

	@BeforeEach
	void setupPost() {
		Person p = new Person("test");
		post = new Post(p, "Post1");

		Comment comment2 = new Comment(p, "excellent Features");
		comment2.setLikes(10);
		comment2.setDislikes(5);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Comment comment1 = new Comment(p, "Good phone");
		comment1.setLikes(5);
		comment1.setDislikes(2);

		post.addComment(comment1);
		post.addComment(comment2);
	}

	@Test
	void testReligousFilter() {
		int initSize = post.totalComments();
		Comment comment1 = new Comment(new Person("extremist"), "muslim onplus");
		post.addComment(comment1);
		assertEquals(initSize, post.totalComments());
	}

	@Test
	void testAbusiveFilter() {
		int initSize = post.totalComments();
		Comment comment1 = new Comment(new Person("abusive"), "fuck this onplus");
		post.addComment(comment1);
		assertEquals(initSize, post.totalComments());
	}

	@Test
	void TestTopCommentFilter() {
		List<Comment> comments = post.filterComments(new TopRatedComment());
		assertEquals(10, comments.get(0).getLikes());
	}

	@Test
	void mostRecentComment() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Comment comment2 = new Comment(new Person("Recent"), "MostRecentComment");
		comment2.setLikes(10);
		comment2.setDislikes(5);
		post.addComment(comment2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Comment> comments = post.filterComments(new MostRecentComment());
		assertEquals("MostRecentComment", comments.get(0).getText());
	}

}
