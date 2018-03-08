package javastory.msClub2.stage3.step4.service;

import java.util.List;
    

import javastory.msClub2.stage3.step4.service.dto.BoardDto;

public interface BoardService {
	//
	public String register(BoardDto board);
	public BoardDto find(String boardId);
	public List<BoardDto> findByName(String clubName);
	public void modify(BoardDto board);
	public void remove(String boardId);
}
