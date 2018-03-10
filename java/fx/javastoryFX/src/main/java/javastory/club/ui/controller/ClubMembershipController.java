/*
 * COPYRIGHT (c) NEXTREE Consulting 2016
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2016. 7. 12.
 */
package javastory.club.ui.controller;

import java.util.List;

import javastory.club.entity.RoleInClub;
import javastory.club.logic.ServiceLogicLycler;
import javastory.club.service.ClubService;
import javastory.club.service.ServiceLycler;
import javastory.club.service.dto.ClubMembershipDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.util.exception.MemberDuplicationException;
import javastory.club.util.exception.NoSuchClubException;
import javastory.util.Narrator;
import javastory.util.TalkingAt;

public class ClubMembershipController {
	//
	private TravelClubDto currentClub;

	private ClubService clubService;
	private Narrator narrator;

	public ClubMembershipController() {
		//
		ServiceLycler serviceFactory = ServiceLogicLycler.shareInstance();
		this.clubService = serviceFactory.createClubService();

		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	public boolean hasCurrentClub() {
		//
		if (currentClub != null) {
			return true;
		}

		return false;
	}

	public String requestCurrentClubName() {
		//
		String clubName = null;

		if (hasCurrentClub()) {
			clubName = currentClub.getName();
		}

		return clubName;
	}
	
	public TravelClubDto findAndGetClub(String clubName) {
		//
		return clubService.findClubByName(clubName);
	}
	
	public List<TravelClubDto> findAllClub() {
		//
		return clubService.findAllClub();
	}
	
	
	public ClubMembershipDto searchByEmail(String email) {
		//
		ClubMembershipDto membershipDto = null;
		String clubId = null;
		if (currentClub != null) {			
			clubId = currentClub.getUsid();
		}
		
		if (!email.isEmpty()) {
			membershipDto = clubService.findMembershipIn(clubId, email);
		}
		
		return membershipDto;
	}
	
	public List<ClubMembershipDto> findAllMembershipsIn(String clubId) {
		//
		List<ClubMembershipDto> memberships = clubService.findAllMembershipsIn(clubId);
		return memberships;
	}

	public TravelClubDto getCurrentClub() {
		return currentClub;
	}

	public void setCurrentClub(TravelClubDto currentClub) {
		this.currentClub = currentClub;
	}

	public void register(String email, RoleInClub role) {
		// 
		try {
			ClubMembershipDto clubMembershipDto = new ClubMembershipDto(currentClub.getUsid(), email);
			clubMembershipDto.setRole(role);
			
	        clubService.addMembership(clubMembershipDto);
		} catch (MemberDuplicationException | NoSuchClubException e) {
			narrator.sayln(e.getMessage());
		} catch (IllegalArgumentException e) {
			narrator.sayln("The Role in club should be president or member.");
		}
	}

	public void update(ClubMembershipDto currentMemberShip) {
		// 
		String clubId = currentMemberShip.getClubId();
		clubService.modifyMembership(clubId, currentMemberShip);
		
		ClubMembershipDto modifiedMembership = clubService.findMembershipIn(clubId, currentMemberShip.getMemberEmail());
		narrator.sayln("\t > Modified memebeship information: " + modifiedMembership);
	}

	public void delete(ClubMembershipDto currentMemberShip) {
		// 
		narrator.sayln("Removing a membership -->" + currentMemberShip.getMemberName());
		clubService.removeMembership(currentClub.getUsid(), currentMemberShip.getMemberEmail());
	}
	
	public void deleteInClub() {
		List<ClubMembershipDto> membershipListFound = this.findAllMembershipsIn(currentClub.getUsid());
		for (ClubMembershipDto membership : membershipListFound) {
			this.delete(membership);
		}
	}
}