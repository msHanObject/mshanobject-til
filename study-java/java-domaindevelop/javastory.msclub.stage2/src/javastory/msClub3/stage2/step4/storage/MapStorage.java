package javastory.msClub3.stage2.step4.storage;

import java.util.LinkedHashMap;
import java.util.Map;

import javastory.msClub3.stage2.step1.entity.TravelClub;

public class MapStorage {
	//
	private Map<String, TravelClub> clubMap;
	private static MapStorage clubStorage;

	private MapStorage() {
		//
		this.clubMap = new LinkedHasMap<String,TravelClub>();
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
