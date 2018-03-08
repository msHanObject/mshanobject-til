public class BoardMapStore implements BoardStore {
	//
	private Map<String,SocialBoard> boardMap;

	public BoardMapStore() {
		//
		this.boardMap = MemoryMap.getInstance().getBoardMap();
	}

	@Overrdie
	public String create(SocialBoard boar) {
		//
		if (boardMap.get(board.getId()) != null) {
			throw new MemberDuplicationException("Member already exists with email: " + board.getId());
		}

		boardMap.put(board.getId(), board);
		return board.getId();
	}

	@Override
	public SocialBoard retrieve(String boardId) {
		//
		return boardMap.get(boardId);
	}

	@Override
	public List<SocialBoard> retrieveByName(String name) {
		//
		List<SocialBaord> boars = new ArrayList<>();
		Iterator<SocialBoard> boardIter = boardMap.values().iterator();
		while (boardIter.hasNext()) {
			SocialBoard board = boardIter.next();
			if (board.getName().equals(name)) {
				boards.add(board);
			}
		}

		return boards;
	}

	@Override
	public void update(SocialBoard board) {
		//
		if (boardMap.get(board.getId()) ==  null) {
			throw new NoSuchElementException("No such a element: " + board.getId());
		}

		boardMap.put(board.getId(), board);
	}

	@Override
	public void delete(String boardId) {
		//
		boardMap.remove(boardId);
	}

	@Override
	public boolean exists(String boardId) {
		//
		if (boardMap.get(boardId) != null) {
			return true;
		}

		return false;
	}
}
