package designs.onlineLibrary;

import java.util.List;

// When you want to make multiple classes methods static
public class BookSystemMediator {

	final MembershipDetails memberDetails;
	final BorrowedBooksCollection borrowedBooks;
	final BooksInInventory booksInInventory;

	private BookSystemMediator() {
		memberDetails = new MembershipDetails();
		borrowedBooks = new BorrowedBooksCollection();
		booksInInventory = new BooksInInventory();
	}

	static class SingleBookInstance {
		static BookSystemMediator mediator = new BookSystemMediator();
	}

	public static BookSystemMediator getInstance() {
		return SingleBookInstance.mediator;
	}

	public boolean validMembership(int memberId) {
		return memberDetails.checkValidMember(memberId);
	}

	public Book getAvailableBook(int bookId) {
		return booksInInventory.searchByBookId(bookId);
	}

	public List<Book> getAvailableBooks(String bookName) {
		return booksInInventory.searchByBookName(bookName);
	}

	public void addBorrowedBook(BorrowedBook book) {
		borrowedBooks.addBorrowedBook(book);
	}

	public void borrowBook(int memberId, String bookName) {
		if (validMembership(memberId)) {
			List<Book> books = getAvailableBooks(bookName);

			if (books == null) {
				// throw exception
			}

			BorrowedBook borrowedBook = new BorrowedBook(memberId, books.get(0), 10000);
			addBorrowedBook(borrowedBook);
		} else {
			// throw Exception
		}
	}

	public void borrowBook(int memberId, int bookId) {
		if (validMembership(memberId)) {
			Book book = getAvailableBook(bookId);
			if (book != null) {
				BorrowedBook borrowedBook = new BorrowedBook(memberId, book, 10000);
				addBorrowedBook(borrowedBook);
			}
		}
	}

	public void returnBook() {
		BorrowedBook book = borrowedBooks.getExpiredBorrowedBook();
	}

}
