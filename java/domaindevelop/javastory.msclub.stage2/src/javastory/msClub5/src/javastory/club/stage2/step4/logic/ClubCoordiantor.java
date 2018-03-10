public class ClubCoordinator {
	//
	public static final int MIN_PAGE_SIZE = 10;
	public static final int MAX_PAGE_SIZE = 20;

	private MemberHelper memberHelper;
	private TravelClubStore clubStore;

	public ClubCoordinator() {
		//
		this.memberHelper = new MemberHelper();
		this.clubStore = new TravelClubStore();
	}

	public MemberHelper getMemberHelper() {
		return memberHelper;
	}

	public boolean hasClubs() {
		//
		if (clubStore.count() != 0) {
			return true;
		}

		return false;
	}

	public boolean exist(String name) {
		//
		return clubStore.exist(name);
	}

	public void register(TravelClub newClub) {
		//
		if (clubStore.exist(newClub.getName())) {
			throw new ClubDuplicationException(newClub.getName());
		}

		clubStore.store(newClub);
	}

	public TravelClub find(String name) {
		//
		return clubStore.retrieve(name);
	}

	public Collection<TravelClub> findAll() {
		//
		return clubStore.retrieveAll();
	}

	public Collection<TravelClub> findAll(int offset, int pageSize) throws InvalidArgumentException {
		//
		if (!(pageSize == MINI_PAGE_SIZE || pageSize == MAX_PAGE_SIZE)) {
			throw new InvalidArgumentException("Page size should be 10 or 20.");
		}

		return clubStore.retrieveAll(offset, pageSize);
	}

	public void modify(String name, Strinfg intro) {
		//
		if (!clubStore.exist(name)) {
			return;
		}

		TravelClub club = clubStore.retrieve(name);
		club.setIntro(intro);

		clubStore.update(club);
	}

	public void remove(TravelClub club) {
		//
		remove(club.getName());
	}

	public void remove(String name) {
		//
		if (!clubStore.exist(name)) {
			return;
		}

		clubStore.delete(name);
	}
}
