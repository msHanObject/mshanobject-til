package javastory.budget.ui.contorller;

import java.util.List;

import javastory.budget.service.TravelService;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.util.exception.NoSuchTravelException;
import javastory.budget.util.exception.TravelDuplicationException;
import javastory.club.logic.ServiceLogicLycler;

public class TravelController {
	//
	private TravelService travelService;
	
	public TravelController() {
		//
		travelService = ServiceLogicLycler.shareInstance().createTravelService();
	}
	
	public boolean exists(String travelId) {
		//
		return travelService.exist(travelId);
	}
	
	public void register(TravelDto currentTravel) {
		// 
		try {			
			travelService.createTravel(currentTravel);
		} catch (TravelDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}	

	public List<TravelDto> searchByName(String travelName) {
		// 
		List<TravelDto> travelDtos = null;
		try {
			travelDtos = travelService.findTravelByName(travelName);
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
		return travelDtos;
	}
	
	public TravelDto findOne(String travelId) {
		//
		TravelDto travelFound = null;
		try {
			travelFound = travelService.findTravel(travelId);
		} catch (NoSuchTravelException e ) {
			System.out.println(e.getMessage());
		}
		return travelFound;
	}
	
	public List<TravelDto> findTravelByClubId(String clubId) {
		//
		List<TravelDto> travelDtos = null;
		try {
			travelDtos = travelService.findTravelByClubId(clubId);
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
		return travelDtos;
	}

	public List<TravelDto> findAll() {
		//
		List<TravelDto> travelDtos = null;
		try {
			travelDtos = travelService.findAll();
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
		return travelDtos;
	}
	
	public void modify(TravelDto currentTravel) {
		// 
		try {
			travelService.modify(currentTravel);
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(TravelDto currentTravel) {
		// 
		try {
			travelService.delete(currentTravel.getTravelId());
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
	}
}