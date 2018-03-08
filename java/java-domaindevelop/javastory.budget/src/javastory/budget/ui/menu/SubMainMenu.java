package javastory.budget.ui.menu;

import java.util.Scanner;

public class SubMainMenu {
	//

	private final int MINIMUM_MENU_NUMBER = 0;
	private final int MAXIMUM_MENU_NUMBER= 3;
	private TravelMenu travelMenu;
	private CashBookMenu budgetMenu;
	private YearlyDueMenu yearlyDueMenu;
	
	private Scanner scanner;
	
	public SubMainMenu() {
		scanner = new Scanner(System.in);
		
		travelMenu = new TravelMenu();
		budgetMenu = new CashBookMenu();
		yearlyDueMenu = new YearlyDueMenu();
	}
	
	public void show() {
		//
		int inputNumber = 0;
		while (true) {
		displayMenu();
		inputNumber = selectMenu();
		
			switch (inputNumber) {
			case 1:
				travelMenu.show();
				break;
			case 2:
				budgetMenu.show();
				break;
			case 3:
				yearlyDueMenu.show();
				break;
			case 0:
				return;
				
			default:
				return;
			}
		}
	}
	
	private void displayMenu() {
		//
		System.out.println("");
		System.out.println("..........................");
		System.out.println("SubMain menu");
		System.out.println("..........................");
		System.out.println(" 1. Travel menu");
		System.out.println(" 2. Budget menu");
		System.out.println(" 3. AccountYearlyDue menu");
		System.out.println("..........................");
		System.out.println(" 0. Previous...");
		System.out.println("..........................");
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