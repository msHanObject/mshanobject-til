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
import java.util.NoSuchElementException;

import javastory.club.da.file.ClubStoreFileLycler;
import javastory.club.entity.TravelClub;
import javastory.club.entity.board.SocialBoard;
import javastory.club.service.BoardService;
import javastory.club.service.dto.BoardDto;
import javastory.club.store.BoardStore;
import javastory.club.store.ClubStore;
import javastory.club.util.exception.BoardDuplicationException;
import javastory.club.util.exception.NoSuchBoardException;
import javastory.club.util.exception.NoSuchClubException;
import javastory.club.util.exception.NoSuchMemberException;

public class BoardServiceLogic implements BoardService {
	//
	private BoardStore boardStore;
	private ClubStore clubStore;
	
	public BoardServiceLogic() {
		//
//		this.boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
//		this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
		this.clubStore = ClubStoreFileLycler.shareInstance().requestClubStore();
		this.boardStore = ClubStoreFileLycler.shareInstance().requestBoardStore();
	}
	
	@Override
	public String register(BoardDto boardDto) {
		//
		String boardId = boardDto.getId();
		SocialBoard boardFound = boardStore.retrieve(boardId);
		if (boardFound != null) {
			throw new BoardDuplicationException("There is board already in the club -->" + boardFound.getName());
		}
		
		TravelClub clubFound = clubStore.retrieve(boardId);
		if (clubFound == null) {
			throw new NoSuchClubException("No such a club with id: " + boardId);
		}
		if (clubFound.getMebershipBy(boardDto.getAdminEmail()) == null) {
			throw new NoSuchMemberException("In the club, No such member with admin's email -->" + boardDto.getAdminEmail());
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
	public List<BoardDto> findAll() {
		// 
		List<SocialBoard> boards = boardStore.retrieveAll();
		
		if (boards == null || boards.isEmpty()) {
			throw new NoSuchBoardException("There is no board at all.");
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
		if(club == null) {
			throw new NoSuchClubException("No such a club with name: " + clubName);
		}
		
		SocialBoard board = boardStore.retrieve(club.getId());
		if (board == null) {
			throw new NoSuchElementException("No such element with clubName: " + clubName);
		}
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
