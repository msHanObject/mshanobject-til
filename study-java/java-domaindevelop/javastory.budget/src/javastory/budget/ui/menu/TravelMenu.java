package javastory.budget.ui.menu;

import java.util.Scanner;

import javastory.budget.ui.console.TravelConsole;

public class TravelMenu {
	//
	private final int MINIMUM_MENU_NUMBER = 0;
	private final int MAXIMUM_MENU_NUMBER= 4;
	
	private TravelConsole travelConsole;
	private Scanner scanner;
	
	public TravelMenu() {
		//
		scanner = new Scanner(System.in);
		
		travelConsole = new TravelConsole();
	}
	
	public void show() {
		//
		int inputNumber = 0;
		while (true) {
		displayMenu();
		inputNumber = selectMenu();
		
			switch (inputNumber) {
			case 1:
				travelConsole.create();
				break;
			case 2:
				travelConsole.find();
				break;
			case 3:
				travelConsole.modify();
				break;
			case 4:
				travelConsole.delete();
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
		System.out.println("");
		System.out.println(".............................");
		System.out.println(" Travel menu");
		System.out.println(".............................");
		System.out.println(" 1. Create travel");
		System.out.println(" 2. Find travel");
		System.out.println(" 3. Modify travel");
		System.out.println(" 4. Delete travel");
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
