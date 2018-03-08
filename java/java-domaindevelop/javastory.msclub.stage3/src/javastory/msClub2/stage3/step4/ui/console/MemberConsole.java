package javastory.msClub2.stage3.step4.ui.console;

import javastory.msClub2.stage3.step1.util.InvalidEmailException;
import javastory.msClub2.stage3.step4.logic.ServiceLogicLycler;
import javastory.msClub2.stage3.step4.service.MemberService;
import javastory.msClub2.stage3.step4.service.dto.MemberDto;
import javastory.msclub2.stage3.step4.util.MemberDuplicationException;
import javastory.msClub2.stage3.step4.util.NoSuchMemberException;
import javastory.msClub2.stage3.util.ConsoleUtil;
import javastory.msClub2.stage3.util.Narrator;
import javastory.msClub2.stage3.util.TalkingAt;

public class MemberConsole {
	//
	private MemberService memberService;

	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public MemberConsole() {
		//
		this.memberService = ServiceLogicLycler.shareInstance().createMemberService();
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void register() {
		//
		while (true) {
			String email = consoleUtil.getValueOf("\n new member's email(0.Member menu)");
			if (email.equals("0")) {
				return;
			}

			String name = consoleUtil.getValueOf(" name");
			String phoneNumber = consoleUtil.getValueOf(" phone number");
			String nickName = consoleUtil.getValueOf(" nickname");
			String birthDay = consoleUtil.getValueOf(" birthday(yyyy.mm.dd)");

			try {
				MemberDto newMember = new MemberDto(email, name, phoneNumber);
				newMember.setNickName(nickName);
				newMember.setBirthDay(birthDay);

				memberService.register(newMember);
				narrator.syaln("Registered member: " + newMember.toString());

			} catch (MemberDuplicationException | InvalidEmailException e) {
				narrator.sayln(e.getMessage());
			}
		}
	}

	public void find() {
		//
		while (true) {
			//
			String email = consoleUtil.getValueOf("\n new member's email(0.Member menu)");
			if (email.equals("0")) {
				return;
			}

			try {
				MemberDto memberFound = memberService.find(email);
				narrator.sayln("Found member: " + memberFound.toString());
			
			} catch (NoSuchMemberException e) {
				narrator.sayln(e.getMessage());
			}
		}
	}

	public MemberDto findOne() {
		//
		MemberDto memberFound = null;
		while (true) {
			//
			String email = consoleUtil.getValueOf("\n member's email to find(0.Member menu)");
			if (email.equals("0")) {
				return null;
			}

			try {
				memberFound = memberService.find(email);
				narrator.sayln("Found member: " + memberFound.toString());
				break;

			} catch (NoSuchMemberException e) {
				narrartor.sayln(e.getMessage());
			}
			memberFound = null;
		}
		return memberFound;
	}

	public void modify() {
		//
		MemberDto targetMember = findOne();
		if (targetMember == null) {
			return;
		}

		String newName = consoleUtil.getValueOf(" new name(Enter. no change)");
		String newPhoneNumber = consoleUtil.getValueOf(" new phone number(Enter. no change)");
		String newNickName = consoleUtil.getValueOf(" new nickname(Enter. no change)");
		String newBirthDay = consoleUtil.get ValueOf(" new birthday(yyyy.mm.dd)(Enter. no change)");
		 
		try {
			MemberDto newMember = new MemberDto(targetMember.getEmail(), newName, newPhoneNumber);
			newMember.setNickName(newNickName);
			newMember.setBirthDay(newBirthDay);

			memberService.modify(newMember);
			narrator.sayln("Modified member: " + newMember.toString());

		} catch (NoSuchMemberException | InvalidEmailException e) {
			narrator.sayln(e.getMessage());
		}
	}

	public void remove() {
		//
		MemberDto targetMember = findOne();
		if (targetMember == null) {
			return;
		}

		String confirmStr = consoleUtil.getValueOf("Remove this club? (Y: yes, N: no)");
		if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
			narrator.sayln("Removing a club --> " + targetMember.getName());
			memberService.remove(targetMember.getEmail());
		} else {
			narrator.sayln("Remove cancelled, your club is safe. --> " + targetMember.getName());
		}
	}
}
