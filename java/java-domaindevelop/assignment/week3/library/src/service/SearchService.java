package service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Book;
import entity.BookCategoryList;
import storage.BookStorage;
import ui.BookDataFormat;

public class SearchService {

	private BookDataFormat bdf;
	private Scanner scanner;
	private BookStorage bs;
	private ArrayList<Book> bookList;
	
	public SearchService() {
		super();
		// TODO Auto-generated constructor stub
		bdf = new BookDataFormat();
		scanner = new Scanner(System.in);
		bs = new BookStorage();
		bookList = new ArrayList<Book>();
		bookList = bs.getBookList();
	}

	public void bookCategory() {
		//request book category for search
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by bookCategory>");
		System.out.print("<Category Index> : ");
		String index = scanner.nextLine();
		
		//input format
		bdf.windowFormat();
		
		try {//bring and show category list
		
		for(BookCategoryList category : BookCategoryList.values()) {
			
		int categoryIndex = category.ordinal() +1;
		String categoryName = category.name();
		
		//Show and find books matching category
		if(categoryIndex == Integer.parseInt(index)) {

			for(Book book : bookList){
				
			if(book.getCategoryName().equalsIgnoreCase(categoryName)) {
			bdf.dataFormatting(book);
			
			}
		
			}
		
		}

		}
		
		}catch(Exception e) {
			System.out.println("Check again Category Index");
		}
		
	}
		
	public void bookName(){
		//request book name for search
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by bookName>");
		System.out.print("<bookName> : ");
		String bookName = scanner.nextLine();
		
		//input format
		bdf.windowFormat();

		//find book contained the word that user input
		for(Book book : bookList){
			if(book.getBookName().contains(bookName)){
				bdf.dataFormatting(book);//show book found
			}
		}
		
	}

	public Book numberOfBorrowed(){
		//input format
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by number of borrowed>");
		int maxValue=0;
	
		//compare book's number of borrowed
		for(Book book : bookList){
			if(book.getNumberOfBorrowed() > maxValue){
				maxValue = book.getNumberOfBorrowed();//set max value of book list
			}	
		}
		Book popularBook = new Book();
		for(Book book : bookList){//find the book that matches max value
			if(book.getNumberOfBorrowed() == maxValue){
				popularBook = book;
			}
		}
		return popularBook;
	}
}
