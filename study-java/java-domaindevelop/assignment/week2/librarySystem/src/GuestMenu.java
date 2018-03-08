import java.util.Scanner;

class GuestMenu {

	public void showMenu(){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Menu>");
		System.out.println("1. Search by BookName");
		System.out.println("2. Search by Category");
		System.out.println("3. Rent Book");
		System.out.println("4. Return Book");
		System.out.println("5. Top-rated Book");
		System.out.println("e. Exit");
	}

	public char selectMenu(){

		Scanner scanner = new Scanner(System.in);
		System.out.print("<Select Menu> : ");
		String menuIndex = scanner.nextLine();
		char menu =menuIndex.charAt(0);
		return menu;
	}
}

