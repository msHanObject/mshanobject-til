package javastory.msClub.stage2.step4.ui.window;

import javastory.msClub.stage2.step1.entity.ClubMember;
import javastory.msClub.stage2.step1.entity.RoleInClub;
import javastory.msClub.stage2.step1.entity.TravelClub;
import javastory.msClub.stage2.step4.logic.MemberHelper;
import javastory.msClub.stage2.step4.logic.ClubCoordinator;
import javastory.msClub.stage2.util.ConsoleUtil;
import javastory.msClub.stage2.util.InvalidEmailException;
import javastory.msClub.stage2.util.Narrator;
import javastory.msClub.stage2.util.TalkingAt;

public class MemberWindow {
	//
	private TravelClub currentClub;
	private ClubCoordinator clubCoordinator;
	private MemberHelper memberHelper;

	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public MemberWindow(ClubCoordinator clubCoordinator) {
		//
		this.clubCoordinator = clubCoordinator;
		this.memberHelper = clubCoordinator.getMemberHelper();
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void setCurrentClub(TravelClub club) {
		this.currentClub = club;
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

	public TravelClub findAnotherClub() {
		//
		TravelClub clubFound = null;

		if (!clubCoordinator.hasClubs()) {
			narrator.sayln("<?> No clubs in the storage.");
			currentClub = null;
			return null;
		}

		while (true) {
			String clubName = consoleUtil.getValueOf("\n name to find(0.Member menu) ");

			if (clubName.equals("0")) {
				break;
			}

			if (clubCoordinator.exist(clubName)) {
				clubFound = clubCoordinator.find(clubName);
				narrator.sayln("\t > Found club: " + clubFound);
				break;
			} else {
				narrator.sayln("\t > No such club in the storage --> " + clubName);
			}
			clubFound = null;
		}

		currentClub = clubFound;
		return clubFound;
	}

	public void add() {
		//
		if (currentClub == null) {
			//
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		while (true) {
			String email = consoleUtil.getValueOf("\n new member's email(0.Member menu)");
			if (email.equals("0")) {
				return;
			}

			if (memberHelper.exist(currentClub.getName(), email)) {
				narrator.sayln("Member with this email already exists. --> " + email);
				continue;
			}

			String name = consoleUtil.getValueOf(" name");
			String phoneNumber = consoleUtil.getValueOf(" phone number");
			String nickName = consoleUtil.getValueOf(" nickname");
			String birthDay = consoleUtil.getValueOf(" birthday(yyyy.mm.dd)");
			String memberRole = consoleUtil.getValueOf(" President|Member");

			try {
				ClubMember newMember = new ClubMember(email, name, phoneNumber);
				newMember.setNickname(nickname);
				newMember.setBirthDay(birthDay);
				newMember.setRole(RoleInClub.valeuOf(memberRole));
				if (!memberHelper.hasMembers(currentClub.getName())) {
					newMember.setRole(RoleInClub.President);
				}

				memberHelper.register(currentClub.getName(), newMember);
				narrator.sayln("Registered member: " + newMember.toString());

			} catch (InvalidEmailException e) {
				narrator.sayln(e.getMessage());
			}
		}
	}

	public void find() {
		//
		if (currentClub == null) {
			//
			narrator.sayln("No target club yet, Find target club first.");
			return;
		}

		if (!memberHeleper.hasMembers(currentClub.getName())) {
			narrator.sayln("No members in the target club --> " + currentClub.getName());
		}

		while (true) {
			String email = consoleUtil.getValueOf("\n member email to modify(0.Member menu)");

			if (email.equals("0") || email.equals("")) {
				return;
			}

			if (!memberHelper.exist(currentClub.getName(), email)) {
				narrator.sayln("\t > No such a member -> " + email);
				continue;
			}

			Clubmember member = memberHelper.find(currentClub.getName(), email);
			narrator.sayln("\t > Found member: " + member.toString());

			String nemName = consoleUtil.getValueOf(" new name(Enter. no change)");
			if (!newName.equals("")) {
				member.setName(newName);
			}

			String newPhoneNumber = consoleUtil.getValeuOf(" new phone number(Enter. no change)");
			if (!newPhoneNumber.equals("")) {
				member.setPhoneNUmber(newPhoneNumber);
			}

			String newNickName = consoleUtil.getValueOf(" new nickname(Enter. no change)");
			if (!newNickName.equals("")) {
				member.setNickname(newNickName);
			}

			String newBirthDay = consoleUtil.getValueOf(" new birthday(yyyy.mm.dd)(Enter. no change)");
			if (!newBirthDay.equals("")) {
				member.setBirthDay(newBirthDay);
			}

			String newRole = consoleUtil.getValueOf("new President|Member(Enter. no change)");
			if (!newRole.equals("")) {
				member.setRole(RoleInClub.valueOf(newRole));
			}

			narrator.sayln("Modified member: " + member.toString());
		}
	}

	public void remove() {
		//
		if (currentClub == null) {
			//
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		if (!memberHeleper.hasMembers(currentClub.getName())) {
			narrator.sayln("No members in the target club --> " + currentClub.getName());
		}

		while (true) {
			String email = consoleUtil.getValueOf("\n member email to remove(0.Member menu)");

			if (email.equals("0") || email.equals("")) {
				return;
			}

			if (!memberHelper.exist(currentClub.getName(), email)) {
				narrator.sayln("\t > No such a member -> " + email);
				continue;
			}

			memberHelper.remove(currentClub.getName(), email);
			narrator.sayln("\t > Removed by email --> " + email);

			break;
		}
	}
}
