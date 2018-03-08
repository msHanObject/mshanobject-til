package javastory.msClub3.stage1.step3;

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
