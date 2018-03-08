/*
 * COPYRIGHT (c) NEXTREE Consulting 2016
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2016. 7. 12.
 */
package javastory.club.logic;

import java.util.ArrayList;
import java.util.List;

import javastory.club.da.file.ClubStoreFileLycler;
import javastory.club.entity.ClubMembership;
import javastory.club.entity.CommunityMember;
import javastory.club.entity.RoleInClub;
import javastory.club.entity.TravelClub;
import javastory.club.service.ClubService;
import javastory.club.service.dto.ClubMembershipDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.store.ClubStore;
import javastory.club.store.MemberStore;
import javastory.club.util.exception.ClubDuplicationException;
import javastory.club.util.exception.MemberDuplicationException;
import javastory.club.util.exception.NoSuchClubException;
import javastory.club.util.exception.NoSuchMemberException;

public class ClubServiceLogic implements ClubService {
	//
	private ClubStore clubStore;
	private MemberStore memberStore;
	
	public ClubServiceLogic() {
		//
		this.clubStore = ClubStoreFileLycler.shareInstance().requestClubStore();
		this.memberStore = ClubStoreFileLycler.shareInstance().requestMemberStore();
//		this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
//		this.memberStore = ClubStoreMapLycler.getInstance().requestMemberStore();
	}

	@Override
	public void registerClub(TravelClubDto clubDto) {
		//
		if (clubStore.retrieveByName(clubDto.getName()) != null) {
			throw new ClubDuplicationException("It is already exist the club name:" + clubDto.getName());
		}
		TravelClub club = clubDto.toTravelClub();
		String clubId = clubStore.create(club);
		
		clubDto.setUsid(clubId);
	}

	@Override
	public TravelClubDto findClub(String clubId) {
		//
		TravelClub club = clubStore.retrieve(clubId);
		
		if(club == null) {
			throw new NoSuchClubException("No such a club with id: " + clubId);
		}
		
		return new TravelClubDto(club);
	}

	@Override
	public TravelClubDto findClubByName(String name) {
		//
		TravelClub club = clubStore.retrieveByName(name);
		
		if (club == null) {
			throw new NoSuchClubException("No such a club with name: " + name);
		} 
		
		return new TravelClubDto(club);
	}

	@Override
	public void modify(TravelClubDto clubDto) {
		//
		if (!clubStore.exists(clubDto.getUsid())) {
			throw new NoSuchClubException("No such a club with id: " + clubDto.getUsid());
		}
		
		TravelClub club = clubDto.toTravelClub();
		clubStore.update(club);
	}

	@Override
	public void remove(String clubId) {
		//
		if (!clubStore.exists(clubId)) {
			throw new NoSuchClubException("No such a club with id: " + clubId);
		}
		
		clubStore.delete(clubId);
	}

	// Membership
	@Override
	public void addMembership(ClubMembershipDto membershipDto) {
		//
		// check existing member
		String memberId = membershipDto.getMemberEmail();
		CommunityMember member = memberStore.retrieve(memberId);
		if (member == null) {
			throw new NoSuchMemberException("No such a member with email: " + memberId);
		}
		
		// check existing membership in the club
		TravelClub club = clubStore.retrieve(membershipDto.getClubId());
		for (ClubMembership membership : club.getMembershipList()) {
			if(memberId.equals(membership.getMemberEmail())) {
				throw new MemberDuplicationException("There is member in club already -->" + memberId);
			}
		}
		
		// add membership
		ClubMembership clubMembership = membershipDto.toMembership();
		club.getMembershipList().add(clubMembership);
		clubStore.update(club);
		member.getMembershipList().add(clubMembership);
		memberStore.update(member);
	}

	@Override
	public ClubMembershipDto findMembershipIn(String clubId, String memberId) {
		//
		TravelClub club = clubStore.retrieve(clubId);
		
		ClubMembership membership = getMembershipIn(club, memberId);
		if (membership == null) {
			return null;
		}
		return new ClubMembershipDto(membership);
	}
	
	@Override
	public List<ClubMembershipDto> findAllMembershipsIn(String clubId) {
		//
		List<ClubMembershipDto> clubMembershipDtos = new ArrayList<>();
		
		TravelClub club = clubStore.retrieve(clubId);
		
		for (ClubMembership membership : club.getMembershipList()) {
			clubMembershipDtos.add(new ClubMembershipDto(membership));
		}
		
		return clubMembershipDtos;
	}

	@Override
	public void modifyMembership(String clubId, ClubMembershipDto newMembership) {
		//
		String targetEmail = newMembership.getMemberEmail();
		RoleInClub newRole = newMembership.getRole();
		
		// modify membership of the club.
		TravelClub targetClub = clubStore.retrieve(clubId);
		ClubMembership membershipOfClub = getMembershipIn(targetClub, targetEmail);
		membershipOfClub.setRole(newRole);
		clubStore.update(targetClub);
		
		// modify membership of the member.
		CommunityMember targetMember = memberStore.retrieve(targetEmail);
		for (ClubMembership membershipOfMember : targetMember.getMembershipList()) {
			if (membershipOfMember.getClubId().equals(clubId)) {
				membershipOfMember.setRole(newRole);
			}
		}
		memberStore.update(targetMember);
		
	}

	@Override
	public void removeMembership(String clubId, String memberId) {
		//
		TravelClub foundClub = clubStore.retrieve(clubId);
		CommunityMember foundMember = memberStore.retrieve(memberId);
		ClubMembership clubMembership = getMembershipIn(foundClub, memberId);
		
		// remove membership
		foundClub.getMembershipList().remove(clubMembership);
		clubStore.update(foundClub);
		foundMember.getMembershipList().remove(clubMembership);
		memberStore.update(foundMember);
		
	}
	
	private ClubMembership getMembershipIn(TravelClub club, String memberEmail) {
		//
		for (ClubMembership membership : club.getMembershipList()) {
			if(memberEmail.equals(membership.getMemberEmail())) {
				return membership;
			}
		}
		
		String message = String.format("No such a member[email:%s] in club[name:%s]", memberEmail, club.getName());
//		throw new NoSuchMemberException(message);
		System.out.println(message);
		return null;
	}

	@Override
	public List<TravelClubDto> findAllClub() {
		// 
		List<TravelClub> travelClubs = clubStore.retrieveAll();
		List<TravelClubDto> travelClubDtos = new ArrayList<>();
		for (TravelClub travelClub : travelClubs) {
			travelClubDtos.add(new TravelClubDto(travelClub));
		}
		return travelClubDtos;
	}
}
