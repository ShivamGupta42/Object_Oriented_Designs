package designs.commentFilteringSystem;

import java.util.function.Predicate;

public class ReligiousFilter implements Predicate<Comment> {

	@Override
	public boolean test(Comment t) {
		if (t.getText().contains("hindu") || t.getText().contains("muslim")) {
			return false;
		}
		return true;
	}

}
