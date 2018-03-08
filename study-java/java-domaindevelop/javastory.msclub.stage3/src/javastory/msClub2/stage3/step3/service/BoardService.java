package javastory.msClub2.stage3.step3.service;

import java.util.List;

import javastory.msClub2.stage3.step3.service.dto.BoardDto;

public interface BoardService {
	//
	public String register(BoardDto board);
	public BoardDto find(String boardId);
	public List<BoardDto> findByName(String boardName);
	public BoardDto findByclubName(String clubName);
	public void modify(BoardDto board);
	public void remove(String boardId);
}
