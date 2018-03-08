package javastory.msClub.stage2.step4;

import javastory.msClub.stage2.step4.ui.menu.ClubMenu;

public class StoryAssistant {
	//
	private void startStory() {
		//
		ClubMenu clubMenu = new ClubMenu();
		clubMenu.show();
	}

	public static void main(String[] args) {
		//
		StoryAssistant assistant = new StoryAssistant();
		assistant.startStory();
	}
}
