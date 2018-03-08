package javastory.msClub.stage1.step1;

public class StoryTeller {
	//
	public static void main(String[] args) {
		//
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.createTravelClub();
	}
	
	public void createTravelClub() {
		//
		String name = "JavaTravelClub";
		String intro = "Travel club to Java island.";
		
		TravelClub club = new TravelClub(name, intro);
		System.out.println(club.tellMeAboutYou()) ;
	}
}
