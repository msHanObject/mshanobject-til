public interface PostingService {
	//
	public String register(String boardId, PostingDto posting);
	public PostingDto find(String postingId);
	public List<PostingDto> findByBoardId(String boardId);
	public void modify(PostingDto newPosting);
	public void remove(String postingId);
	public void removeAllIn(String boardId);
}
