package javastory.msClub3.stage2.step2;

import javastory.msClub3.stage2.step2.ui.menu.ClubMenu;

public class StoryTeller {
	//
	public static void main(String[] args) {
		//
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.start();
	}

	private void start() {
		//
		ClubMenu clubMenu = new ClubMenu();
		clubMenu.show();
	}
}
