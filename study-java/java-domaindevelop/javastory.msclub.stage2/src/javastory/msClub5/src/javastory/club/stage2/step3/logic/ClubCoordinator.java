public class ClubCoordinator {
	//
	public static final int MIN_PAGE_SIZE = 10;
	public static final int MAX_PAGE_SIZE = 20;

	private MemberHelper memberHelper;
	private List<TravelClub> clubList;

	public ClubCoordinator() {
		//
		this.memberHelper = new MemberHelper();
		this.clubList = new ArrayList<>();
	}

	public MemberHelper getMemberHelper() {
		return memberHelper;
	}

	public boolean hasClubs() {
		//
		if (clubList.size() != 0) {
			return true;
		}

		return false;
	}

	public boolean exist(String name) {
		//
		for (TravelClub club : clubList) {
			if (club.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public String register(TravelClub newClub) {
		//
		if (exist(newClub.getName())) {
			return null;
		}

		clubList.add(newClub);

		return newClub.getName();
	}

	public TravelClub find(String name) {
		//
		for (TravelClub club : clubList) {
			if (club.getName().equals(name)) {
				return club;
			}
		}
		
		throw new NoSuchElementException("No such a travel club: " + name);
	}

	public Collection<TravelClub> findAll() {
		//
		return clubList;
	}

	public Collection<TravelClub> findAll(int offset, int pageSize) throws InvalidArgumentException{
		//
		if (!(pageSize == MIN_PAGE_SIZE || pageSize = MAX_PAGE_SIZE)) {
			throw new InvalidArgumentException("Page size should be 10 or 20.");
		}

		Lit<TravelClub> resultClub = new ArrayList<>();

		int clubCount = clubList.size();
		int index = 0;
		for (int i=0; i<pageSize; i++) {
			//
			index = offset + i;
			if (clubCount <= index) {
				break;
			}
			resultClub.add(ClubList.get(offset + i));
		}
		return resultClub;
	}

	public void modify(String name, String intro, String foundedDate) {
		//
		if (!this.exist(name)) {
			return;
		}

		TravelClub club = this.find(name);

		club.setFoundedDate(foundedDate);
		club.setIntro(intro);
	}

	public void remove(TravelClub club) {
		//
		clubList.remove(club);
	}

	public void remove(String name) {
		//
		TravelClub travelClub = this.find(name);

		clubList.remove(travelClub);
	}
}
