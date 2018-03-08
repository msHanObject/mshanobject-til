package javastory.club.stage1.step3;

public lass StoryTeller {
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
