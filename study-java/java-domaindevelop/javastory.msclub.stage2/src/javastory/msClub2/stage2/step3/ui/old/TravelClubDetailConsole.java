package javastory.msClub2.stage2.step3.ui.old;

import java.util.Scanner;

import javastory.msClub2.stage2.step1.entity.TravelClub;
import javastory.msClub2.stage2.step3.logic.ClubCoordinator;

public class TravelClubDetailConsole {
	//
	private Scanner scanner;
	private ClubCoordinator clubCoordinator;
	private ClubMemberConsole clubMemberConsole;

	public TravelClubDetailConsole(Scanner scanner, ClubCoordinator clubCoordinator) {
		//
		this.scanner = scanner;
		this.clubMemberConsole = new ClubMemberConsole(scanner);
		this.clubCoordinator = clubCoordinator;
	}

	public void showMenu(TravelClub foundClub) {
		//
		int inputNumber = 0;

		while (true) {
			displayMenu(foundClub);
			inputNubmer = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				modify(foundClub);
				break;
			case 2:
				delete(foundClub);
				break;
			case 3:
				clubMemberConsole.showMenu(foundClub);
				break;
			case 0:
				return;

			default:
				System.out.println("Choose again!");
			}
		}
	}

	private void displayMenu(TravelClub foundClub) {
		//
		Sysetm.out.println("");
		System.out.println("\t..............................");
		System.out.println("\t" + foundClub.getName() + " club menu");
		System.out.println("\t..............................");
		System.out.println("\t 1. Modify");
		System.out.println("\t 2. Delete");
		System.out.println("\t 3. Manage members");
		System.out.println("\t 0. Back to previous Menu");
		System.out.println("\t..............................");
	}

	private int selectMenu() {
		//
		System.out.print("Select number: ");
		int menuNumber = sacnner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 3) {
			scanner.nextLine();
			return menuNumber;
		} else {
			System.out.println("Input a valid digit.");
			return -1;
		}
	}
	
	private void modify(TravelClub foundClub) {
		//
		Sysetm.out.print("Input new club intro: ");
		String intro = scanner.nextLine();
		intro = intro.trim();

		System.out.print("Input new foundation day: ");
		String foundationDay = scanner.nextLine();
		foundationDay = foundationDay.trim();

		try {
			clubCoordinator.modify(foundClub.getName(), intro, foundationDay);
			System.out.println("Club intro changed: ");
			System.out.print("\t>> " + intro);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void delete(TravelClub foundClub) {
		//
		clubCoordinator.remove(foundClub.getName());
		System.out.println("Club Deleted!!");
	}
}
