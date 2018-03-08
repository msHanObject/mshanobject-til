/*
 * COPYRIGHT (c) NEXTREE Consulting 2016
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2016. 7. 12.
 */
package javastory.club.stage3.step4.ui.menu;

import java.util.Scanner;

import javastory.budget.ui.menu.SubMainMenu;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

public class MainMenu {
	//
	private ClubMenu clubMenu;
	private MemberMenu memberMenu;
	private BoardMenu boardMenu;
	private SubMainMenu subMain;
	
	private Scanner scanner;
	private Narrator narrator; 

	public MainMenu() {
		//
		this.clubMenu = new ClubMenu();
		this.memberMenu = new MemberMenu();
		this.boardMenu = new BoardMenu();
		this.subMain = new SubMainMenu();
		
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
				clubMenu.show();
				break;
			case 2:
				memberMenu.show(); 
				break;
			case 3:
				boardMenu.show(); 
				break;
			case 4:
				subMain.show();
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
		narrator.sayln("..............................");
		narrator.sayln(" Main menu ");
		narrator.sayln("..............................");
		narrator.sayln(" 1. Club menu");
		narrator.sayln(" 2. Member menu");
		narrator.sayln(" 3. Board menu");
		narrator.sayln("..............................");
		narrator.sayln(" 4. SubMain menu");
		narrator.sayln("..............................");
		narrator.sayln(" 0. Exit program");
		narrator.sayln("..............................");
	}

	private int selectMenu() {
		//
		System.out.print("Select: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 4) {
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