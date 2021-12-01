package designs.onlineLibrary;

import java.util.Objects;

public class Book implements Cloneable {
	String name;
	int BookId;
	String genre;
	String author;
	final String text;

	public Book(String name, int bookId, String genre, String author, String text) {
		this.name = name;
		BookId = bookId;
		this.genre = genre;
		this.author = author;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String readBook() {
		return text;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Book other = (Book) obj;

		return Objects.deepEquals(this.getBookId(), other.getBookId());
	}

	@Override
	public int hashCode() {
		return this.getBookId();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
