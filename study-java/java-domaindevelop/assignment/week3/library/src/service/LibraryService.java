package service;

import java.util.Scanner;

public interface LibraryService {

	public void showMenu();
	public void selectMenu();
	public void excuteMenu();
	default public String keybordIn() {
		Scanner keybordIn = new Scanner(System.in);
		String inputData = keybordIn.nextLine();
		return inputData;
	}
}
