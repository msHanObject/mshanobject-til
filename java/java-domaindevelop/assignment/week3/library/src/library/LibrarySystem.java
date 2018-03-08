package library;

import ui.UserInterface;

public class LibrarySystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInterface ui = new UserInterface();
		while(true) {
			ui.showAlternative();
			ui.setUser();
			ui.excuteMode();	
		}
	}
}
