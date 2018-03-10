public interface BoardService {
	//
	public Stirng register(BoardDto board);
	public BoardDto find(String boardId);
	public List<BoardDto> findByName(String boardName);
	public BoardDto findByClubName(String clubName);
	public void modify(BoardDto board);
	public void remove(String boardId);
}
