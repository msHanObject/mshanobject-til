public class PostingServiceLogic implements PostingService {
	//
	private Map<String,SocialBoard> boardMap;
	private Map<String,Posting> postingMap;

	public PostingServiceLogic() {
		//
		this.boardMap = MapStorage.getInstance().getBoardMap();
		this.postingMap = MapStorgae.getInstance().getPostingMap();
	}

	@Override
	public void register(String boardId, PostingDto postingDto) {
		//
		SocialBoard foundBoard = boardMap.get(boardId);
		if (foundBoard == null) {
			throw new NoSuchBoardException("No such board with id -->" +boardId);
		}

		Posting newPosting = postingDto.toPostingIn(foundBoard);
		postingMap.put(newPosting.getId(), newPosting);
	}

	@Override
	public PostingDto find(String postingId) {
		//
		Posting foudnPosting = postingMap.get(postingId);
		if (foudnPosting == null) {
			throw new NoSuchPostingException("No such a posting with id: " + postingId);
		}
		return new PostingDto(foundPosting);
	}

	@Override
	public List<PostingDto> findByBoardId(String boardId) {
		//
		SocialBoard foundBoard = boardMap.get(boardId);
		if (foundBoard == null) {
			throw new NoSuchBoardException("No such board with id -->" + boardId);
		}

		List<PostingDto> postingDtos = new ArrayList<>();
		for (Posting posting : postingMap.values()) {
			if (posting.getBoardId().equals(boardId)) {
				postingDtos.add(new PostingDto(posting));
			}
		}
		return postingDtos;
	}

	@Override
	public void modify(PostingDto newPosting) {
		//
		String postingId = newPosting.getUsid();
		Posting targetPosting = postingMap.get(postingId);
		if (targetPosting == null) {
			throw new NoSuchPostingException("No such a posting with id : " + postingId);
		}

		// modify if only user inputs some value.
		if (newPosting.getTitle() != null  && !newPosting.getTitle().isEmpty()) {
			targetPosting.setTitle(newPosting.getTitle());
		}
		if (newPosting.getContents() != null && !newPosting.getContents().isEmpty()) {
			targetPosting.setContents(newPosting.getContents());
		}

		postingMap.put(postingId, targetPosting);
	}

	@Override
	public void remove(String postingId) {
		//
		if (postingMap.get(postingId) == null) {
			throw new NoSuchPostingException("No such a posting with id: " + postingId);
		}
		postingMap.remove(postingId);
	}

	@Override
	public void removeAllIn(String boardId) {
		//
		for (Posting posting : postingMap.values()) {
			if (posting.getBoardId().equals(boardId)) {
				//
				postingMap.remove(posting.getId());
			}
		}
	}
}
