package javastory.msClub2.stage3.step3.logic.storage;

import java.util.LinkedHashMap;
import java.util.Map;

import javastory.msClub2.stage3.step1.entity.board.Posting;
import javastory.msClub2.stage3.step1.entity.board.SocialBoard;
import javastory.msClub2.stage3.step1.entity.club.CommunityMember;
import javastory.msClub2.stage3.step1.entity.club.TravelClub;

public class MapStorage {
	//
	private static MapStorage singletonMap;

	private Map<String,CommunityMember>  memberMap;
	private Map<String,TravelClub>  clubMap;
	private Map<String,SocialBoard> boardMap;
	private Map<String,Posting> postingMap;
	private Map<String,Integer> autoIdMap;

	private MapStorage() {
		//
		this.memberMap = new LinkedHashMap<>();
		this.clubMap = new LinkedHashMap<>();
		this.boardMap = new LinkedHashMap<>();
		this.postingMap = new LinkedHashMap<>();
		this.autoIdMap = new LinkedHashMap<>();
	}
	
	public static MapStorage getInstance() {
		//
		if (singletonMap == null) {
		    singletonMap = new MapStorage();
		}
		
		return singletonMap;
	}
	
	public Map<String,Integer> getAutoIdMap() {
		//
		return this.autoIdMap;
	}

	public Map<String,CommunityMember> getMemberMap() {
		return memberMap;
	}
	
	public Map<String,TravelClub> getClubMap() {
	    return clubMap;
	}
	
	public Map<String,SocialBoard> getBoardMap() {
		return boardMap;
	}
	
	public Map<String,Posting> getPostingMap() {
	    return postingMap;
	}
}
