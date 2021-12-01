package designs.onlineLibrary;

import java.util.List;

public class Member {
	int memberId;
	List<Book> borrowedBooks;

	public Member(int memberId) {
		this.memberId = memberId;
	}

	public List<Book> getAllAvailableBooks() {
		return borrowedBooks;
	}

	public String readABook(String bookName) {
		return borrowedBooks.stream().filter(e -> e.getName().equals(bookName)).findFirst().get().text;

	}

}
