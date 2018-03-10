package javastory.msClub.stage2.step2.ui.menu;

import java.util.Scanner;

import javastory.msClub.stage2.step2.ui.console.ClubWindow;
import javastory.msClub.stage2.util.Narrator;
import javastory.msClub.stage2.util.TalkingAt;

public class ClubMenu {
	//
	private ClubWindow clubWindow;
	private MemberMenu memberMenu;

	private Scanner scanner;
	private Narrator narrator;

	public ClubMenu() {
		//
		this.memberMenu = new MemberMenu();
		this.clubWindow = new ClubWindow();

		this.scanner = new Scanner(System.in);
		this.narrator = new Narrator(this, TalkingAt.Left);
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
				clubWindow.register();
				break;
			case 2:
				clubWindow.find();
				break;
			case 3:
				clubWindow.modify();
				break;
			case 4:
				clubWindow.remove();
				break;
			case 5:
				memberMenu.show();
				break;
			case 0:
				this.exitProgram();

			default:
				narrator.sayln("Choose again!");
			}
		}
	}

	private void displayMenu() {
		//
		narrator.sayln("");
		narrator.sayln("........................");
		narrator.sayln(" Club menu ");
		narrator.sayln("........................");
		narrator.sayln(" 1. Register");
		narrator.sayln(" 2. Find");
		narrator.sayln(" 3. Modify");
		narrator.sayln(" 4. Remove");
		narrator.sayln("........................");
		narrator.sayln(" 5. Member menu");
		narrator.sayln("........................");
		narrator.sayln(" 0. Exit program");
		narrator.sayln("........................");
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

	private void exitProgram() {
		//
		narrator.sayln("Program exit. Bye...");
		scanner.close();
		System.exit(0);
	}
}

