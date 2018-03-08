package javastory.msClub.stage1.step2;

import java.util.Scanner;

public class ClubConsole {
	//
	private Scanner scanner;
	
	public ClubConsole() {
		//
		this.scanner = new Scanner(System.in);
	}
	
	public void showMenu() {
		//
		int inputNumber = 0;
		
		while(true) {
			displayMainMenu();
			inputNumber = selectMainMenu();
			
			switch (inputNumber) {
			//
			case 1:
				register();
				break;
			case 2:
				find();
				break;
			case 3: 
				findAll();
				break;
			case 0:
				exitProgram();
				return;
				
			default:
					System.out.println("Choose again!");
			}
		}
	}
	
	private void displayMainMenu() {
		//
		System.out.println("");
		System.out.println("................");
		System.out.println("Travel club Menu");
		System.out.println(".................");
		
	}
	
	private int selectMainMenu() {
		//
		System.out.println("Select number: ");
		int menuNumber = scanner.nextInt();
		
		return menuNumber;
	}
	
	private void exitProgram() {
		//
		System.out.println("Program exit. Bye...");
		scanner.close();
		System.exit(0);
	}
	
	private void register() {
		//
		System.out.print("You've select the register menu [Enter to go back].");
		scanner.nextLine();
	}
	
	private void find() {
		//
		System.out.print("You've select the find menu [Enter to go back.]");
		scanner.nextLine();
	}
	
	private void findAll() {
		//
		System.out.println("You've select the find all menu [Enter to go back.]");
		scanner.nextLine();
	}
}
