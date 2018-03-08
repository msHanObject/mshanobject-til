package javastory.msClub2.stage2.step2.ui.old;

import java.util.Scanner;

public class ClubMemberDetailConsole {

	private Scanner scanner;

	public ClubMemberDetailConsole(Scanner scanner) {
		//
		this.scanner = scanner;
	}

	public void showMenu() {
		//
		int inputNumber = 0;

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
		System.out.println("\t\t\t..............................");
		System.out.println("\t\t\t Kim Joon Ki member menu ");
		System.out.println("\t\t\t..............................");
		System.out.println("\t\t\t 1. Modify");
		System.out.println("\t\t\t 2. Delete");
		System.out.println("\t\t\t 0. Back to previous Menu");
		System.out.println("\t\t\t..............................");
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
		System.out.print("You've select the modify member menu [Enter to go back].");
		scanner.nextLine();
	}

	private void delete() {
		//
		System.out.print("You've select the delete member menu [Enter to go back].");
		scanner.nextLine();
	}
}
