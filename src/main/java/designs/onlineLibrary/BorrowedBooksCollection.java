package designs.onlineLibrary;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class BorrowedBooksCollection {

	BlockingQueue<BorrowedBook> borrowedBooks;

	public BorrowedBooksCollection() {
		borrowedBooks = new DelayQueue<BorrowedBook>();
	}

	public synchronized void addBorrowedBook(BorrowedBook book) {
		borrowedBooks.add(book);
	}

	public synchronized BorrowedBook getExpiredBorrowedBook() {
		return borrowedBooks.poll();
	}

}
