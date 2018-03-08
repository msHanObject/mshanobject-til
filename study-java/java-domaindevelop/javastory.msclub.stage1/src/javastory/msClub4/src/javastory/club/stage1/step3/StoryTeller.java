package javastory.club.stage1.step3;

import javastory.club.stage1.step2.ClubConsole;

public class StoryTeller {
	//
	public static void main(String[] args) {
		//
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.startStory();
	}
	
	public void startStory() {
		//
		ClubConsole clubConsole = new ClubConsole();
		clubConsole.showMenu();
	}
}
