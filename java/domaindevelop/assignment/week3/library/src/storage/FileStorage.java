package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Book;

public class FileStorage extends BookStorage implements FileStorageInterface{

	private String fileLocation;
	private File file;	
	
	public FileStorage() {
		super();
		// TODO Auto-generated constructor stub 
		fileLocation = "./src/libraryBook.dat";
		file = new File(fileLocation);
	}

	@Override
	public void loadFile() {
		// TODO Auto-generated method stub
		if(file.exists()==false) {
			try {
			
			file.createNewFile();
			
			} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			}
		}
		try {
			
			br = new BufferedReader(new FileReader(fileLocation));
			this.makeBookList();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveFile(ArrayList<Book> bookList) {
		// TODO Auto-generated method stub
		// BufferedWriter, FileWriter, bookList
	}

}
