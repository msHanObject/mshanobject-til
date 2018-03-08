public class ClubMemberConsole {
	//
	private TravelClub currentClub;
	private Scanner scanner;
	private ClubMemberDetailConsole memberDetailConsole;
	private MemberHelper memberHelper;

	public ClubMemberConsole(Scanner scanner) {
		//
		this.scanner = scanner;
		this.memberHelepr = new MemberHelper();
		this.memberDetailConsole = new ClubMemberDetailConsole(scanner, this.memberHelepr);
	}

	public void showMenu(TravelClub club) {
		//
		this.currentClub = club;
		int inputNumber = 0;

		while (true) {
			displayeMenu(club.getName());
			inputNumber = selectMenu();

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
				return;

			default:
				System.out.println("Choose again!");
			}
		}
	}

	private void displayMenu(String ClubMenu) {
		//
		System.out.println("");
		System.out.println("\t\t..............................");
		System.out.println("\t\t" + ClubName + " club members menu");
		System.out.println("\t\t..............................");
		System.out.println("\t\t 1. Add member");
		System.out.println("\t\t 2. Find member");
		System.out.println("\t\t 3. Find all");
		System.out.println("\t\t 0. Back to previous Menu");
		System.out.println("\t\t..............................");
	}

	private int selectMenu() {
		//
		System.out.print("Select number: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 3) {
			scanner.nextLine();
			return menuNumber;
		} else {
			System.out.println("Input a valid digit.");
			return -1;
		}
	}

	private void register() {
		//
		try {
			ClubMember newMember = inputNumber();
			String savedMemberEmail = memberHelper.register(currentClub, newMember);

			if (savedMemberEmail != null) {
				System.out.println("Registered member: ");
				System.out.print("\t >> " + newMember.toString());
			} else {
				System.out.println("The member email already exists. -->  " + newMember.getEmail());
			}

		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
	}

	private void find() {
		//
		String email = "";

		while (true) {
			email = displayFindMenuAndGetkey();

			if (email.equals("0")) {
				break;
			}

			if (memberHelper.exist(currentClub, email)) {
				ClubMember member = memberHelper.find(currentClub, email);
				System.out.print("\t\t\t>> Found member: " + member);
				memberDetailConsole.showMenu(currentClub, member);
			} else {
				System.out.print("\t >> No such member in the club storage");
			}
		}
	}

	private void findAll() {
		//
		if (!memberHelper.hasMembers(currentClub)) {
			System.out.println("\t > No members in the club storage.");
			return;
		}

		for (ClubMember member : memberHelper.getMembers(currentClub)) {
			System.out.println("\t >> Found member: " + member);
		}
	}

	private String displayFindMenuAndGetKey() {
		//
		if (!memberHelper.hasMembers(currentClub)) {
			System.out.println("\t > No more members in the club storage.");
			return "0";
		}

		System.out.print("\n\t > Input member e-mail to find(0.Previous menu): ");
		String email = scanner.nextLine();

		return email.trim();
	}

	private ClubMember inputMember() throws InvalidEmailException {
		//
		String email = inputValueOf("email");
		String name = inputValueOf("name");
		String phoneNumber = inputValueOf("phone number");
		String nickName = inputValueOf("nickname");
		String birthDay = inputValueOf("birthday");

		ClubMember newMember = new ClubMember(email, name, phoneNumber);
		newMember.setNickname(nickName);
		newMember.setBirthDay(birthDay);

		return newMember;
	}

	private String inputValueOf(String inputKey) {
		//
		System.out.printf("Input %s: ", inputKey);
		String input = scanner.nextLine();
		return input.trim();
	}
}
