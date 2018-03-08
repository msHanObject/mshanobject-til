package ui;

import java.util.Scanner;

import entity.Guest;
import entity.Librarian;

public class UserInterface {

	private String user;
	
	public void showAlternative() {
		System.out.println("================================================================");
		System.out.println("			Namoo Sori			");
		System.out.println("			 Librarry			");
		System.out.println("================================================================");
		System.out.println("\nPlease choose either <Librarian Mode> or <Guest Mode>");
	}
	public void setUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter 'librarian' or 'guest'.");
		System.out.print("Set User : ");
		this.user = scanner.nextLine();
	}
	
	public void  excuteMode() {
		if(this.user.equalsIgnoreCase("librarian")) {
			LibrarianInterface librarianInterface = new Librarian();
			librarianInterface.exe();
		}else {
			GuestInterface libGuest = new Guest();
			libGuest.exe();
		}
	}
}