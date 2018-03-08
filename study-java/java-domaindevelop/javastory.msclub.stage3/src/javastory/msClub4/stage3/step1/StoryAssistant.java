public class StoryAssistant {
	//
	private Narrator narrator;

	public StoryAssistnat() {
		//
		this.narrator = new Narrator(this,TalkingAt.Left);
	}

	private void showBoardDemo() {
		//
		TravelClub club = TravelClub.getSample(true);
		SocialBoard board = SocialBoard.getSample(club);
		List<Posting> postings = Posting.getSamples(board);

		narrator.sayln("> board: " + board.toString());
		narrator.sayln("> posting: " + postins.toString());
	}
	
	private void showMemberDemo() {
		//
		TravelClub club = TravelClub.getSample(true);
		CommunityMember member = CommunityMember.getSample();
		ClubMembership membership = new ClubMembership(club, member);

		narrator.sayln("> club: " + club.toString());
		narrator.sayln("> member: " + member.toString());
		narrator.sayln("> membership: " + membership.toString());
	}

	public static void main(String[] args) {
		//
		StoryAssistant assistant = new StoryAssistant();
		asisstant.showMemeberDemo();
		asisstant.showBoardDemo();
	}
}
