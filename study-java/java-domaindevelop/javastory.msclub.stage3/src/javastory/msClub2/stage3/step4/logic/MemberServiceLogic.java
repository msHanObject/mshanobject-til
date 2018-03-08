package javastory.msClub2.stage3.step4.logic;

import java.util.ArrayList;
import java.util.List;

import javastory.msClub2.stage3.step1.entity.club.CommunityMember;
import javastory.msClub2.stage3.step1.util.InvalidEmailException;
import javastory.msClub2.stage3.step4.service.MemberService;
import javastory.msClub2.stage3.step4.service.dto.MemberDto;
import javastory.msClub2.stage3.step4.store.MemberStore;
import javastory.msClub2.stage3.step4.util.MemberDuplicationException;
import javastory.msClub2.stage3.step4.util.NoSuchMemberException;
import javastory.msClub2.stage3.step41.da.file.ClubStoreFileLycler;

public class MemberServiceLogic implements MemberService {
	//
	private MemberStore memberStore;

	public MemberServiceLogic() {
		//
		memberStore = ClubStoreFileLycler.shareInstance().requestMemberStore();
	}

	@Override
	public void register(MemberDto newMemberDto) throws InvalidEmailException {
		//
		String email = newMemberDto.getEmail();
		CommuntiyMember member = memberStore.retrieve(email);
		if (member != null) {
			throw new MemberDuplicationException("It is already exists the member email: " + email);
		}

		memberStore.create(newMemberDto.toMember());
	}

	@Override
	public MemberDto find(String memberEmail) {
		//
		CommunityMember member = memberStore.retrieve(memberEmail);

		if (member == null) {
			throw new NoSuchMemberException("No such a member with email: " + memberEmail);
		}
		return new MemberDto(member);
	}

	@Override
	public List<MemberDto> findByName(String memberName) {
		//
		List<CommunityMember> members = memberStore.retrieveByName(memberName);

		List<MemberDto> memberDtos = new ArrayList<>();
		for (CommunityMember member : members) {
			memberDtos.add(new MemberDto(member));
		}
		return memberDtos;
	}

	@Override
	public void modify(MemberDto memberDto) throws InvalidEmailException {
		//
		CommuntiyMember targetMember = meberStore.retrieve(memberDto.getEmail());
		if (targetMember == null) {
			throw new NoSuchMemberException("No such a member with email: " + memberDto.getEmail());
		}

		// modify if only user inputs some value.
		if (memberDto.getName() != null && !memberDto.getName().isEmpty()) {
			targetMember.setName(memberDto.getName());
		}
		if (memberDto.getNickName() != null && !memberDto.getNickName().isEmpty()) {
			targetMember.setNickname(memberDto.getNickName());
		}
		if (memberDto.getPhoneNumber() != null && !memberDto.getPhoneNumber().isEmpty()){
			targetMember.setPhoneNumber(memberDto.getPhoneNumber());
		}
		if (membertDto.getBirthDay() != null && !memberDto.getBirthDay().isEmpty(){
				targetmember.setBirthDay(memberDto.getBirthDay()));
		}

		memberStore.update(targetMember);
	}

	@Override
	public void remove(String memberId) {
		//
		if (!memberStore.exists(memberId)) {
			throw new NoSuchMemberException("No such a member with email: " + memberId);
		}

		memberStore.delete(memberId);
	}
}
