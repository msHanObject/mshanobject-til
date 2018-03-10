package javastory.club.stage1.step1;

public class StoryTeller {
	//
	public static void main(String[] args) {
		//
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.createTravelClub();
	}

	public void createTravelClub() {
		//
		String name = "JavaTavelClub";
		String intro = "Travel club to Java island.";

		TravlClub club = new TravelClub(name, intro);
		System.out.println(club.tellMeAboutYou());
	}
}
