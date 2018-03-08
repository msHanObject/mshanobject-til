package javastory.budget.da.map;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import javastory.budget.da.map.io.MemoryMap;
import javastory.budget.entity.travel.Travel;
import javastory.budget.store.TravelStore;
import javastory.budget.util.TravelDuplicationException;

public class TravelMapStore implements TravelStore{
	//
	private Map<String,Travel> travelMap;
	
	public TravelMapStore() {
		//
		travelMap = MemoryMap.getInstance().getTravelMap();
	}
	
	@Override
	public boolean isExist(String travelId) {
		// 
		if (travelMap.get(travelId) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void create(Travel travel) {
		// 
		String travelId = travel.getId();
		if (travelMap.get(travelId) != null) {
			throw new TravelDuplicationException("The travel is already exists with id --> " + travelId);
		}
		
		travelMap.put(travelId, travel);
	}

	@Override
	public Travel retrieve(String travelId) {
		// 		
		return travelMap.get(travelId);
	}

	@Override
	public Travel retrieveByName(String travelName) {
		// 
		Iterator<Travel> travelIter = travelMap.values().iterator();
		Travel travelFound = null;
		while (travelIter.hasNext()) {
			if (travelIter.next().getName().equals(travelName)) {
				travelFound = travelIter.next();
				break;
			}
		}
		return travelFound;
	}

	@Override
	public void update(Travel travel) {
		// 
		if (travelMap.get(travel.getId()) == null) {
			throw new NoSuchElementException("No such a element with --> " + travel.toString());
		}
		
		travelMap.put(travel.getId(), travel);
	}

	@Override
	public void remove(String travelId) {
		// 
		travelMap.remove(travelId);
	}
}
