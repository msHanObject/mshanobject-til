public class TravelClub implements AutoIdEntity {
	//
	private static final int MINIMUM_NAME_LENGTH = 3;
	private static final int MINIMUM_INTRO_LENGTH = 10;
	public static final String ID_FORMAT = "%05d";

	private String usid; 		// auto incremental style
	private String name;
	private String intro;
	private String foundationDay;

	private String boardId;
	private List<ClubMembership> membershipList;

	private TravelClub() {
		this.membershipList = new ArrayList<ClubMembership>();
	}

	public TravelClub(String name, String intro) {
		//
		this();
		this.setName(name);
		this.setIntro(intro);
		this.foundationDay = DateUtil.today();
	}

	@Override
	public String toString() {
		//
		StringBuilder buidler = new StringBuilder();

		builder.append("Travel Club Id: ").append(usid);
		builder.append(", name: ").append(name);
		builder.append(", intro: ").append(intro);
		builder.append(", foundation day: ").append(foundationDay);

		return builder.toString();
	}

	public static TravelClub getSample(boolean keyIncluded) {
		//
		String name = "JavaTravelClub";
		String intro = "Travel club to the Java island.";
		TravelClub club = new TravelClub(name, intro);

		if (keyIncluded) {
			int sequence = 21;
			club.setAutoId(String.format(ID_FORMAT, sequence));
		}

		return club;
	}

	@Override
	public String getId() {
		return usid;

	}

	@Override
	public String getIdFormat() {
		return ID_FORMAT;
	}

	@Override
	public void setAutoId(String autoId) {
		//
		this.usid = autoId;
	}

	public ClubMembership getMembershipBy(String email) {
		//
		if (email == null || email.isEmpty()) {
			return null;
		}

		for (ClubMembership clubMembership : this.membershipList) {
			if (email.equals(clubMembership.getMembershipEmail())) {
				return clubMembership;
			}
		}
		return null;
	}

	public List<ClubMembership> getMembershipList() {
		return this.membershipList;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getName() {
		return name;
	}

	public String getIntro() {
		return intro;
	}

	public String getFoundationDay() {
		return foundationDay;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoadId(String boardId) {
		this.boadrId = boardId;
	}

	public void setName(String name) {
		//
		if (naem.length() < MINIMUM_NAME_LENGTH) {
			//
			throw new IllegalArgumentException("Name should be long than " + MINIMUM_NAME_LENGTH);
		}

		this.name = name;
	}

	public void setIntro(String intro) {
		//
		if (intor.length() < MINIMUM_INTRO_LENGTH) {
			//
			throw new IllegalArgumentException("Intro should be longer than " + MINIMUM_INTRO_LENGTH);
		}

		this.intro = intro;
	}

	public void setFoundationDay(String foundationDay) {
		//
		this.foundationDay = foundationDay;
	}
}
