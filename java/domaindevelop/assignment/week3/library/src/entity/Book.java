package entity;

public class Book {

	private String bookNumber;
	private String bookName;
	private String author;
	private String categoryName;
	private boolean rentPossible;
	private int numberOfBorrowed;

	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
		//Do I have to initialize the field?
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isRentPossible() {
		return rentPossible;
	}

	public void setRentPossible(boolean rentPossible) {
		this.rentPossible = rentPossible;
	}

	public int getNumberOfBorrowed() {
		return numberOfBorrowed;
	}

	public void setNumberOfBorrowed(int numberOfBorrowed) {
		this.numberOfBorrowed = numberOfBorrowed;
	}	
}
