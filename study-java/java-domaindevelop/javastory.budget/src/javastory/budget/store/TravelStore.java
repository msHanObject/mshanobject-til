package javastory.budget.store;

import javastory.budget.entity.travel.Travel;

public interface TravelStore {
	//
	public boolean isExist(String travelId);
	public void create(Travel travel);
	public Travel retrieve(String travelId);
	public Travel retrieveByName(String travelName);
	public void update(Travel travel); 
	public void remove(String travelId);
}
