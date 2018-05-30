package model;

public class Textbook {
	private String title;
	private Name author;
	private String publisher;
	private String isbn;
	private double price;

	public Textbook(String title, Name author, String publisher, String isbn) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
	}

	public Textbook(Textbook textbook) {
		this(textbook.title, textbook.author, textbook.publisher, textbook.isbn);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Name getAuthor() {
		return author;
	}

	public void setAuthor(Name author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String ibsn) {
		this.isbn = ibsn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Textbook Title: " + title + "\n" + author + "\n" + publisher + "\n" + isbn
				+ "\n" + price + "\n";
	}
	
}
