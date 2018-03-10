/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */
package javastory.club.stage3.step4.da.map;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import javastory.club.stage3.step1.entity.AutoIdEntity;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.util.ClubDuplicationException;

public class ClubMapStore implements ClubStore {
	//
	private Map<String,TravelClub> clubMap; 
	private Map<String,Integer> autoIdMap; 
	
	public ClubMapStore() {
		//  
		this.clubMap = MemoryMap.getInstance().getClubMap(); 
		this.autoIdMap = MemoryMap.getInstance().getAutoIdMap();
//		this.clubMap = (Map<String, TravelClub>) ClubStoreFileLycler.shareInstance().requestClubStore();
//		AutoIdFile autoIdFile = new AutoIdFile();
//		this.autoIdMap = (Map<String, Integer>) autoIdFile.read(this.getClass().getSimpleName());
	}
	
	@Override
	public String create(TravelClub club) {
		// 
		if (clubMap.get(club.getId()) != null) {
			throw new ClubDuplicationException("Club already exists with id: " + club.getId()); 
		}

		if (club instanceof AutoIdEntity) {
			String className = TravelClub.class.getSimpleName(); 
			if(autoIdMap.get(className) == null) {
				autoIdMap.put(className, 1); 
			}
			
			int keySequence = autoIdMap.get(className); 
			String autoId = String.format("%05d", keySequence); 
			club.setAutoId(autoId);
			autoIdMap.put(className, ++keySequence);
		}
		
		clubMap.put(club.getId(),  club); 

		return club.getId();
	}

	@Override
	public TravelClub retrieve(String clubId) {
		// 
		return clubMap.get(clubId); 
	}
	
	@Override
	public TravelClub retrieveByName(String name) {
		//
		Iterator<TravelClub> clubIter = clubMap.values().iterator(); 
		TravelClub clubFound = null; 
		while(clubIter.hasNext()) {
			TravelClub club = clubIter.next(); 
			if (club.getName().equals(name)) {
				clubFound = club;
				break; 
			}
		}
		
		return clubFound; 
	}

	@Override
	public void update(TravelClub club) {
		// 
		if (clubMap.get(club.getId()) == null) {
			throw new NoSuchElementException("No such a element: " + club.getId()); 
		}
		
		clubMap.put(club.getId(), club); 
	}

	@Override
	public void delete(String clubId) {
		// 
		clubMap.remove(clubId); 
	}

	@Override
	public boolean exists(String clubId) {
		//
		if (clubMap.get(clubId) != null) {
			return true; 
		};
		
		return false; 
	}
}