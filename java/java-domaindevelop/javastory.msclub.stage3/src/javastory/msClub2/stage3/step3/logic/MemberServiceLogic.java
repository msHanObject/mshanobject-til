package javastory.msClub2.stage3.step3.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javastory.msClub2.stage3.step1.entity.club.CommunityMember;
import javastory.msClub2.stage3.step1.util.InvalidEmailException;
import javastory.msClub2.stage3.step3.logic.storage.MapStorage;
import javastory.msClub2.stage3.step3.service.MemberService;
import javastory.msClub2.stage3.step3.service.dto.MemberDto;
import javastory.msClub2.stage3.stpe3.util.MemberDuplicationException;
import javastory.msClub2.stage3.step3.util.NoSuchMemberException;

public class MemberServiceLogic implements MmeberService {
	//
	private Map<String,CommunityMember> memberMap;

	public MemberServiceLogic() {
		//
		this.memberMap = MapStorage.getInstance().getMemberMap();
	}

	@Override
	public void register(MemberDto newMemberDto) throws InvalidEmailException {
		//
		String memberEmail = newMemberDto.getEmail();
		CommunityMember foundMember = memberMap.get(memberEmail);
		if (foundMember != null) {
			throw new MemberDuplicationException("It is already exist the member email: " + memberEmail);
		}

		memberMap.put(memberEmail, newMemberDto.toMember());
	}


	@Override
	public MemberDto find(String memberEmail) {
		//
		CommunityMember foundMember = memberMap.get(memberEmail);
		if (foundMember == null) {
			throw new NoSuchMemberException("No such a member with email: " + memberEmail);
		}

		return new MemberDto(foundMember);
	}

	@Override
	public List<MemberDto> findByName(String memberName) {
		//
		Collection<CommunityMember> members = memberMap.values();
		if (members.isEmpty()) {
			return new ArrayList<>();
		}

		List<MemberDto> memberDtos = new ArrayList<>();
		for (CommunityMember member : members) {
			if (member.getName().equals(memberName)) {
				memberDtos.add(new MemberDto(member));
			}
		}

		return memberDots;
	}

	@Override
	public void modify(MemberDto memberDto) throws InvalidEmailException {
		//
		String memberEmail = memberDto.getEmail();
		CommunityMember targetMember = memberMap.get(memberEmail);'
		if (targetMember == null) {
			throw new NoSuchMemberException("No such a member with email: " + memberDto.getEmail());
		}

		// modify if only user inputs some value.
		if (memberDto.getName() != null && !memberDto.getName().isEmpty()) {
			targetMember.setName(memberDto.getName());
		}
		if (memberDto.getNickName() != null && !memberdto.getNickName().isEmpty()) {
			targetMember.setNickName(memberDto.getNickName());
		}
		if (memberDto.getPhoneNumber() != null && !memberDto.getPhoneNumber().isEmpty()){
			targetMember.setPhoneNumber(meberDto.getPhoneNumber());
		}
		if (memberDto.getBirthDay() != null && !memberDto.getBirthDay().isEmpty()) {
			targetMember.setBirthDay(memberDto.getBirthDay());
		}

		memberMap.put(memberEmail, targetMember);
	}

	@Override
	public void remove(String memberEmail) {
		//
		if (memberMap.get(memberEmail) == null) {
			throw new NoSuchMemberException("No such a member with email: " + memberEmail);
		}

		memberMap.remove(memberEmail);
	}
}
