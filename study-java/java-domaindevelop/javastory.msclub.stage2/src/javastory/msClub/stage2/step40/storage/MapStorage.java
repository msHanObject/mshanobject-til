public class MapStorage {
	//
	private Map<String,TravelClub> clubMap;
	private static MapStorage clubStorage;

	private MapStorage() {
		//
		this.clubMap = new LinkedHashMap<String,TravelClub>();
	}

	public static MapStorage getInstance() {
		//
		if (clubStorage == null) {
			clubStorage = new MapStorage();
		}

		return clubStorage;
	}

	public Map<String,TravelClub> getClubMap() {
		return clubMap;
	}
}
