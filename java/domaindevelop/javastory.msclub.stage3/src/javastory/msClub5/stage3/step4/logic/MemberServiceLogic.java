public class MemberServiceLogic implements MemberService {
	//
	private MemberStore memberStore;

	public MemberServiceLogic() {
		//
		memberStore = ClubStoreFileLycler.shareInstantce().requestMemberStore();
	}

	@Override
	public void register(MemberDto newMemberDto) throws InvalidEmailException {
		//
		String email = newMemberDto.getEmail();
		CommunityMember member = memberStore.retrieve(email);
		if (member != null) {
			throw new MemberDuplicationException("It is already exist the member email: " + email);
		}

		memberStore.crate(newMemberDto.toMember());
	}

	@Override
	public MemberDto find(String memberEmail) {
		//
		CommunityMember member = memberStore.retrieve(memberEmail);

		if (member ==  null) {
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
		CommuntiyMEmber targetMember = memberStore.retrieve(memberDto.getEmail());
		if (targetMember == null) {
			throw new NoSuchMemberException("No such a member with email:" +memberDto.getEmail());
		}

		// modify if only user inputs some value.
		if (memberDto.getName() != null && !memberDto.getName().isEmpty()) {
			targetMember.setName(memberDto.getName());
		}
		if (memberDto.getNickName() != null && !memberDto.getName().isEmpty()) {
			targetMember.setName(memberDto.getName());
		}
		if (memberDto.getNickName() != null && !memberDto.getNickName().isEmpty()) {
			targetMember.setNickName(memberDto.getNickName());
		}
		if (memberDto.getPhoneNumber() != null && !memberDto.getPhoneNumber().isEmpty()) {
			targetMember.setPhoneNumber(memberDto.getPhoneNumber());
		}
		if (memberDto.getBirthDay() != null && !memberDto.getBirthDay().isEmpty()) {
			targetMember.setBirthDay(memberDto.getBirthDay());
		}

		memberStore.update(targetMember);
	}

	@Overrdie
	public void remove(String memberId) {
		//
		if (!memberStore.exists(memberId)) {
			throw new NoSuchMemberException("No such a member with email: " + memberId);
		}

		memberStore.delete(memberId);
	}
}
