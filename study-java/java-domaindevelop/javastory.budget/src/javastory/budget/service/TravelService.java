package javastory.budget.service;

import javastory.budget.service.dto.TravelDto;

public interface TravelService {
	//
	public void createTravel(TravelDto travelDto);
	public TravelDto findTravel(String travelId);
	public TravelDto findTravelByName(String travelName);
	public void modify(TravelDto travelDto);
	public void delete(String travelId);
}
