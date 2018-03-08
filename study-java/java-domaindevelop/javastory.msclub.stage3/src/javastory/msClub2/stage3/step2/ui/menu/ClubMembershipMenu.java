package javastory.msClub2.stage3.step2.ui.menu;

import java.util.Sacnner;

import javastory.msClub2.stage3.step2.ui.console.ClubMembershipConsole;
import javastory.msClub2.stage3.util.Narrator;
import javastory.msClub2.stage3.util.TalkingAt;

public class ClubMembershipMenu {
	//
	private ClubMebmershipConsole clubMembershipConsole;

	private Scanner scanner;
	private Narrator narrtor;

	public ClubMembershipMenu() {
		//
		this.ClubMembershipConsole = new ClubMembershipConsole();
		this.scanner = new Scanner(System.in);
		this.narrtor = new Narrator(this, TalkingAt.Left);
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
				clubMembershipConsole.findClub();
				break;
			case 2:
				clubMembershipConsole.add();
				break;
			case 3:
				clubMembershipConsole.find();
				break;
			case 4:
				clubMembershipConsole.modify();
				break;
			case 5:
				clubMembershipConsole.remove();
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
		narrator.sayln(".............................");
		narrator.sayln(" Membership menu");
		narrator.sayln(".............................");
		narrator.sayln(" 1. Find a club");
		narrator.sayln(" 2. Add a membership");
		narrator.sayln(" 3. Find a membership");
		narrator.sayln(" 4. Modify a membership");
		narrator.sayln(" 5. Remove a mebership");
		narrator.sayln(".............................");
		narrator.sayln(" 0. Previous");
		narrator.sayln(".............................");
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
