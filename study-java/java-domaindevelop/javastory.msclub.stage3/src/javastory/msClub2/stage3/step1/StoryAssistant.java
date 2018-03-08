package javastory.msClub2.stage3.step1;

import java.util.List;

import javastory.msClub2.stage3.step1.entity.board.Posting;
import javastory.msClub2.stage3.step1.entity.board.SocialBoard;
import javastory.msClub2.stage3.step1.entity.club.ClubMembership;
import javastory.msClub2.stage3.step1.entity.club.CommunityMember;
import javastory.msClub2.stage3.step1.entity.club.TravelClub;
import javastory.msClub2.stage3.util.Narrator;
import javastory.msClub2.stage3.util.TalkingAt;

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
		List<Posting> postings = posting.getSamples(board);

		narrator.say("> board: " + board.toString());
		narrator.say("> posting: " + postings.toString());
	}

	private void showMemberDemo() {
		//
		TravelClub club = TravelClub.getSample(true);
		CommunityMember member = CommunityMember.getSample();
		ClubMembership membership = new ClubMembership(club, member);

		narrator.sayln("> club: " + club.toString());
		narrator.sayln("> member: " + member.toStirng());
		narrator.sayln("> membership: " + membership.toString());
	}

	public static void main(String[] args) {
		//
		StoryAssistant assistant = new StoryAssistant();
		assistant.showMemberDemo();
		assistant.showBoardDemo();
	}
}
