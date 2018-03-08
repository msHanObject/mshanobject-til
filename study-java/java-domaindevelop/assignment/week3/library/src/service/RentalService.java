package service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Book;
import storage.BookStorage;
import ui.BookDataFormat;

public class RentalService {

	private BookDataFormat bdf;
	private Scanner scanner;
	
	public RentalService() {
		super();
		// TODO Auto-generated constructor stub
		bdf = new BookDataFormat();
		scanner = new Scanner(System.in);
	}

	public void rentBook(){
		//request book number for return
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by bookNumber>");
		System.out.print("<bookNumber> : ");
		String bookNumber = scanner.nextLine();
		
		//input format & bring book list
		bdf.windowFormat();		BookStorage bs = new BookStorage();		ArrayList<Book> bookList = new ArrayList<Book>();
		bookList = bs.getBookList();
		
		//find book that match the requested book number
		for(Book book : bookList){
			if(book.getBookNumber().equalsIgnoreCase(bookNumber)){
				
			bdf.dataFormatting(book);//show the book found 
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("[ Book rental ]");
			try{//according to book's rent state change the rent state
				
			if(book.isRentPossible()){
				
			book.setRentPossible(false);
			int count =book.getNumberOfBorrowed();
			book.setNumberOfBorrowed(count++);
			
			System.out.println(book.getBookNumber() + " Rental is complete.");
			System.out.println("--------------------------------------------------------------------------------");
			
			}else{
				
			System.out.println(book.getBookNumber() + " is already rented.");
			System.out.println("--------------------------------------------------------------------------------");
		
			}
			
			}catch(Exception e){

			System.out.println("Something is wrong! please check again!");
			System.out.println("--------------------------------------------------------------------------------");
			
			}
	
			}else{
			
			System.out.println(bookNumber+" undetected");
			
			}
		}
	}

	public void returnBook(){
		//request book number for return
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by bookNumber>");
		System.out.print("<bookNumber> : ");
		String bookNumber = scanner.nextLine();
		
		//input format & bring book list
		bdf.windowFormat();		BookStorage bs = new BookStorage(); 	ArrayList<Book> bookList = new ArrayList<Book>();
		bookList = bs.getBookList();
		
		//find book that match the requested book number
		for(Book book : bookList){
		
		if(book.getBookNumber().equalsIgnoreCase(bookNumber)){				
		bdf.dataFormatting(book);//show the book found 
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[ Return books ]");
		try{//according to book's rent state change the rent state
			if(!book.isRentPossible()){

			book.setRentPossible(true);
			System.out.println(book.getBookNumber() + " Return is complete.");
			System.out.println("--------------------------------------------------------------------------------");
	
			}else{
			
			System.out.println(book.getBookNumber() + " is already returned.");
			System.out.println("--------------------------------------------------------------------------------");
		
			}
		}catch(Exception e){
			System.out.println("Something is wrong! please check again!");
			System.out.println("--------------------------------------------------------------------------------");
		}
		
		}else{
				
			System.out.println(bookNumber+" undetected");
				
			}
		}
	}
}
