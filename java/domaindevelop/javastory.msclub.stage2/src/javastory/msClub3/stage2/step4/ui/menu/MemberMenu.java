package javastory.msClub3.stage2.step4.ui.menu;

import java.util.Scanner;

import javastory.msClub3.stage2.step1.entity.TravelClub;
import javastory.msClub3.stage2.step4.logic.ClubCoordinator;
import javastory.msClub3.stage2.step4.ui.window.MemberWindow;
import javastory.msClub3.stage2.util.Narrator;
import javastory.msClub3.stage2.util.TalkingAt;

public class MemberMenu {
	//
	private MemberWindow memberWindow;

	private Scanner scanner;
	private Narrator narrator;

	public MemberMenu(ClubCoordinator clubCoordinator) {
		//
		this.memberWindow = new MemberWindow(clubCoordinator);
		this.scanner = new Scanner(System.in);
		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	public void show(TravelClub currentClub) {
		//
		int inputNumber = 0;
		if (currentClub == null) {
			//
			narrator.sayln("No club selected yet !!");
			return;
		}

		this.memberWindow.setCurrentClub(currentClub);

		while (true) {
			displayMenu();
			inputNumber = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				memberWindow.findAnotherClub();
				break;
			case 2:
				memberWindow.add();
				break;
			case 3:
				memberWindow.find();
				break;
			case 4:
				memberWindow.modify();
				break;
			case 5:
				memberWindow.remove();
				break;
			case 0:
				return;

			default:
				narrator.sayln("Choose again!");
			}
		}
	}
	
	private void displayMenu() {
		//
		narrator.sayln("");
		narrator.sayln("...................................");
		if (memberWindow.hasCurrentClub()) {
			narrator.sayln(" Members menu for[" + memberWindow.requestCurrentClubName() + "]");
		} else {
			narrator.sayln(" Members menu [No club selected]");
		}
		narrator.sayln("...............................");
		narrator.sayln(" 1. Find another club");
		narrator.sayln(" 2. Add member");
		narrator.sayln(" 3. Find a member");
		narrator.sayln(" 4. Modify a member");
		narrator.sayln(" 5. Remove a member");
		narrator.sayln("...............................");
		narrator.sayln(" 0. Previous");
		narrator.sayln("...............................");
	}

	private int selectMenu() {
		//
		System.out.print("Select: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 5) {
			scanner.nextLine();
			return menuNumber;
		} else {
			narrator.sayln("It's a invalid number --> " + menuNumber);
			return -1;
		}
	}
}
