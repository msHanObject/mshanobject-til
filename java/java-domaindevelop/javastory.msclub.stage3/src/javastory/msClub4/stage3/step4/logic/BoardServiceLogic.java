public class BoardServiceLogic implements BoardService {
	//
	private BoardStore boardStore;
	private ClubStore clubStore;

	public BoardServiceLogic() {
		//
		this.boardStore = ClubStoreFileLycler.shareInstance().requestBoardStore();
		this.clubStore = ClubStoreFileLycler.shareInstance().requestClubStore();
	}

	@Override
	public String register(BoardDto boardDto) {
		//
		String boardId = boardDto.getId();
		SocialBoard boardFound = boardStore.retrieve(boardId);
		if (boardFound != null) {
			throw new BoardDuplicationException("There is board already in the club --> " + boardFound.getName());
		}

		TravelClub clubFound = clubStore.retrieve(boardId);
		if (clubFound == null) {
			throw new NoSuchClubException("No such a club with id: " + boardId);
		}
		if (clubFound.getMembershipBy(boardDto.getAdminEmail()) == null) {
			throw new NoSuchMemberException("In the club, No such member with admin's email --> " + boardDto.getAdminEmail());
		}

		SocialBoard board = boardDto.toBoard();

		return boardStore.create(board);
	}

	@Override
	public BoardDto find(String boardId) {
		//
		SocialBoard board = boardStore.retrieve(boardId);
		if (board == null) {
			throw new NoSuchBoardException("No such board with id --> " + boardId);
		}

		return new BoardDto(board);
	}

	@Override
	public List<BoardDto> findByName(String boardName) {
		//
		List<SocialBoard> boards = boardStore.retrieveByName(boardName);

		if (boards == null || boards.isEmpty()) {
			throw new NoSuchBoardException("No such board with name --> " + boardName);
		}

		List<BoardDto> boardDtos = new ArrayList<>();
		for (SocialBoard board : boards) {
			boardDtos.add(new BoardDto(board));
		}
		return boardDtos;
	}

	@Override
	public BoardDto findByClubName(String clubName) {
		//
		TravelClub club = clubStore.retrieveByName(clubName);
		if (club == null) {
			throw new NoSuchClubException("No such a club with name: " + clubName);
		}

		SocialBoard board = boardStore.retrieve(club.getId());
		return new BoardDto(board);
	}

	@Override
	public void modify(BoardDto boardDto) {
		//
		SocialBoard board = boardStore.retrieve(boardDto.getId());
		if (board == null) {
			throw new NoSuchBoardException("No such board with id --> " + boardDto.getId());
		}

		boardStore.update(boardDto.toBoard());
	}

	@Override
	public void remove(String boardId) {
		//
		SocialBoard board = boardStore.retrieve(boardId);
		if (board == null) {
			throw new NoSuchBoardException("No such board with id --> " + boardId);
		}

		boardStore.delete(boardId);
	}
}
