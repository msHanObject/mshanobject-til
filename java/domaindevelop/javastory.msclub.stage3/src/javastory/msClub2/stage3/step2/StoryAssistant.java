package javastory.msClub2.stage3.step2;

import javastory.msClub2.stage3.step2.ui.menu.MainMenu;

public class StoryAssistant {
	//
	private void startStory() {
		//
		MainMenu mainMenu = new MainMenu();
		mainMenu.show();
	}

	public static void main(String[] args) {
		//
		StoryAssistant assistant = new StoryAssistant();
		assistant.startStory();
	}
}
