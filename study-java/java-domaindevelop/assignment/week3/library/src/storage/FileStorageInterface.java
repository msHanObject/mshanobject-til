package storage;

import java.util.ArrayList;

import entity.Book;

public interface FileStorageInterface {

	public void loadFile();
	public void saveFile(ArrayList<Book> bookList);
}
