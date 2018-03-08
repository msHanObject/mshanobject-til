package javastory.msClub2.stage2.step2.ui.menu;

import java.util.Scanner;

import javastory.msClub2.stage2.step2.ui.console.MemberWindow;
import javastory.msClub2.stage2.util.Narrator;
import javastory.msClub2.stage2.util.TalkingAt;

public class MemberMenu {
	//
	private MemberWindow memberWindow;

	private Scanner scanner;
	private Narrator narrator;

	public MemberMenu() {
		//
		this.memberWindow = new MemberWindow();
		this.scanner = new Scanner(System.in);
		this.narrator = new Narrator(this,TalkingAt.Left);
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
				memberWindow.findClub();
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
		narrator.sayln("..................................");
		narrator.sayln(" Members menu");
		narrator.sayln("..................................");
		narrator.sayln(" 1. Find a club");
		narrator.sayln(" 2. Add member");
		narrator.sayln(" 3. Find a member");
		narrator.sayln(" 4. Modify a member");
		narrator.sayln(" 5. Remove a member");
		narrator.sayln("..................................");
		narrator.sayln(" 0. Previous");
		narrator.sayln("..................................");
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
