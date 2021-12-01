package designs.commentFilteringSystem;

import java.util.function.Predicate;

public class AbusiveLanguage implements Predicate<Comment> {

	@Override
	public boolean test(Comment t) {
		if (t.getText().contains("fuck")) {
			return false;
		}
		return true;
	}

}
