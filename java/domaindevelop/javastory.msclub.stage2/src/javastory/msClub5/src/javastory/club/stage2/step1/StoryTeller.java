package javastory.club.stage2.step1;

public class StoryTeller {
	//
	public static void main(String[] args) {
		//
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.start();
	}

	public void start() {
		//
		String name = "JavaTravelClub";
		String intro = "Travel club to the Java island.";

		TravelClub club = new TravelClub(name, intro);
		club.getMembers().add(inviteLeader());
		club.getMembers().add(inviteMember());
		System.out.println(club);

		try {
			club.getMembers().add(inviteBadEmailMemeber());
		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
	}

	private ClubMember inviteLeader() {
		//
		ClubMember leader = new ClubMember("hell@nextree.co.kr", "Hello Kim", "010-0001-0001");
		leader.setRole(RoleInClub.President);

		return leader;
	}

	private ClubMember inviteMember() {
		//
		return new ClubMemberr("mymy@nextree.co.kr", "Mymy Lee", "010-0001-1002");
	}

	private ClubMember inviteBadEmailMember() {
		//
		String invalidEmail = "^^_^^@nextree.co.kr";
		return new ClubMember(invalidEmail, "Mymy Lee", "010-0001-1003");
	}	
}
