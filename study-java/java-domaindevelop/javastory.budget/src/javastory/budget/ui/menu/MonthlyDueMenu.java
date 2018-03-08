package javastory.budget.ui.menu;

import java.util.Scanner;

import javastory.budget.service.dto.YearlyDueDto;
import javastory.budget.ui.console.MonthlyDueConsole;

public class MonthlyDueMenu {
	//
	private Scanner scanner;
	private static final int MINIMUM_INDEX = 0;
	private static final int MAXIMUM_INDEX = 4;
	
	private MonthlyDueConsole monthlyDueConsole;
	
	private YearlyDueDto currentYear;
	
	public MonthlyDueMenu() {
		//
		scanner = new Scanner(System.in);
	}
	public MonthlyDueMenu(YearlyDueDto currentYear) {
		// 
		this();
		this.currentYear = currentYear;		
		monthlyDueConsole = new MonthlyDueConsole(currentYear);
	}
	
	public void show() {
		// 
		int inputNumber = 0;
		while (true) {
			//
			displayMenu();
			inputNumber = selectNumber();
			
			switch (inputNumber) {
			//
			case 1:
				monthlyDueConsole.register();
				break;
			case 2:
				monthlyDueConsole.find();
				break;
			case 3:
				monthlyDueConsole.modify();
				break;
			case 4:
				monthlyDueConsole.delete();
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
		int index = 0;
		System.out.println("");
		System.out.println("............................");
		if (currentYear != null) {
			System.out.print("[" + currentYear.getYear() + "]");
		}
		System.out.println(" MonthlyDue menu");
		System.out.println("............................");
		System.out.println(++index + ". Register monthlyDue");
		System.out.println(++index + ". Find monthlyDue");
		System.out.println(++index + ". Modify monthlyDue");
		System.out.println(++index + ". Delete monthlyDue");
		System.out.println("............................");
		System.out.println("0. Previous");
		System.out.println("............................");
	}
	
	public int selectNumber() {
		//
		int menuNumber = 0;
		System.out.print("Select: ");
		menuNumber = scanner.nextInt();
		
		if (menuNumber >= MINIMUM_INDEX && menuNumber <= MAXIMUM_INDEX) {
			scanner.nextLine();
			return menuNumber;
		} else {
			System.out.println("It's a invalid number --> " + menuNumber);
			return -1;
		}
	}
}
