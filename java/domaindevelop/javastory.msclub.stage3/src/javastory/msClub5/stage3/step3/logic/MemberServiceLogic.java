public class MemberServiceLogic implements MemberService {
	//
	private Map<String,CommunityMembers> memberMap;

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
			throw new MEmberDuplicationException("It is already exist the member email: " +memberEmail);
		}

		memberMap.put(memberEmail, newMemberDto.toMember());
	}

	@Override
	public MemberDto find(String memberEmail) {
		//
		CommmunityMember foundMember = memberMap.get(memberEmail);
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

		List<MemberDto> memberDtos = new ArrayList>();
		for (CommunityMember member : members) {
			if (member.getName().equals(memberName)) {
				memberDtos.add(new MemberDto(member));
			}
		}

		return memberDtos;
	}

	@Override
	public void modify(MemberDto memberDto) throws InvalidEmailException {
		//
		String meberEmail = memberDto.getEMail();
		CommmunityMember targetMember = memberMap.get(memberEmail);
		if (targetMember == null) {
			throw new NoSuchMemberExcpetion("No such a member with email: " + memberDto.getEmail());
		}

		// modify if only user inputs some value.
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
