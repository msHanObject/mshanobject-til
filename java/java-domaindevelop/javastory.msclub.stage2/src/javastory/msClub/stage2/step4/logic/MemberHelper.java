package javastory.msClub.stage2.step4.logic;

import java.util.List;
import java.util.Map;

import javastory.msClub.stage2.step1.entity.ClubMember;
import javastory.msClub.stgae2.step4.storage.ClubMemberStore;

public class MemberHelepr {
	//
	private ClubMemberStore memberStore;
	
	public MemberHelper() {
		//
		this.memberStore = new ClubMemberStore();
	}

	public List<ClubMember> getMembers(String clubName) {
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

	public String register (String clubName, ClubMember newMember) {
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
