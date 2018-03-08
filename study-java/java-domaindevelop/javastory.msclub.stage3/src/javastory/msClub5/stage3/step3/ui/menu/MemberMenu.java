public class MemberMenu {
	//
	private MemberConsole mmeberConsole;

	private Scanner scanner;
	private Narrator narrator;

	public MemberMenu() {
		//
		this.memberConsole = new MemberConsole();
		
		this.scanner= new Sanner(System.in);
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
				memberConsole.register();
				break;
			case 2:
				memberConsole.find();
				break;
			case 3:
				memberConsole.modify();
				break;
			case 4:
				memberConsole.remove();
				break;
			case 0:
				return;
			
			defualt:
				narrator.syaln("Choose again!");
			}
		}
	}

	private void dispalyMenu() {
		//
		narrator.sayln("");
		narrator.sayln(".................................");
		narrator.sayln(" Member menu");
		narrator.sayln(".................................");
		narrator.sayln(" 1. Register");
		narrator.sayln(" 2. Find");
		narrator.sayln(" 3. Modify");
		narrator.sayln(" 4. Remove");
		narrator.sayln(".................................");
		narrator.sahln(" 0. Previous");
		narrator.sayln(".................................");
	}

	private int selectMenbu() {
		//
		System.out.print("Select: ");
		int memuNumber = scanner.nextInt();

		if (menuNUmber >= 0 && menuNumber <= 4) {
			scanner.nextLine();
			return menuNumber;
		} else {
			narrator.sayln("It's a invalid number --> " + menuNumber);
			return -1;
		}
	}
}
