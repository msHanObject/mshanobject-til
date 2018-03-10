/*
 * COPYRIGHT (c) NEXTREE Consulting 2016
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2016. 7. 12.
 */
package javastory.club.ui.controller;

import java.util.List;

import javastory.club.logic.ServiceLogicLycler;
import javastory.club.service.ClubService;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.util.exception.ClubDuplicationException;
import javastory.club.util.exception.NoSuchClubException;
import javastory.util.Narrator;
import javastory.util.TalkingAt;

public class ClubController {
	//
	private ClubService clubService;
	private Narrator narrator;

	public ClubController() {
		//
		this.clubService = ServiceLogicLycler.shareInstance().createClubService();
		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	public TravelClubDto register(String clubName, String intro) {
		//
		TravelClubDto clubDto = new TravelClubDto(clubName, intro);
		try {
			clubService.registerClub(clubDto);
			narrator.say("Registered club :" + clubDto.toString());
		} catch (ClubDuplicationException e) {
			narrator.sayln(e.getMessage());
		}
		return clubDto;
	}
	
	public List<TravelClubDto> findAll() {
		//
		List<TravelClubDto> travelClubs = clubService.findAllClub();
		return travelClubs;
	}
	
	public TravelClubDto searchByName(String clubName) {
		//
		TravelClubDto clubFound = null;
		try {
			clubFound = clubService.findClubByName(clubName);
			narrator.sayln("\t > Found club: " + clubFound);
		} catch (NoSuchClubException e) {
			narrator.sayln(e.getMessage());
		}
		return clubFound;
	}
	
	public TravelClubDto serachById(String clubId) {
		//
		TravelClubDto clubFound = null;
		try {
			clubFound = clubService.findClub(clubId);
		} catch (NoSuchClubException e) {
			narrator.sayln(e.getMessage());
		}
		return clubFound;
	}
	
	public void modify(TravelClubDto targetClub) {
		//
		if (targetClub.getName().isEmpty()) {
			return;
		}
		
		if (targetClub.getIntro().isEmpty()) {
			return;
		}

		try {
			clubService.modify(targetClub);
			narrator.sayln("\t > Modified club: " + targetClub);
		} catch (IllegalArgumentException | NoSuchClubException e) {
			narrator.sayln(e.getMessage());
		}
	}

	public void remove(TravelClubDto targetClub) {
		//
		clubService.remove(targetClub.getUsid());
	}
}