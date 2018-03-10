package javastory.club.ui.controller;

import javastory.club.logic.ServiceLogicLycler;
import javastory.club.service.MemberService;
import javastory.club.service.dto.MemberDto;
import javastory.club.util.exception.InvalidEmailException;
import javastory.club.util.exception.MemberDuplicationException;
import javastory.club.util.exception.NoSuchMemberException;

public class MemberController {
	//
	private MemberService memberService;
	
	public MemberController() {
		//
		this.memberService = ServiceLogicLycler.shareInstance().createMemberService();
	}
	
	public void register(String email, String name, String nickName, String phoneNumber, String birthDay) {
		//
		try {
			MemberDto newMember = new MemberDto(email, name, nickName, phoneNumber, birthDay);
			
			memberService.register(newMember);
			System.out.println("Registered member: " + newMember.toString());
		} catch (MemberDuplicationException | InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void modify(MemberDto currentMember) {
		//
		try {
			memberService.modify(currentMember);
		} catch (NoSuchMemberException | InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void remove(MemberDto currentMember) {
		//
		memberService.remove(currentMember.getEmail());
	}
	
	public MemberDto searchByEmail(String memberEmail) {
		//
		MemberDto memberFound = null;
		try {
			memberFound = memberService.findByEmail(memberEmail);
		} catch (NoSuchMemberException e) {
			System.out.println(e.getMessage());
		}
		return memberFound;
	}
}
