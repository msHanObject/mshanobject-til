public interface PostingStore {
	//
	public String create(Posting posting);
	public Posting retrieve(String postingId);
	public List<Posting> retrieveByBoardId(String boardId);
	public List<Posting> retrieveByTitle(String title);
	public void update(Posting posting);
	public void delete(String postingId);

	public boolean exists(String postingId);
}
