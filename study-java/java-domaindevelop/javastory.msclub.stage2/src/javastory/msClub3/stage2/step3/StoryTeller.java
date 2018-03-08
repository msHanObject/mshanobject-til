package javastory.msClub3.stage2.step3;

import javastory.msClub3.stage2.step3.ui.menu.ClubMenu;

public class StoryTeller {
	//
	public static void main(String[] args) {
		//
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.start();
	}

	public void start() {
		//
		ClubMenu clubMenu = new ClubMenu();
		clubMenu.show();
	}
}
