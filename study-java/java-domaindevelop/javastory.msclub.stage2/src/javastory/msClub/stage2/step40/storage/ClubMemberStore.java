public class ClubMemberStore {
	//
	private Map<String,TravelClub> clubMap;

	public ClubMemberStore() {
		//
		this.clubMap = MapStorage.getInstance().getClubMap();
	}

	public List<ClubMember> getMembers(String clubName) {
		//
		TravelClub club = clubMap.get(clubName);
		return club.getMembers();
	}

	public boolean hasMembers(String clubName) {
		//
		TravelClub club = clubMap.get(clubName);

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
				member.setPhoneNumber(value);
			} else if (name.equals("birthDay")) {
				member.setBirthDay(value);
			}
		}
	}

	public boolean exist(String clubName, String email) {
		//
		TravelClub club = clubMap.get(clubName);

		for (ClubMember member : club.getMembers()) {
			if (member.getEmail().equals(email)) {
				return true;
			}
		}

		return false;
	}

	public String register (String clubName, ClubMember newMember) {
		//
		TravelClub club = clubMap.get(clubName);

		if (exist(club.getName(), newMember.getEmail())) {
			return null;
		}

		club.getMembers().add(newMember);

		return newMember.getEmail();
	}

	public ClubMember find(String clubName, String email) {
		//
		TravelClub club = clubMap.get(clubName);

		for (ClubMember member : club.getMembers()) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		return null;
	}

	public void remove(String clubName, String memberEmail) {
		//
		TravelClub club = clubMap.get(clubName);

		ClubMember targetMember = null;
		for (ClubMember member : club.getMembers()) {
			if (member.getEmail().equals(memberEmail)) {
				targetMember = member;
				break;
			}
		}

		if (targetMember != null) {
			club.getMembers().remove(targetMember);
		}
	}
}
