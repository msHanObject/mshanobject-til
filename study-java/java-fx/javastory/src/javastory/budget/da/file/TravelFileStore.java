package javastory.budget.da.file;

import java.util.List;
import java.util.NoSuchElementException;

import javastory.budget.da.file.io.TravelFile;
import javastory.budget.entity.travel.Travel;
import javastory.budget.store.TravelStore;
import javastory.budget.util.NoSuchTravelException;
import javastory.budget.util.TravelDuplicationException;

public class TravelFileStore implements TravelStore {
	//
	private TravelFile travelFile;
	
	public TravelFileStore() {
		//
		travelFile = new TravelFile();
	}
	
	@Override
	public boolean isExist(String travelId) {
		// 
		return travelFile.exists(travelId);
	}

	@Override
	public void create(Travel travel) {
		// 
		if (travelFile.exists(travel.getId())) {
			throw new TravelDuplicationException("The travel already exists: " + travel.toString());
		}
		travelFile.write(travel);
	}

	@Override
	public Travel retrieve(String travelId) {
		// 
		return travelFile.read(travelId);
	}

	@Override
	public List<Travel> retrieveByName(String travelName) {
		// 
		return travelFile.readByName(travelName);
	}

	@Override
	public List<Travel> retrieveAll() {
		// 
		return travelFile.readAll();
	}
	
	@Override
	public void update(Travel travel) {
		// 
		if (!travelFile.exists(travel.getId())) {
			throw new NoSuchElementException("No such element: " + travel.toString());
		}
		travelFile.update(travel);
	}

	@Override
	public void remove(String travelId) {
		// 
		if (!travelFile.exists(travelId)) {
			throw new NoSuchTravelException("No such travel with id: " + travelId);
		}
		travelFile.delete(travelId);
	}
}
