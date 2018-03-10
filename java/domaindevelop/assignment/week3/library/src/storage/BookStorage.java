package storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import entity.Book;

public class BookStorage implements BookStorageInterface{

	private ArrayList<Book> bookList;
	protected BufferedReader br;
	
	
	public BookStorage() {
		super();
		// TODO Auto-generated constructor stub
		bookList = new ArrayList<Book>();
	}

	@Override
	public void makeBookList() {
		// TODO Auto-generated method stub
		try {//set book list & book info
			String line="";
			while((line = br.readLine()) != null) {

			String bookInfo[] = line.split(";");
			Book book = new Book();
			book.setBookNumber(bookInfo[0]);
			book.setBookName(bookInfo[1]);
			book.setAuthor(bookInfo[2]);
			book.setCategoryName(bookInfo[3]);
			book.setRentPossible(Boolean.parseBoolean(bookInfo[4]));
			book.setNumberOfBorrowed(Integer.parseInt(bookInfo[5]));
			bookList.add(book);

			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Book> editBookList() {
		// TODO Auto-generated method stub
		//edit
		return (ArrayList<Book>) bookList;
	}

	@Override
	public ArrayList<Book> deleteBookList() {
		// TODO Auto-generated method stub
		//delete
		return (ArrayList<Book>) bookList;
	}

	@Override
	public ArrayList<Book> getBookList() {
		// TODO Auto-generated method stub
		return bookList;
	}
	

}
