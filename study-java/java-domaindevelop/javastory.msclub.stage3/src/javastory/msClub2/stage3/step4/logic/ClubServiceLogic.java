package javastory.msClub2.stage3.step4.logic;

import java.util.ArrayList;
import java.util.List;

import javastory.msClub2.stage3.step1.entity.club.ClubMembership;
import javastory.msClub2.stage3.step1.entity.club.CommunityMember;
import javastory.msClub2.stage3.step1.entity.club.RoleInClub;
import javastory.msClub2.stage3.step1.entity.club.TravelClub;
import javastory.msClub2.stage3.step4.service.ClubService;
import javastory.msClub2.stage3.step4.service.dto.ClubMembershipDto;
import javastory.msClub2.stage3.step4.store.ClubStore;
import javastory.msClub2.stage3.step4.store.MemberStore;
import javastory.msClub2.stage3.step4.util.ClubDuplicationException;
import javastory.msClub2.stage3.step4.util.MemberDuplicationException;
import javastory.msClub2.stage3.step4.util.NoSuchClubException;
import javastory.msClub2.stage3.step4.util.NoSuchMemberException;
import javastory.msClub2.stage3.step41.da.fil.ClubStoreFileLycler;

public class ClubServiceLogic implements ClubServie {
	//
	private ClubStore clubStore;
	private MemberStore memberStore;

	public ClubServiceLogic() {
		//
		this.clubStore = ClubStoreFileLycler.shareInstance().requestClubStore();
		this.memberStore = ClubStoreFileLycler.shareInstance().requestMemberStore();
	}

	@Override
	public void registerClub(TravelClubDto clubDto) {
		//
		if (clubStore.retrieveByName(clubDto.getName())  != null) {
			throw new ClubDuplicationException("It is already exist the club name:  " + clubDto.getName());
		}
		TravelClub club = clubDto.toTravelClub();
		String clubId = clubStore.create(club);

		clubDto.setUsid(clubId);
	}

	@Override
	public TravelClubDto findClub(String clubId) {
		//
		TravelClub club = clubStore.retrieve(clubId);

		if (club == null) {
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
		//check existing member
		String memberId = membershipDto.getMemberEmail();
		CommunityMember member = memberStore.retrieve(memberId);
		if (member == null) {
			throw new NoSuchClubException("No such a member with email: " + memberId);
		}

		// check existing membership in the club
		TravelClub club = clubStore.retrieve(membershipDto.getClubId());
		for (ClubMembership membership : club.getMembershipList()) {
			if (memberId.equals(membership.getMemberEmail())) {
				throw new MemberDuplicationException("There is member in club already -->" + memberId);
			}
		}

		// add membership
		ClubMembership clubMembership = membershipDto.toMembership();
		club.getMembershipList().add(clubMembership);
		clubStore.update(club);
		member.getMembershipList().add(clubMembership);
		membgerStore.update(member);
	}

	@Override
	public ClubMembershipDto findMembershipIn(String clubId, String memberId) {
		//
		TravelClub club = clubStore.retrieve(clubId);

		ClubMembership membership = getMembershipIn(club, memberId);

		return new ClubMembershipDto(membership);
	}

	@Override
	public List<ClubMembershipDto> findAllMembershipIn(String clubId) {
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
			if (memberEmail.equals(membership.getMemberEmail())) {
				return membership;
			}
		}

		String message = String.format("No such a member[email:%s] in club[name:%s]", memberEmail, club.getName());
		throw new NoSuchMemberException(message);
	}
}
