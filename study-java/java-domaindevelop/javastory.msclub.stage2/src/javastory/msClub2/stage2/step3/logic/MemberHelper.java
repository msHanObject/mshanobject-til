package javastory.msClub2.stage2.step3.logic;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javastory.msClub2.stage2.step1.entity.ClubMember;
import javastory.msClub2.stage2.step1.entity.TravelClub;

public class MemberHelepr {
	//
	public MemberHelepr() {
		//
	}

	public List<ClubMember> getMembers(TravelClub club) {
		return club.getMembers();
	}

	public boolean hasMembers(TravelClub club) {
		//
		if (club.getMembers().size() != 0) {
			return true;
		}

		return false;
	}

	public void modify(ClubMember member, Map<String,String> newValueMap) {
		//
		Iterator<String> nameIter = newValueMap.keySet().iterator();
		while (nameIter.hasNext()) {
			String name = nameIter.next();
			String value = newValueMap.get(name);

			if (name.equals("name")) {
				member.setName(value);
			} else if (name.equals("nickname")) {
				member.setNickname(value);
			} else if (name.equals("phoneNumber")) {
				member.setPhoneNumber(value);
			} else if (name.equals("birthDay")) {
				member.setBirthDay(value);
			}
		}
	}

	public boolean exist(TravelClub club, String email) {
		//
		for (ClubMember member : club.getMembers()) {
			if (member.getEmail().equals(email)) {
				return true;
			}
		}

		return false;
	}

	public String register (TravelClub club, ClubMember newMember) {
		//
		if (exist(club, newMember.getEmail())) {
			return null;
		}

		club.getMembers().add(newMember);

		return newMember.getEmail();
	}

	public ClubMember find(TravelClub club, String email) {
		//
		for (ClubMember member : club.getMembers()) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		return null;
	}

	public void remove(TravelClub club, ClubMember clubMember) {
		//
		club.getMembers().remove(clubMember);
	}
}
