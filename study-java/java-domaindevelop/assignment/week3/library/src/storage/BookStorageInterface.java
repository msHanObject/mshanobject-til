package storage;

import java.util.ArrayList;
import java.util.List;

import entity.Book;

public interface BookStorageInterface {

	public void makeBookList();
	public ArrayList<Book> editBookList();
	public ArrayList<Book> deleteBookList();
	public ArrayList<Book> getBookList();
}
