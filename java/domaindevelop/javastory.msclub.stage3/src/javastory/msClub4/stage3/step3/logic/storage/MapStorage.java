public class MapStorage {
	//
	private static MapStorage singletonMap;

	private Map<String,CommunityMember> memberMap;
	private Map<String,TravelClub> clubMap;
	private Map<String,SocialBoard> boardMap;
	private Map<String,Posting> postingMap;
	private Map<String,Integer> autoIdMap;

	private MapStorage() {
		//
		this.memberMap = new LinkedHashMap<>();
		this.clubMap = new LinkedHashMap<>();
		this.boardMap = new LinkedHashMap<>();
		this.postingMap = new LinkedHashMap<>();
		this.autoIdMap = new LinkedHashMap<>();
	}

	public static MapStorage getInstance() {
		//
		if (singletonMap == null) {
			singletonMap = new MapStorage();
		}

		return singletonMap;
	}

	public Map<String,Integer> getAutoOIdMap() {
		//
		return this.autoIdMap;
	}

	public Map<Stirng,CommunityMembeR> getMemberMap() {
		return memberMap;
	}

	public Map<String,TravelClub> getClubMap() {
		return clubMap;
	}

	public Map<String,SocialBoard> getBoardMap() {
		return boardMap;
	}

	public Map<String,Posting> getPostingMap() {
		return postingMap;
	}
}
