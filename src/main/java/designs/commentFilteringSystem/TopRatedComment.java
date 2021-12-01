package designs.commentFilteringSystem;

public class TopRatedComment implements UserFilter {

	@Override
	public int compare(Comment o1, Comment o2) {
		return o2.getLikes() - o1.getLikes();
	}

}
