package javastory.budget.ui.menu;

import java.util.Scanner;

import javastory.budget.ui.console.CashBookConsole;

public class CashBookMenu {
	//
	private final int MINIMUM_MENU_NUMBER = 0;
	private final int MAXIMUM_MENU_NUMBER= 5;
	
	private CashBookConsole cashBookConsole;
	private TranMenu tranMenu;
	
	private Scanner scanner;
	
	public CashBookMenu() {
		//
		scanner = new Scanner(System.in);
		
		cashBookConsole = new CashBookConsole();
		tranMenu = new TranMenu();
	}
	
	public void show() {
		//
		int inputNumber = 0;
		while (true) {
		displayMenu();
		inputNumber = selectMenu();
		
			switch (inputNumber) {
			case 1:
				cashBookConsole.register();
				break;
			case 2:
				cashBookConsole.find();
				break;
			case 3:
				cashBookConsole.update();
				break;
			case 4:
				cashBookConsole.remove();
				break;
			case 5:
				tranMenu.show();
				break;
			case 0:
				return;
			default:
				System.out.println("Choose again!");
			}
		}
	}
	
	private void displayMenu() {
		//
		int index = 0;
		
		System.out.println("");
		System.out.println(".............................");
		System.out.println(" CashBook menu");
		System.out.println(".............................");
		System.out.println(++index + ". Register cashbook");
		System.out.println(++index + ". Find cashbook");
		System.out.println(++index + ". Update cashbook");
		System.out.println(++index + ". Remove cashbook");
		System.out.println(".............................");
		System.out.println(++index + ". Transaction menu");
		System.out.println(".............................");
		System.out.println(" 0. Previous");
	}
	
	private int selectMenu() {
		//
		System.out.print("Select: ");
		int menuNumber = scanner.nextInt();
		
		if (menuNumber >= MINIMUM_MENU_NUMBER && menuNumber <= MAXIMUM_MENU_NUMBER) {
			scanner.nextLine();
			return menuNumber;
		} else {
			System.out.println("It's a invalid number --> " + menuNumber);
			return -1;
		}
	}
}