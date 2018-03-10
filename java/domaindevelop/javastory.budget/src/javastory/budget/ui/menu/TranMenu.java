package javastory.budget.ui.menu;

import java.util.Scanner;

import javastory.budget.ui.console.TranConsole;

public class TranMenu {
	//
	private static final int MINIMUM_INDEX = 0;
	private static final int MAXMUM_INDEX = 4;
	
	private TranConsole tranConsole;
	private Scanner scanner;
	
	public TranMenu() {
		//
		tranConsole = new TranConsole();
		scanner = new Scanner(System.in);
	}
	
	public void show() {
		//
		int inputNumber = 0;
		while (true) {
			//
			displayMenu();
			inputNumber = selectMenu();
			
			switch (inputNumber) {
			//
			case 1:
				tranConsole.register();
				break;
			case 2:
				tranConsole.find();
				break;
			case 3:
				tranConsole.modify();
				break;
			case 4:
				tranConsole.delete();
				break;
			case 0:
				return;
			default:
				System.out.println("Choose again!");
			}
		}
	}
	
	public void displayMenu() {
		//
		int index =0;
		
		System.out.println("");
		System.out.println(".................................");
		System.out.println("Transaction menu");
		System.out.println(".................................");
		System.out.println(++index + ". Register transaction.");
		System.out.println(++index + ". Find transaction.");
		System.out.println(++index + ". Modify transaction.");
		System.out.println(++index + ". Delete transaction.");
		System.out.println(".................................");
		System.out.println("0. Previous");
	}
	
	public int selectMenu() {
		//
		int menuNumber = 0;
		System.out.print("Select: ");
		menuNumber = scanner.nextInt();
		
		if (menuNumber >= MINIMUM_INDEX && menuNumber <= MAXMUM_INDEX) {
			scanner.nextLine();
			return menuNumber;
		} else {
			System.out.println("It is a nvalid number --> " + menuNumber);
			return -1;
		}
	}
}
