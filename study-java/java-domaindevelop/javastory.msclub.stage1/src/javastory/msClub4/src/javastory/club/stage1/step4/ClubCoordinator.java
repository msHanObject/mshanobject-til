package javastory.club.stage1.step4;

import java.util.List;

public class ClubCoordinator {
	//
	private ClubStorage clubStorage;
	
	public ClubCoordinator() {
		//
		this.clubStorage = new ClubStorage();
	}
	
	public boolean hasClubs() {
		//
		if (clubStorage.count() != 0) {
			return true;
		}
		return false;
	}
	
	public boolean register(TravelClub newClub) {
		//
		String clubName = clubStorage.store(newClub);
		if (clubName == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean exist(String name) {
		//
		return clubStorage.exist(name);
	}
	
	public TravelClub find(String name) {
		//
		return clubStorage.retrieve(name);
	}
	
	public List<TravelClub> findAll() {
		//
		return clubStorage.retrieveAll();
	}
	
	public void modify(String name, String intro	) {
		//
		if (!clubStorage.exist(name)) {
			return;
		}
		
		TravelClub club = clubStorage.retrieve(name);
		club.setIntro(intro);
		
		clubStorage.update(club);
	}
	
	public void remove(String name)	{
		//
		if (!clubStorage.exist(name)) {
			return;
		}
		
		clubStorage.delete(name);
	}
}
