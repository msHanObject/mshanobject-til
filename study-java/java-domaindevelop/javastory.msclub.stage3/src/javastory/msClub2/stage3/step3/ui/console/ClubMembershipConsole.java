package javastory.msClub2.stage3.step3.ui.console;

import javastory.msClub2.stage3.step1.entity.club.RoleInClub;
import javastory.msClub2.stage3.step3.logic.ServiceLogicLycler;
import javastory.msClub2.stage3.step3.service.ClubService;
import javastory.msClub2.stage3.step3.service.ServiceLycler;
import javastory.msClub2.stage3.step3.service.dto.ClubMembershipDto;
import javastory.msClub2.stage3.step3.service.dto.TravelClubDto;
import javastory.msClub2.stage3.step3.util.MemberDuplicationException;
import javastory.msClub2.stage3.step3.util.NoSuchClubException;
import javastory.msClub2.stage3.step3.util.NoSuchMemberException;
import javastory.msClub2.stage3.util.ConsoleUtil;
import javastory.msClub2.stage3.util.Narrator;
import javastory.msClub2.stage3.util.TalkingAt;

public class ClubMembershipConsole {
	//
	private TravelClubDto currentClub;

	private ClubService clubService;

	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public ClubMembershipConsole() {
		//
		ServiceLycler serviceFactory = ServiceLogicLycler.shareInstance();
		this.clubService = serviceFactory.createClubService();

		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
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

	public void findClub() {
		//
		TravelClubDto clubFound = null;
		while (true) {
			String clubName = consoleUtil.getValueOf("\n club name to find(0.Membership menu)");
			if (clubName.equals("0")) {
				break;
			}
			try {
				clubFound = clubService.findClubByName(clubName);
				narrator.sayln("\t > Found club: " + clubFound);
				break;
			
			} catch (NoSuchClubException e) {
				narrator.sayln(e.getMessage());
			}
			clubFound = null;
		}
		this.currentClub = clubFound;
	}

	public void add() {
		//
		if (!hasCurrentClub()) {
			//
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		while (true) {
			String email = consoleUtil.getValueOf("\n member's email to add(0.Member menu)");
			if (email.equals("0")) {
				return;
			}

			String memberRole = consoleUtil.getValueOf(" President|Member");

			try {
				ClubMembershipDto clubMembershipDto = new ClubMembershipDto(currentClub.getUsid(), email);
				clubMembershipDto.setRole(RoleInClub.valueOf(memberRole));

				clubService.addMembership(clubMembershipDto);
				narrator.sayln(String.format("Add a member[email:%s] in club[name:%s]", email, currentClub.getName()));

			} catch (MemberDuplicationException | NoSuchClubException e) {
				narrator.sayln(e.getMessage());
			} catch (IllegalArgumentException e) {
				narrator.sayln("The Role in club should be president or member.");
			}
		}
	}

	public void find() {
		//
		if (!hasCurrentClub()) {
			//
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		ClubMembershipDto membershipDto = null;
		while (true) {
			String memberEmail = consoleUtil.getValueOf("\n email to find(0.Membership menu) ");
			if (memberEmail.equals("0")) {
				break;
			}
			try {
				membershipDto = clubService.findMembershipIn(currentClub.getUsid(), memberEmail);
				narrator.sayln("\t > Found membership information: " + membershipDto);

			} catch (NoSuchMemberException e) {
				narrator.sayln(e.getMessage());
			}
		}
	}

	public ClubMembershipDto findOne() {
		//
		ClubMembershipDto membershipDto = null;
		while (true) {
			String memberEmail = consoleUtil.getValueOf("\n member email to find(0.Membership menu) ");
			if (memberEmail.equals("0")) {
				break;
			}

			try {
				membershipDto = clubService.findMembershipIn(currentClub.getUsid(), memberEmail);
				narrator.sayln("\t > Found membership information: " + membershipDto);
				break;

			} catch (NoSuchMemberException e) {
				narrator.sayln(e.getMessage());
			}
		}
		return membershipDto;
	}

	public void modify() {
		//
		if (!hasCurrentClub()) {
			//
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		ClubMembershipDto targetMembership = findOne();
		if (targetMembership == null) {
			return;
		}

		String newRole = consoleUtil.getValueOf("new President|Member(0.Membership menu,Enter. no change)");
		if (!newRole.equals("0")) {
			return;
		}
		if (!newRole.equals("")) {
			targetMembership.setRole(RoleInClub.valueOf(newRole));
		}
		String clubId = targetMembership.getClubId();
		clubService.modifyMembership(clubId, targetMembership);

		ClubMembershipDto modifiedMembership = clubService.findMembershipIn(clubId, targetMembership.getMemberEmail());
		narrator.sayln("\t > Modified membership information: " + modifiedMembership);
	}

	public void remove() {
		//
		if (!hasCurrentClub()) {
			//
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		ClubMembershipDto targetMembership = findOne();
		if (targetMembership == null) {
			return;
		}

		String confirmStr = consoleUtil.getValueOf("Remove this member in the club? (Y: yes, N: no)");
		if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
			//
			narrator.sayln("Removing a membership --> " + targetMembership.getMemberName());
			clubService.removeMembership(currentClub.getUsid(), targetMembership.getMemberEmail());
		} else {
			narrator.sayln("Remove cancelled, the member is safe. --> " + targetMembership.getMemberName());
		}
	}
}
