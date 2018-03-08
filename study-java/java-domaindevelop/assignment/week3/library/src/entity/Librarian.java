package entity;

import service.LibrarianService;
import ui.LibrarianInterface;

public class Librarian extends LibrarianService implements LibrarianInterface  {

	@Override
	public void exe() {
		// TODO Auto-generated method stub
		System.out.println("\nRunning Librarian Mode...\n");
		this.excuteMenu();
	}	
}
