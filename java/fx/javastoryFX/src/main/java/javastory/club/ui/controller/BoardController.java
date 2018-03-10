package javastory.club.ui.controller;

import java.util.List;

import javastory.club.logic.ServiceLogicLycler;
import javastory.club.service.BoardService;
import javastory.club.service.ClubService;
import javastory.club.service.dto.BoardDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.util.exception.BoardDuplicationException;
import javastory.club.util.exception.NoSuchBoardException;
import javastory.club.util.exception.NoSuchClubException;
import javastory.club.util.exception.NoSuchMemberException;
import javastory.util.Narrator;
import javastory.util.TalkingAt;

public class BoardController {
	//
	private ClubService clubService;
	private BoardService boardService;
	
	private Narrator narrator;
	
	public BoardController() {
		//
		this.clubService = ServiceLogicLycler.shareInstance().createClubService();
		this.boardService = ServiceLogicLycler.shareInstance().createBoardService();
		this.narrator = new Narrator(this, TalkingAt.Left);
	}
	
	public void register(String clubName, String boardName, String adminEmail) {
		//
		TravelClubDto targetClub = clubService.findClubByName(clubName);
		try {
			BoardDto newBoardDto = new BoardDto(targetClub.getUsid(), boardName, adminEmail);
			boardService.register(newBoardDto);
		} catch (BoardDuplicationException | NoSuchClubException | NoSuchMemberException e) {
			narrator.sayln(e.getMessage());
		}
	}

	public List<BoardDto> searchByName(String boardName) {
		// 
		List<BoardDto> boardDtos = null;
		try {
			boardDtos = boardService.findByName(boardName);
		} catch (NoSuchBoardException e) {
			narrator.sayln(e.getMessage());
		}
		return boardDtos;
	}
	
	public BoardDto searchByClubName(String clubName) {
		//
		BoardDto foundBoard = null;
		try {
			foundBoard = boardService.findByClubName(clubName);
		} catch (NoSuchBoardException e) {
			narrator.sayln(e.getMessage());
		}
		return foundBoard;
	}

	public List<BoardDto> findAll() {
		// 
		List<BoardDto> boardList = null;
		boardList = boardService.findAll();
		
		return boardList;
	}

	public void modify(BoardDto currentBoard) {
		// 
		try {
			boardService.modify(currentBoard);
		} catch (BoardDuplicationException | NoSuchClubException | NoSuchMemberException e) {
			narrator.sayln(e.getMessage());
		}
	}

	public void remove(BoardDto currentBoard) {
		// 
		boardService.remove(currentBoard.getId());
	}
}
