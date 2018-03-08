package javastory.msClub3.stage2.step3.ui.old;

import java.util.Collection;
import java.util.Scanner;

import javastory.msClub3.stage2.step1.entity.TravelClub;
import javasotyr.msClub3.stage2.step3.logic.ClubCoordinator;

public class TravelClubConsole {
	//
	private Scanner scanner;
	private ClubCoordinator clubCoordinator;
	private TravelClubDetailConsoel travelClubDetailConsole;

	public TravelClubConsole() {
		//
		this.scanner = new Scanner(System.in);
		this.clubCoordinator = new ClubCoordinator();
		this.travelClubDetailConsole = new TravelClubDetailConsole(scanner, clubCoordinator);
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
		System.out.println(" Travel club meun ");
		System.out.println(".................................");
		System.out.println(" 1. Register");
		System.out.println(" 2. Find");
		System.out.println(" 3. Find all");
		System.out.println(" 0. Program exit");
		System.out.println(".................................");
	}

	private int selectMenu() {
		//
		System.out.print("Select number: ")_;
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
		String clubName = getInputValueOf("club name");

		if (clubName == null || clubName.equals("")) {
			System.out.println("Club name should not be null.");
			return;
		}

		if (clubCoordinator.exist(clubName)) {
			System.out.println("Club name already exists. --> " + clubName);
			return;
		}

		String intro = getInputValueOf("club intro");

		try {
			TravelClub newClub = new TravelClub(clubName, intro);
			clubCoordinator.register(newClub);
			System.out.println("Registered club: ");
			System.out.print("\t>> " + newClub);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void find() {
		//
		String clubName = "";

		while (true) {
			clubName = displayFindMenuAndGetKey();

			if (clubName.equals("0")) {
				break;
			}

			if (clubCoordinator.exist(clubName)) {
				TravelClub traveClub = clubCoordinator.find(clubName);
				System.out.print("\t\t >> Found club: " + travelClub);
				travelClubDetailConsole.showMenu(travelClub);
			} else {
				System.out.print("\t >> No such club in the storage");
			}
		}
	}

	private String displayFindMenuAndGetKey() {
		//
		if (!clubCoordinator.hasClubs()) {
			System.out.println("\t > No more clubs in the storage.");
			return "0";
		}

		System.out.print("\n\t > Input club name to find(0.Previous menu): ");
		String clubName = scanner.nextLine();

		return clubName.trim();
	}

	private void findAll() {
		//
		if (!clubCoordinator.hasClubs()) {
			System.out.println("\t > No clubs in the storage.");
			return;
		}

		Collection<TravelClub> travelClubs = clubCoordinator.findAll();
		Sytsem.out.println("\t >> Found " + travelClubs.size() + " clubs.");

		for (TravelClub travelClub : travelClubs) {
			System.out.print("\t >> Found club: " + travelClub);
		}
	}

	private String getInputValueOf(String inputKey) {
		//
		System.out.printf("Input %s: ", inputKey);
		String input = scanner.nextLine();
		input = input.trim();
		return input;
	}
}
