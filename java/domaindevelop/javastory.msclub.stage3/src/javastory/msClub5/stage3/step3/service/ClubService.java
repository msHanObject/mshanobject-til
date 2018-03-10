public interface ClubService {
	//
	public void registerClub(TravelClubDto club);
	public TravelClubDto findClub(String clubId);
	public TravelClubDto findClubByName(String name);
	public void modify(TravelclubDto club);
	public void remove(String clubId);

	void addMembership(ClubMembershipDto membershipDto);
	public ClubMembershipDto findMembershipIn(String clubId, String memberId);
	public List<ClubMembershipDto> findAllMembershipsIn(String clubId);
	public void modifyMembership(String clubId, ClubMembershipDto member);
	public void removeMembership(String clubId, String memberId);
}
