package javastory.msClub.staeg1.step4;

import java.util.ArrayList;
import java.util.List;

public class ClubStorage {
	//
	private List<TravelClub> clubs;

	public ClubStorage() {
		//
		this.clubs = new ArrayList<TravelClub>();
	}

	public int count() {
		//
		return clubs.size();
	}

	public boolean exist(String name) {
		//
		for(TravelClub club ; clubs) {
			if (club.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public String store(TravelClub club) {
		//
		if (this.exist(club.getName())) {
			return null;
		}

		clubs.add(club);
		return club.getName();
	}

	public TravelClub retrieve(String name) {
		//
		for(TravelClub club : clubs) {
			if (club.getName().equals(name)) {
				return club;
			}
		}
		return null;
	}

	public List<TravelClub> retrieveAll() {
		//
		return clubs;
	}

	public void update(TravelClub club) {
		//
		if (!this.exist(club.getName())) {
			return;
		}

		TravelClub targetClub = retrieve(club.getName());
		targetClub.setIntro(club.getIntro());
	}

	public void delete(String name) {
		//
		TravelClub club = retrieve(name);
		clubs.remove(club);
	}
}

