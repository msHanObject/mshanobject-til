public interface MemberService {
	//
	public void register(MemberDto member) throws InvalidEmailException;
	public MemberDto find(String memberId);
	public List<MemberDto> findByName(String memberName);
	public void modify(MemberDto member) throws InvalidEmailException;
	public void remove(String memberId);
}
