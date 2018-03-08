package javastory.msClub2.stage3.step4.logic;

import java.util.ArrayList;
import java.util.List;

import javastory.msClub2.stage3.step1.entity.board.Posting;
import javastory.msClub2.stage3.step1.entity.board.SocialBoard;
import javastory.msClub2.stage3.step4.service.PostingService;
import javastory.msClub2.stage3.step4.service.dto.PostingDto;
import javastory.msClub2.stage3.step4.store.BoardStore;
import javastory.msClub2.stage3.step4.store.PostingStore;
import javastory.msClub2.stage3.step4.util.NoSuchBoardException;
import javastory.msClub2.stage3.step4.util.NoSuchPostingException;
import javastory.msClub2.stage3.step41.da.file.ClubStoreFileLycler;

public class PostingServiceLogic implements PostingService {
	//
	private BoardStore boardStore;
	private PostingStore postingStore;

	public PostingServiceLogic() {
		//
		this.boardStore = ClubStoreFileLycler.shareInstance().requestBoardStore();
		this.postingStore = ClubstoreFileLycler.shareInstance().reqeustPostingStore();
	}

	@Override
	public String register(String boardId, PostingDto postingDto) {
		//
		SocialBoard board = boardStore.retrieve(boardId);
		if (board == null) {
			throw new NoSuchBoardException("No Such board with id --> " + boardId);
		}
		Posting posting = postingDto.toPostingIn(board);

		return postingStore.create(posting);
	}

	@Overrdie
	public PostingDto find(String postingId) {
		//
		Posting posting = postingStore.retrieve(postingId);
		if (posting == null) {
			throw new NoSuchPostingException("No such a posting with id: " + postingId);
		}
		return new PostingDto(posting);
	}

	@Override
	public List<PostingDto> findByBoardId(String boardId) {
		//
		SocialBoard board = boardStore.retrieve(boardId);
		if (board == null) {
			throw new NoSuchBoardException("No such board with id --> " + boardId);
		}
		
		List<Posting> postings = postingStore.retrieveByBoardId(boardId);

		List<PostingDto> postingDtos = new ArrayList<>();
		for (Posting posting : postings) {
			postingDtos.add(new PostingDto(posting));
		}
		return postingDtos;
	}

	@Override
	public void modify(PostingDto newPosting) {
		//
		String postingId = newPosting.getUsid();
		Posting targetPosting = postingStore.retrieve(postingId);
		if (targetPosting == null) {
			throw new NoSuchPostingException("No such a posting with id: " + postingId);
		}

		// modify if only user inputs some value.
		if (newPosting.getTitle() != null && !newPosting.getTitle().isEmpty()) {
			targetPosting.setTitle(newPosting.getTitle());
		}
		if (newPosting.getContents() != null && !newPosting.getContents().isEmpty()) {
			targetPosting.setContents(newPosting.getContents());
		}
		
		postingStore.update(targetPosting);
	}

	@Override
	public void remove(String postingId) {
		//
		if (!postingStore.eixst(postingId)) {
			throw new NoSuchPostingException("No such a posting with id: " + postingId);
		}
		postingStore.delete(postingId);
	}

	@Override
	public void removeAll(String boardId) {
		//
		List<Posting> postings = postingStore.retrieveByBoardId(boardId);
		for (Posting posting : postings) {
			postingStore.delete(posting.getId());
		}
	}
}
