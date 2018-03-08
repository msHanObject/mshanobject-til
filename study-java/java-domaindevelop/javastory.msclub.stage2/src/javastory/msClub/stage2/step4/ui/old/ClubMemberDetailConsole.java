package javastory.msClub.stage2.step4.ui.old;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javastory.msClub.stage2.step1.entity.ClubMember;
import javastory.msClub.stage2.step1.entity.TravelClub;
import javastory.msClub.stage2.step4.logic.MemberHelper;

public class ClubMemberDetailConsole {
	//
	private TravelClub currentClub;
	private Scanner scanner;
	private MemberHelper memberHelper;

	public ClubMemberDetailConsole(Scanner scanner, MemberHelper memberHelper) {
		//
		this.scanner = scanner;
		this.memberHelepr = memberHelper;
	}

	public void showMenu(TravelClub currentClub, ClubMember foundMember) {
		//
		this.currentClub = currentClub;
		int inputNumber = 0;

		while(true) {
			displayMenu(foundMember);
			inputNumber = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				modify(foundMember);
				break;
			case 2:
				delete(foundMember);
				return;
			case 0:
				return;

			default:
				System.out.println("Choose again!");
			}
		}
	}

	private void displayMenu(ClubMember foundMember) {
		//
		System.out.println("");
		System.out.println("\t\t\t................................");
		System.out.println("\t\t\t" + foundMember.getName() + " member menu ");
		System.out.println("\t\t\t................................");
		System.out.println("\t\t\t 1. Modify");
		System.out.println("\t\t\t 2. Delete");
		System.out.println("\t\t\t 0. Back to previous Menu");
		System.out.println("\t\t\t.................................");
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

	private void modify(ClubMember foundMember) {
		//
		String name = getInputValueOf("name");
		String nickname = getInputValueOf("nickname");
		String phoneNumber = getInputValueOf("phone number");
		String birthDay = getInputValueOf("birthday");

		Map<String,String> newValueMap = new HashMap<>();

		newValueMap.put("name", name);
		newValueMap.put("nickname", nickname);
		newValueMap.put("phoneNumber", phoneNumber);
		newValueMap.put("birthDay", birthDay);

		memberHelper.modify(foundMember, newValueMap);

		System.out.println("Member information changed: ");
		System.out.print("\t>> " + foundMember);
	}

	private void delete(ClubMember foundMember) {
		//
		memberHelper.getMembers(currentClub.getName()).remove(foundMember);
		System.out.println("Member Deleted!!");
	}

	private String getInputValueOf(String inputKey) {
		//
		System.out.printf("Input %s: ", inputKey);
		String input = scanner.nextLine();
		return input.trim();
	}
}
