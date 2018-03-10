package javastory.budget.ui.menu;

import java.util.Scanner;

import javastory.budget.service.dto.YearlyDueDto;
import javastory.budget.ui.console.YearlyDueConsole;

public class YearlyDueMenu {
	//
	private Scanner scanner;
	private static final int MINIMUM_INDEX = 0;
	private static final int MAXIMUM_INDEX = 6;

	private YearlyDueConsole yearlyDueConsole;
	private MonthlyDueMenu monthlyDueMenu;
	private YearlyDueDto currentYear;
	
	public YearlyDueMenu() {
		//
		scanner = new Scanner(System.in);
		yearlyDueConsole = new YearlyDueConsole();
		//
	}
	public void show() {
		// 
		int inputNumber = 0;
		while(true) {
			//
			displayMenu();
			inputNumber = selectNumber();
			
			switch (inputNumber) {
			case 1:
				yearlyDueConsole.selectMember();
				break;
			case 2:
				yearlyDueConsole.add();
				break;
			case 3:
				yearlyDueConsole.find();
				currentYear = yearlyDueConsole.requestCurrentYear();
				break;
			case 4:
				yearlyDueConsole.modify();
				break;
			case 5:
				yearlyDueConsole.delete();
				break;
			case 6:
				if (currentYear == null) {
					System.out.println("Not yet selected year.");
				}else {
					monthlyDueMenu = new MonthlyDueMenu(currentYear);
					monthlyDueMenu.show();
				}
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
		if (yearlyDueConsole.hasMember()) {
			System.out.print("["+yearlyDueConsole.requestCurrentMember().getEmail()+"]");
		}
		if (currentYear != null) {
			System.out.println(" (" + currentYear.getYear() + ")");
		}
		System.out.println(" YearlyDue menu");
		System.out.println("............................");
		System.out.println(++index + ". Select Member");
		System.out.println(++index + ". Add yearlydue");
		System.out.println(++index + ". Find yearlydue");
		System.out.println(++index + ". Modify yearlydue");
		System.out.println(++index + ". Delete yearlydue");
		System.out.println("............................");
		System.out.println(++index + ". MonthlyDue menu");
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