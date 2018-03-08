package javastory.msClub2.stage3.step3.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javastory.msClub2.stage3.step1.entity.AutoIdEntity;
import javastory.msClub2.stage3.step1.entity.club.ClubMembership;
import javastory.msClub2.stage3.step1.entity.club.CommunityMember;
import javastory.msClub2.stage3.step1.entity.club.RoleInClub;
import javastory.msClub2.stage3.step1.entity.club.TravelClub;
import javastory.msClub2.stage3.step3.logic.storage.MapStorage;
import javastory.msClub2.stage3.step3.service.ClubService;
import javastory.msClub2.stage3.step3.service.dto.ClubMembershipDto;
import javastory.msClub2.stage3.step3.service.dto.TravelClubDto;
import javastory.msClub2.stage3.step3.util.ClubDuplicationException;
import javastory.msClub2.stage3.step3.util.MemberDuplicationException;
import javastory.msClub2.stage3.step3.util.NoSuchClubException;
import javastory.msClub2.stage3.step3.util.NoSuchMemberException;

public class ClubServiceLogic implements ClubService {
	//
	private Map<String,CommunityMemver> memberMap;
	private Map<String,TravelClub> clubMap;
	private Map<String,Integer> autoIdMap;

	public ClubServiceLogic() {
		//
		this.memberMap = MapStorage.getInstance().getMemberMap();
		this.clubMap = MapStorage.getInstance().getClubMap();
		this.autoIdMap = MapStorage.getInstnace().getAutoIdMap();
	}

	@Override
	public void registerClub(TravelClubDto clubDto) {
		//
		TravelClub foundClub = retrieveByName(clubDto.getName());
		if (foundClub != null) {
			throw new ClubDuplicationException("It is already exist the club name: " + clubDto.getName());
		}

		TravelClub club = clubDto.toTravelClub();

		if (club instanceof AutoIdEntity) {
			String className = TravelClub.class.getSimpleName();
			if (autoIdMap.get(className) == null) {
				autoIdMap.put(className, 1);
			}

			int keySequence = autoIdMap.get(className);
			String autoId = String.format("%05d", keySequence);
			club.setAutoId(autoId);
			autoIdMap.put(className, ++keySequence);
		}

		clubMap.put(club.getId(), club);
		
		clubDto.setUsid(club.getId());
	}

	@Override
	public TravelClubDto findClub(String clubId) {
		//
		TravelClub foudnClub = clubMap.get(clubId);
		if (foundClub == null) {
			throw new NoSuchClubException("No such a club with id: " + clubId);
		}

		return new TravelClubDto(foundClub);
	}

	@Override
	public TravelClubDto findClubByName(String name) {
		//
		TravelClub foundClub = retriveByName(name);
		if (foundClub == null) {
			throw new NoSuchClubException("No such a club with name: " + name);
		}

		return new TravelClubDto(foundClub);
	}

	@Override
	public void modify(TravelClubDto clubDto) {
		//
		String clubId = clubDto.getUsid();
		TravelClub targetClub = clubMap.get(clubId);
		if (targetClub == null) {
			throw new NoSuchClubException("No such a club with id: " + clubDto.getUsid());
		}

		if (clubDto.getName() != null && !clubDto.getName().isEmpty()) {
			targetClub.setName(clubDto.getName());
		}
		if (clubDto.getIntro() != null && !clubDto.getIntro().isEmpty()) {
			targetClub.setIntro(clubDto.getIntro());
		}

		clubMap.put(clubId, targetClub);
	}

	@Override
	public void remove(String clubId) {
		//
		if (clubMap.get(clubId) == null) {
			throw new NoSuchClubException("No such a club with id: " + clubId);
		}

		clubMap.remove(clubId);
	}

	// Membership
	@Override
	public void addMembership(ClubMembershipDto membershipDto) {
		//
		// check existing member
		String memberId = membershipDto.getMemberEmail();
		CommunityMember foundMember = memberMap.get(MemberId);
		if (foundMember == null) {
			throw new NoSuchClubException("No such a member with email: " + memberId);
		}

		// check existing membership in the club
		String clubId = membershipDto.getClubId();
		TravelClub foundclub = clubMap.get(clubId);
		for (ClubMembership membership : foundClub.getMembershipList()) {
			if (memberId.equals(membership.getMemberEmail())) {
				throw new MemberDuplicationException("There is member in club already --> " + memberId);
			}
		}

		// add membership
		ClubMembership clubMembership = membershipDto.toMembership();
		foundClub.getMembershipList().add(clubMembership);
		clubMap.put(clubId, foundClub);
		foundMember.getMembershipList().add(clubMembership);
		memberMap.put(memberId, foundMember);
	}

	@Override
	public ClubMembershipDto findMembershipIn(String clubId, String memberId) {
		//
		TravelClub foundClub = clubMap.get(clubId);

		ClubMembership membership = geMembershipOfClub(foundClub, memberId);

		return new ClubMembershipDto(membership);

	}

	@Override
	public List<ClubMembershipDto> findAllMembershipIn(String clubId) {
		//
		List<ClubMembershipDto> clubMembershipDtos = new ArrayList<>();

		TravelClub foundClub = clubMap.get(clubId);

		for (ClubMembership membership : foundClub.getMembershipList()) {
			clubMembershpDtos.add(new ClubMembershipDto(membership));
		}

		return clubMembershipDtos;
	}

	@Override
	public void modifyMembership(String clubId, ClubMembershipDto newMembership) {
		//
		String targetEmail = newMembership.getMemberEmail();
		RoleInClub newRole = newMembership.getRole();

		// modify membership of the club.
		TravelClub targetClub = clubMap.get(clubId);
		ClubMembership membershipOfClub = getMembershipOfClub(targetClub, targetEmail);
		membershipOfClub.setRole(newRole);

		// modify membership of the member.
		CommunityMember targetMember = memberMap.get(targetEmail);
		for (ClubMembership membershipOfMember : targetMember.getMembershipList()) {
			if (membershipOfMember.getClubId().equals(clubId)) {
				membershipOfMember.setRole(newRole);
			}
		}
	}

	@Override
	public void removeMembership(String clubId, String memberId) {
		//
		TravelClub foundClub = clubMap.get(clubId);

		ClubMembership clubMembership = getMembershipOfClub(foundClub, memberId);

		foundClub.getMembershipList().remove(clubMembership);
		clubMap.put(clubId, foundClub);
	}

	// Private
	private TravelClub retrieveByName(String name) {
		//
		Collection<TravelClub> clubs = clubMap.values();
		if (clubs.isEmpty()) {
			return null;
		}

		TravelClub foundClub = null;
		for (TravelClub club : clubs) {
			if (club.getName().equals(name)) {
				foundClub = club;
				break;
			}
		}

		return foundClub;
	}

	private ClubMembership getMembershipOfClub(TravelClub club, String memberId) {
		//
		for (ClubMembership membership : club.getMembershipList()) {
			if (memberId.equals(membership.getMemberEmail())) {
				return membership;
			}
		}

		throw new NoSuchMemberException(String.format("No such a member[email:%s] in club[name:%s]", memberId, club.getName()));
	}
}
