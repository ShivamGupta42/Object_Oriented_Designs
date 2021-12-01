package designs.onlineLibrary;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BooksInInventory {
	// Keeping just one copy, since we will return clones of this book on runtime
	HashMap<Integer, Book> availableBooks;

	public BooksInInventory() {
		availableBooks = new HashMap<Integer, Book>();
	}

	public Book searchByBookId(int bookId) {

		Book book = null;
		try {
			book = (Book) availableBooks.get(bookId).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return book;
	}

	public List<Book> searchByBookName(String bookName) {

		List<Book> book = null;

		book = availableBooks.values().parallelStream().filter(e -> e.getName().equals(bookName))
				.collect(Collectors.toList());

		return book;
	}

	public void addBook(int bookId, Book book) {
		availableBooks.put(bookId, book);
	}
}
