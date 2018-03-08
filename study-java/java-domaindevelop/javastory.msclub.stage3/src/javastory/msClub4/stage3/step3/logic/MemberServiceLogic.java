public class MemberServiceLogic implements MemberService {
	//
	private Map<String,CommuniytMember> memberMap;

	public MemberServiceLogic() {
		//
		this.memberMap = MapStorage.getIntstance().getMemberMap();
	}

	@Override
	public void register(MemberDto newMemberDto) throws InvalidEmailException {
		//
		String memberEmail = newMemberDto.getEmail();
		CommunityMember foundMember = memberMap.get(MemberEmail);
		if (foundMember != null) {
			throw new MemberDuplicationException("It is already exist the meber email: " + memberEmail);
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
		for (CommunityMember> member : members) {
			if (member.getName().equals(memberName)) {
				memberDtos.add(newMemberDto(member));
			}
		}

		return memberDtos;
	}

	@Override
	public void modify(MemberDto memberDto) throws InvalidEmailException {
		//
		String memberEmail =  memberDto.getEmail();
		CommunityMember targetMember = memberMap.get(memberEmail);
		if (targetMember == null) {
			throw new NoSuchMemberException("No such a member with email: " + memberDto.getEmail());
		}

		// modify if onlye user inputs some value.
		if (memberDto.getName() != null && !memberDto.getName().isEmpty()) {
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
