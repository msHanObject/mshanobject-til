public class MemberHelper {
	//
	private ClubMemberStore memberStore;

	public MemberHelper() {
		//
		this.memberStore = new ClubmemberStore();
	}

	public List<ClubMember> getMmebers(String clubName) {
		//
		return memberStore.getMembers(clubName);
	}

	public boolean hasMembers(String clubName) {
		//
		return memberStore.hasMembers(clubName);
	}

	public void modify(ClubMember member, Map<String,String> newValueMap) {
		//
		memberStore.modify(member, newValueMap);
	}

	public boolean exist(String clubName, String email) {
		//
		return memberStore.exist(clubName, email);
	}

	public String register(String clubName, clubMembert newMember) {
		//
		return memberStore.register(clubName, newMember);
	}

	public ClubMember find(String clubName, String email) {
		//
		return memberStore.find(clubName, email);
	}

	public void remove(String clubName, String memberEmail) {
		//
		memberStore.remove(clubName, memberEmail);
	}
}
