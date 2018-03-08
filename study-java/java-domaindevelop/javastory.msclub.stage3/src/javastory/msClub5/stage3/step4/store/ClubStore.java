public interface ClubStore {
	//
	public String create(TravelClub club);
	public TravelClub retrieve(String clubId);
	public TravelClub retrieveByName(String name);
	public void update(TravelClub club);
	public void delete(String clubId);

	public boolean exists(String clubId);
}
