package designs.commentFilteringSystem;

public class MostRecentComment implements UserFilter {

	@Override
	public int compare(Comment o1, Comment o2) {
		return o2.postCreation.getNano() - o1.postCreation.getNano();
	}
}
