package javastory.msClub.stage3.step3.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javastory.msClub.stage3.step1.entity.board.Posting;
import javastory.msClub.stage3.step1.entity.board.SocialBoard;
import javastory.msClub.stage3.step3.logic.storage.MapStorage;
import javastory.msClub.stage3.step3.service.PostingService;
import javastory.msClub.stage3.step3.service.dto.PostingDto;
import javastory.msClub.stage3.step3.util.NoSuchBoardException;
import javastory.msClub.stage3.step3.util.NoSuchPostingException;

public class PostingServiceLogic implements PostingService {
	//
	private Map<String,SocialBoard> boardMap;
	private Map<String,Posting> postingMap;

	public PostingServiceLogic() {
		//
		this.boardMap = MapStorage.getInstance().getBoardMap();
		this.postingMap = MapStorage.getInstance().getPostingMap();
	}

	@Override
	public void register(String boardId, PostingDto postingDto) {
		//
		SocialBoard foundBoard = boardMap.get(boardId);
		if (foundBoard == null) {
			throw new NoSuchBoardException("No such board with id --> " + boradId);
		}

		Posting newPosting = postingDto.toPostingIn(foundBoard);
		postingMap.put(newPosting.getId(), newPosting);
	}

	@Override
	public PostingDto find(String postingId) {
		//
		Posting foundPosting = postingMap.get(postingId);
		if (foundPosting == null) {
			throw new NoSuchPostingException("No such a posting with id: " + postingId);
		}
		return new PostingDto(foundPosting);
	}

	@Override
	public List<PostingDto> findByBoardId(String boardId) {
		//
		SocialBoard foundBoard = boardMap.get(boardId);
		if (foundBoard == null) {
			throw new NoSuchBoardException("No such board with id --> " + boardId);
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
			throw new NoSuchPostingException("No such a posting with id: " + postingId);
		}

		// modify if only user inputs some value.
		if (newPosting.getTitle() != null && !newPosting.getTitle().isEmpty()) {
			targetPosting.setTitle(newPosting.getTitel());
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

	@Ovveride
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
