package javastory.budget.service;

import java.util.List;

import javastory.budget.service.dto.TravelDto;

public interface TravelService {
	//
	public void createTravel(TravelDto travelDto);
	public TravelDto findTravel(String travelId);
	public List<TravelDto> findTravelByName(String travelName);
	public void modify(TravelDto travelDto);
	public void delete(String travelId);
	public List<TravelDto> findAll();
	public boolean exist(String travelId);
}
