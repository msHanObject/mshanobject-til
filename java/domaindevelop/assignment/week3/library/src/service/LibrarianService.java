package service;

import storage.BookStorage;
import storage.BookStorageInterface;
import storage.FileStorage;
import storage.FileStorageInterface;

public class LibrarianService implements LibraryService {

	private String selectedMenu;
	private FileStorageInterface fsi;
	private BookStorageInterface bsi;
	
	
	public LibrarianService() {
		super();
		// TODO Auto-generated constructor stub
		fsi = new FileStorage();
		bsi = new BookStorage();
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("\n\t<Librarian Menu>");
		System.out.println("1. Registrate Book");
		System.out.println("2. Edit Book");
		System.out.println("3. Delete Book");
		System.out.println("0. Exit");
	}

	@Override
	public void selectMenu() {
		// TODO Auto-generated method stub
		System.out.print("\nPlease enter menu number.\t");
		System.out.println("ex) <Librarian> : 1");
		System.out.print("<Librarian> : ");
		selectedMenu = keybordIn();		
	}

	@Override
	public void excuteMenu() {
	// TODO Auto-generated method stub
		
	while(true) {
		this.showMenu();
		this.selectMenu();
		switch(Integer.parseInt(selectedMenu)) {
		case 1:
			// FileStorage.loadFile
			// BookStorage.addBookList
			fsi.loadFile();
			break;
		case 2:
			//BookStorage.editBookList
			//FileStorage.updateFile
			fsi.saveFile(bsi.editBookList());
			break;
		case 3:
			//BookStorage.removeBookList
			//FileStorage.saveFile
			fsi.saveFile(bsi.deleteBookList());
			break;
		case 0:
			//recall UserInterface
			break;
		}
		if(Integer.parseInt(selectedMenu)==0)
			break;			
		}
	}
}
