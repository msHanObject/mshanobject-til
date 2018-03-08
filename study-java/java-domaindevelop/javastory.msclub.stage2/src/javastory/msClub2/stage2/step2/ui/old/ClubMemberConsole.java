package javastory.msClub2.stage2.step2.ui.old;

import java.util.Scanner;

public class ClubMemberConsole {
	//
	private Scanner scanner;
	private ClubMemberDetailConsole memberDetailConsole;

	public ClubMemberConsole(Scanner scanner) {
		//
		this.scanner = scanner;
		this.memberDetailConsole = new ClubMemberDetailConsole(scanner);
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
				addMember();
				break;
			case 2:
				findMember();
				break;
			case 3:
				findAll();
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
		System.out.println("\t\t.............................");
		System.out.println("\t\t Nextree club members menu ");
		System.out.println("\t\t.............................");
		System.out.println("\t\t 1. Add member");
		System.out.println("\t\t 2. Find member");
		System.out.println("\t\t 3. Find all");
		System.out.println("\t\t 0. Back to previous Menu");
		System.out.println(".................................");
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

	private void addMember() {
		//
		System.out.print("You've select the add member menu [Enter to go back].");
		scanner.nextLine();
	}

	private void findMember() {
		//
		System.out.print("\t\t >> Found member: Name:Kim Joon Ki, Email:jkkim@nextree.co.kr, Nickname:joon, Phone number:01012341234, BirthDay:960111, Role:Member");
		memberDetailConsole.showMenu();
	}

	private void findAll() {
		//
		System.out.print("You've select the find all members menu [Enter to go back].");
		scanner.nextLine();
	}
}
