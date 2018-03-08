package javastory.msClub2.stage2.step4.storage;

import java.util.LinkedHashMap;
import java.util.Map;

import javastory.msClub2.stage2.step1.entity.TravelClub;

public class MapStorage {
	//
	private Map<String, TravelClub> clubMap;
	private static MapStorage clubStorage;

	private MapStorage() {
		//
		this.clubMap = new LinkedHashMap<String, TravelClub>();
	}

	public static MapStorage getInstance() {
		//
		if (clubStorage == null) {
			clubStorage = new MapStorage();
		}

		return clubStorage;
	}

	public Map<String,TraveClub> getClubMap() {
		return clubMap;
	}
}
