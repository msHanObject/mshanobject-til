package javastory.msClub3.stage2.step2.ui.old;

import java.util.Scanner;

public class TravelClubConsole {
	//
	private Scanner scanner;
	private TravelClubDetailConsole travelClubDetailConsole;

	public TravelClubConsole() {
		//
		this.scanner = new Scanner(System.in);
		this.travelClubDetailConsole = new TravelClubDetailConsole(scanner);
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
				register();
				break;
			case 2:
				find();
				break;
			case 3:
				findAll();
				break;
			case 0:
				exitProgram();
				return;

			default:
				System.out.println("Choose again!");
			}
		}
	}

	private void displayMenu() {
		//
		System.out.println("");
		System.out.println(".................................");
		System.out.println(" Travel club menu");
		System.out.println(".................................");
		System.out.println(" 1. Register");
		System.out.println(" 2. Find");
		System.out.println(" 3. Find all");
		System.out.println(" 0. Program exit");
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

	private void exitProgram() {
		//
		System.out.println("Program exit. Bye...");
		scanner.close();
		System.exit(0);
	}

	private void register() {
		//
		System.out.print("You've select the club register menu [Enter to go back].");
		scanner.nextLine();
	}

	private void find() {
		//
		System.out.print("\t >> Found club: Travel Club Name: Nextree, Intro: Nextree TravelClub, founded date:2016.06.23");
		travelClubDetailConsole.showMenu();
	}

	private void findAll() {
		//
		System.out.print("You've select the find all menu [Enter to go back].");
		scanner.nextLine();
	}
}
