package javastory.msClub2.stage3.step3;

import javastory.msClub2.stage3.step3.ui.menu.MainMenu;

public class StoryAssistant {
	//
	private void startStory() {
		//
		MainMenu mainMenu = new MainMenu();
		mainMenu.show();
	}

	public static void main(String[] args) {
		//
		StoryAssistant assistant = new StoryAsistant();
		assistant.startStory();
	}
}
