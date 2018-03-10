public class StoryAssistant {
	//
	private Narrator narrator;

	public StoryAssistant() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	private void showBoardDemo() {
		//
		TravelClub club = TravelClub.getSample(true);
		SocialBoard board = SocialBoard.getSample(club);
		List<Posting> postings = Posting.getSamples(board);
		narrator.say("> board: " + board.toString());
		narrator.say("> postings: " + postings.toString());
	}

	private void showMemberDemo() {
		//
		TravelClub club = TravelClub.getSample(true);
		SocialBoard board = SocialBoard.getSample(club);
		List<Posting> postings = Postings.getSamples(board);

		narrator.sayln("> club: " + club.toString());
		narrator.sayln("> member: " + member.toString());
		narrator.sayln("> membership: " + membership.toString());
	}

	public static void main(String[] args) {
		//
		StoryAssistant assistant = new StoryAssistant();
		assistant.showMemberDemo();
		assistnat.showBoardDemo();
	}
}
