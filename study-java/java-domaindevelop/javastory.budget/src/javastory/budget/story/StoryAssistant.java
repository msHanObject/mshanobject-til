package javastory.budget.story;

import javastory.club.stage3.step4.ui.menu.MainMenu;

public class StoryAssistant {
	//
	public void startStory() {
		MainMenu mainMenu = new MainMenu();
		mainMenu.show();
	}
	
	public static void main(String[] args) {
		StoryAssistant storyAssistant = new StoryAssistant();
		storyAssistant.startStory();
	}
}
