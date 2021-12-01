package designs.onlineLibrary;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class BorrowedBook implements Delayed {

	Book book;
	Long returnTimeInMs = 0L;
	int memberId;

	public BorrowedBook(int memberId, Book book, long returnTime) {
		this.book = book;
		this.returnTimeInMs = returnTime;
		this.memberId = memberId;
	}

	@Override
	public int compareTo(Delayed o) {
		return (int) (this.getReturnTimeInMs() - o.getDelay(TimeUnit.SECONDS));
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return this.getReturnTimeInMs() - System.currentTimeMillis();
	}

	public Long getReturnTimeInMs() {
		return returnTimeInMs;
	}

	public void setReturnTimeInMs(Long returnTimeInMs) {
		this.returnTimeInMs = returnTimeInMs;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

}
