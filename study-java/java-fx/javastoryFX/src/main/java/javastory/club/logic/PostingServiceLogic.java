/*
 * COPYRIGHT (c) NEXTREE Consulting 2016
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2016. 7. 12.
 */
package javastory.club.logic;

import java.util.ArrayList;
import java.util.List;

import javastory.club.da.file.ClubStoreFileLycler;
import javastory.club.entity.board.Posting;
import javastory.club.entity.board.SocialBoard;
import javastory.club.service.PostingService;
import javastory.club.service.dto.PostingDto;
import javastory.club.store.BoardStore;
import javastory.club.store.PostingStore;
import javastory.club.util.exception.NoSuchBoardException;
import javastory.club.util.exception.NoSuchPostingException;

public class PostingServiceLogic implements PostingService {
	//
	private BoardStore boardStore;
	private PostingStore postingStore;

	public PostingServiceLogic() {
		//
		this.boardStore = ClubStoreFileLycler.shareInstance().requestBoardStore();
		this.postingStore = ClubStoreFileLycler.shareInstance().requestPostingStore();
//		this.boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
//		this.postingStore = ClubStoreMapLycler.getInstance().requestPostingStore();
	}

	@Override
	public void register(String boardId, PostingDto postingDto) {
		//
		SocialBoard board = boardStore.retrieve(boardId);
		if (board == null) {
			throw new NoSuchBoardException("No such board with id --> " + boardId);
		}
		Posting posting = postingDto.toPostingIn(board);
		
		String usid = postingStore.create(posting);
		postingDto.setUsid(usid);
	}

	@Override
	public PostingDto find(String postingId) {
		//
		Posting posting = postingStore.retrieve(postingId);
		if (posting == null) {
			throw new NoSuchPostingException("No such a posting with id : " + postingId);
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
			throw new NoSuchPostingException("No such a posting with id : " + postingId);
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
		if (!postingStore.exists(postingId)) {
			throw new NoSuchPostingException("No such a posting with id : " + postingId);
		}
		postingStore.delete(postingId);
	}

	@Override
	public void removeAllIn(String boardId) {
		//
		List<Posting> postings = postingStore.retrieveByBoardId(boardId);
		for (Posting posting : postings) {
			postingStore.delete(posting.getId());
		}
	}
}
