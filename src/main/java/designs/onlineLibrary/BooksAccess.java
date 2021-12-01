package designs.onlineLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BooksAccess {
	HashMap<Integer, List<Integer>> bookAccessOfMember;

	public BooksAccess() {
		bookAccessOfMember = new HashMap<Integer, List<Integer>>();
	}

	void addAccess(int memberId, int bookId) {
		List<Integer> Accesses = bookAccessOfMember.get(memberId);
		if (Accesses == null) {
			Accesses = new ArrayList<Integer>();
		}

		Accesses.add(bookId);
		bookAccessOfMember.put(memberId, Accesses);
	}

	void removeAccess(int memberId, int bookId) {
		List<Integer> Accesses = bookAccessOfMember.get(memberId);
		if (Accesses == null) {
			return;
		}

		Accesses.removeIf(e->e.equals(bookId));
		bookAccessOfMember.put(memberId, Accesses);
	}

}
