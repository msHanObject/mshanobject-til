package javastory.msClub3.stage2.step2.ui.old;

import java.util.Scanner;

public class TravelClubDetailConsole {
	//
	private Scanner scanner;
	private ClubMemberConsole clubMemberConsole;

	public TravelClubDetailConsole(Scanner scanner) {
		//
		this.scanner = scanner;
		this.clubMemberConsole = new ClubMemberConsole(scanner);
	}

	public void showMenu() {
		//
		int inpuNumber = 0;

		while (true) {
			displayMenu();
			inputNumber = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				modify();
				break;
			case 2:
				delete();
				break;
			case 3:
				clubMemberConsole.showMenu();
				break;
			case 0:
				return;

			default:
				System.out.println("Choose again!");
			}
		}
	}

	private void displayMenu() {
		//
		System.out.println("");
		System.out.println("\t...............................");
		System.out.println("\t Nextree club menu");
		System.out.println("\t...............................");
		System.out.println("\t 1. Modify");
		System.out.println("\t 2. Delete");
		System.out.println("\t 3. Manage member");
		System.out.println("\t 0. Back to previous Menu");
		System.out.println("\t...............................");
	}

	private int selectMenu() {
		//
		System.out.print("Select number: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 3) {
			scanner.nextLine();
			return menuNumber;
		} else {
			System.out.println("Input a valid digit.");
			return -1;
		}
	}

	private void modify() {
		//
		System.out.print("You've select the club modify menu [Enter to go back].");
		scanner.nextLine();
	}

	private void delete() {
		//
		System.out.print("You've select the delete menu [Enter to go back].");
		scanner.nextLine();
	}
}
