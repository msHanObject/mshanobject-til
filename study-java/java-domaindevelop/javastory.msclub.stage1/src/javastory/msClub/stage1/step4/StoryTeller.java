package javastory.msClub.staeg1.step4;

public class StoryTeller {
	//
	public static void main(String[] args) {
		//
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.startStory();
	}

	public void startStory() {
		//
		ClubConsole travelClubConsole = new ClubConsole();
		travelClubConsole.showMenu();
	}
}
