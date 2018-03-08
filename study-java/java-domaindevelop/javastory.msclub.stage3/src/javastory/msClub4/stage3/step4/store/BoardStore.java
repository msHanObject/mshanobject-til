public interface BoardStore {
	//
	public String create(SocialBoard board);
	public SocialBoard retrieve(String boardId);
	public List<SocialBoard> retrieveByName(String boardId);
	public void update(SocialBoar board);
	public void delete(String boardId);

	public boolean exists(String boardId);
}
