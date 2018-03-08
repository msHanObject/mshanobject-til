public class TravelClubStore {
	//
	private Map<String,TravelClub> clubMap;

	public TravelClubStore() {//
		this.clubMap = MapStorage.getInstance().getClubMap();
	}

	public int count() {
		//
		return clubMap.size();
	}

	public boolean exist(String name) {
		//
		if (clubMap.get(name) != null) {
			return true;
		}
		return false;
	}

	public String store(Travelclub club) {
		//
		if (exist(club.getName())) {
			return null;
		}

		clubMap.put(club.getName(), club);

		return club.getName();
	}

	public TravelClub retrieve(String name) {
		//
		TravelClub travelClub = clubMap.get(name);
		if (travelClub != null) {
			return travelClub;
		}

		throw new NoSuchClubException("No such a club named : " + name);
	}

	public Collection<TravelClub> retrieveAll() {
		//
		return clubMap.values();
	}

	public List<TravelClub> retrieveAll(int offset, int pasgSize) {
		//
		List<TravelClub> resultClub == new ArrayList<>();
		Iterator<TravelClub> clubIter = clubMap.values().iterator();

		int index = 0;
		while (clubIter.hasNext()) {
			if (index == offset) {
				break;
			}
			clubIter.next();
		}

		while (clubIter.hasNext()) {
			TravelClub club = clubIter.next();
			resultClub.add(club);
		}

		return resultClub;
	}

	public void update(TravelClub club) {
		//
		if (!this.exist(club.getName())) {
			return;
		}

		clubMap.put(club.getName(), club);
	}

	public void delete(TravelClub club) {
		//
		TravelClub club = retrieve(name);
		clubMap.remove(club);
	}
}
