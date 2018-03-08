package javastory.budget.logic;

import javastory.budget.da.file.StoreFileLycler;
import javastory.budget.entity.travel.Travel;
import javastory.budget.service.TravelService;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.store.TravelStore;
import javastory.budget.util.NoSuchTravelException;
import javastory.budget.util.TravelDuplicationException;

public class TravelServiceLogic implements TravelService {
	//
	private TravelStore travelStore;
	
	public TravelServiceLogic() {
		//
		travelStore = StoreFileLycler.shareInstance().requestTravelStore();
//		travelStore = StoreMapLycler.getInstance().requestTravelStore();
	}
	
	@Override
	public void createTravel(TravelDto travelDto) {
		// 
		String travelId = travelDto.getId();
		if (travelStore.isExist(travelId)) {
			throw new TravelDuplicationException("The travel id already exists with --> " + travelId);
		}
		Travel travel = travelDto.toTravel();
		travelStore.create(travel);
	}

	@Override
	public TravelDto findTravel(String travelId) {
		//
		Travel travel = travelStore.retrieve(travelId);
		if (travel == null) {
			throw new NoSuchTravelException("There is no travel with id --> " + travelId);
		}
		
		return new TravelDto(travel);
	}

	@Override
	public TravelDto findTravelByName(String travelName) {
		// 
		Travel travel = travelStore.retrieveByName(travelName);
		if (travel == null) {
			throw new NoSuchTravelException("There is no travel with name --> " + travelName);
		}
		
		return new TravelDto(travel);
	}

	@Override
	public void modify(TravelDto travelDto) {
		// 		
		travelStore.update(travelDto.toTravel());
	}

	@Override
	public void delete(String travelId) {
		// 
		if (!travelStore.isExist(travelId)) {
			throw new NoSuchTravelException("There is no travel with id --> " + travelId);
		}
		
		travelStore.remove(travelId);
	}
}
