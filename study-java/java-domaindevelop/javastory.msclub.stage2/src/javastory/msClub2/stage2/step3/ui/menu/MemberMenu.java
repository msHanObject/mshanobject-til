package javastory.msClub2.stage2.step3.ui.menu;

import java.util.Scanner;

import javastory.msClub2.stage2.step1.entity.TravelClub;
import javastory.msClub2.stage2.step3.logic.ClubCoordinator;
import javastory.msClub2.stage2.step3.ui.window.MemberWindow;
import javastory.msClub2.stage2.util.Narrator;
import javastory.msClub2.stage2.util.TalkingAt;

public class MemberMenu {
	//
	private MemberWindow memberConsole;

	private Scanner scanner;
	private Narrator narrator;

	public MemberMenu(ClubCoordinator clubCoordinator) {
		//
		this.memberConsole = new MemberWindow(clubCoordinator);
		this.scanner = new Scanner(System.in);
		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	public void show(TravelClub currentClub) {
		//
		int inputNumber = 0;
		if (currentClub == null) {
			//
			narrator.sayln("No club selected yet !! ");
			return;
		}

		this.memberConsole.setCurrentClub(currentClub);

		while(true) {
			displayMenu();
			inputNumber = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				memberConsole.findAnotherClub();
				break;
			case 2:
				memberConsole.add();
				break;
			case 3:
				memberConsole.find();
				break;
			case 4:
				memberConsole.modify();
				break;
			case 5:
				memberConsole.remove();
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
		narrator.sayln("................................");
		if (memberConsole.hasCurrentClub()) {
			narrator.sayln(" Members menu for[" + memberConsole.getCurrentClub().getName() + "]");
		} else {
			narrator.sayln(" Members menu [No club selected]");
		}
		narrator.sayln("................................");
		narrator.sayln(" 1. Find another club");
		narrator.sayln(" 2. Add member");
		narrator.sayln(" 3. Find a member");
		narrator.sayln(" 4. Modify a member");
		narrator.sayln(" 5. Remove a member");
		narrator.sayln("................................");
		narrator.sayln(" 0. Previous");
		narrator.sayln("................................");
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
