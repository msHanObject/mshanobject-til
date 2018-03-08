package ui;

import entity.Book;
import entity.BookCategoryList;

public class BookDataFormat {

	public void dataFormatting(Book book){//output formatted book information
		String rent;
		if(book.isRentPossible()) {
			rent = "Rent Possible";
		}else {
			rent = "Rent Impossible";
		}		
		System.out.println(rent + "               " + book.getBookNumber() + "            " + book.getCategoryName() + "                "+ book.getBookName());
	}

	public void windowFormat(){//table format
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Rent>    <BookNumber>    <BookCategory>        <BookName>");
	}
	
	public void showCategoryList() {//show category list
		for(BookCategoryList category : BookCategoryList.values() ) {
			int index = category.ordinal()+1;
			System.out.print(index+"\t");
			System.out.println(category);
		}
	}
	
	public void showMostPopularBook(Book book){//show formatted most popular book information
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[ Most popular book ]");
		System.out.println("Book number" + "\t|\t" + book.getBookNumber());
		System.out.println("Book category" + "\t|\t" + book.getCategoryName());
		System.out.println("Book name" + "\t|\t" + book.getBookName());
		System.out.println("Book author" + "\t|\t" + book.getAuthor());
		if(book.isRentPossible()){
			System.out.println("* Rental available.");
		}else{
			System.out.println("* This book is currently being rented.");
		}
	}
}
