package javastory.msClub2.stage3.step3.service;

import java.util.List;

import javastory.msClub2.stage3.step3.service.dot.PostingDto;

public interface PostingService {
	//
	public void register(String boardId, PostingDto posting);
	public PostingDto find(String postingId);
	public List<PostingDto> findByBoardId(String boardId);
	public void modify(PostingDto newPosting);
	public void remove(String postingId);
	public void removeAllIn(String boardId);
}
