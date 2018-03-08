/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */
package javastory.club.stage3.step4.store;

import javastory.club.stage3.step1.entity.club.TravelClub;

public interface ClubStore {
	//
	public String create(TravelClub club); 
	public TravelClub retrieve(String clubId);
	public TravelClub retrieveByName(String name);
	public void update(TravelClub club); 
	public void delete(String clubId); 
	
	public boolean exists(String clubId); 
}