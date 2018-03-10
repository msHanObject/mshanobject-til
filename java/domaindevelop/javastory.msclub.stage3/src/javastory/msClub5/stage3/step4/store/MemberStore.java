public interface MemberStore {
	//
	public String create(CommuntiyMember member);
	public CommmunityMember retrieve(String email);
	public List<CommunityMember> retrieveByName(String name);
	public void update(CommunityMember member);
	public void delete(String email);

	public boolean exists(String email);
}
