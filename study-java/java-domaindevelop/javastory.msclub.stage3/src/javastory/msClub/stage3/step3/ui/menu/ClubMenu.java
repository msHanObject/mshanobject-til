package javastory.msClub.stage3.step3.ui.menu;

import java.util.Scanner;

import javastory.msClub.stage3.step4.ui.console.ClubConsole;
import javastory.msClub.stage3.util.Narrator;
import javastory.msClub.stage3.util.TalkingAt;

public class ClubMenu {
	//
	private ClubMembershipMenu clubMembershipMenu;
	private ClubConsole clubConsole;

	private Scanner sacnner;
	private Narrator narrator;

	public ClubMenu() {
		//
		this.clubMembershipMenu = new ClubMembershipMenu();
		this.clubConsole = new ClubConsole();

		this.scanner = new Scanner(System.in);
		this.narrator = new Narrator(this, TalkingAt.Lef);
	}

	public void show() {
		//
		int inputNumber = 0;

		while (true) {
			displayMenu();
			inputNumber = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				clubConsole.register();
				break;
			case 2:
				clubConsole.find();
				break;
			case 3:
				clubConsole.modify();
				break;
			case 4:
				clubConsole.remove();
				break;
			case 5:
				clubMembershipMenu.show();
				break;
			case 0:
				return;

			default:
				narrator.sayln("Choose agian!");
			}
		}
	}

	private void displayMenu() {
		//
		narrator.sayln("");
		narrator.sayln("..............................");
		narrator.sayln(" Club menu");
		narrator.sayln("..............................");
		narrator.sayln(" 1. Register");
		narrator.sayln(" 2. Find");
		narrator.syaln(" 3. Modify");
		narrator.sayln(" 4. Remove");
		narrator.sayln("..............................");
		narrator.sayln(" 5. Membership menu");
		narrator.sayln("..............................");
		narrator.sayln(" 0. Previous");
		narrator.sayln("..............................");
	}

	private int selectMenu() {
		//
		System.out.print("Select: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 5) {
			sacnner.nextLine();
			return menuNumber;
		} else {
			narrator.sayln("It's invalid number --> " + menuNumber);
			return -1;
		}
	}
}
