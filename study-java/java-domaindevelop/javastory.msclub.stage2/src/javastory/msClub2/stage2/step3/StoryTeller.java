package javastory.msClub2.stage2.step3;

import javastory.msClub2.stage2.step3.ui.menu.Clubmenu;

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
