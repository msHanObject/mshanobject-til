/*
 * COPYRIGHT (c) NEXTREE Consulting 2016
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2016. 7. 12.
 */
package javastory.club.ui.controller;

import java.util.List;

import javastory.club.logic.ServiceLogicLycler;
import javastory.club.service.BoardService;
import javastory.club.service.PostingService;
import javastory.club.service.ServiceLycler;
import javastory.club.service.dto.BoardDto;
import javastory.club.service.dto.PostingDto;
import javastory.club.util.exception.NoSuchBoardException;
import javastory.club.util.exception.NoSuchClubException;
import javastory.club.util.exception.NoSuchPostingException;
import javastory.util.Narrator;
import javastory.util.TalkingAt;

public class PostingController {
	//
	private BoardDto currentBoard;

	private BoardService boardService;
	private PostingService postingService;

	private Narrator narrator;

	public PostingController() {
		//
		ServiceLycler serviceFactory = ServiceLogicLycler.shareInstance();
		this.boardService = serviceFactory.createBoardService();
		this.postingService = serviceFactory.createPostingService();

		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	private boolean hasCurrentBoard() {
		//
		if (currentBoard != null) {
			return true;
		}
		return false;
	}

	public String requestCurrentBoardName() {
		//
		String boardName = null;

		if (hasCurrentBoard()) {
			boardName = currentBoard.getName();
		}

		return boardName;
	}
	
	public BoardDto findBoardByClubName(String clubName) {
		//
		BoardDto boardFound = null;
		try {
			boardFound = boardService.findByClubName(clubName);
		} catch (NoSuchClubException e) {
			narrator.sayln(e.getMessage());
		}
		this.currentBoard = boardFound;
		
		return currentBoard;
	}

	public void register(String title, String writerEmail, String password, String content) {
		// 
		if (!hasCurrentBoard()) {
			return;
		}
		try {
			PostingDto postingDto = new PostingDto(title, writerEmail, content);
			postingDto.setPassword(password);
			
			postingService.register(currentBoard.getId(), postingDto);
		} catch (NoSuchBoardException e) {
			narrator.sayln(e.getMessage());
		}
	}
	
	public List<PostingDto> getPostings() {
		//
		List<PostingDto> postings = null;
		try {
			postings = postingService.findByBoardId(currentBoard.getId());
		} catch (NoSuchBoardException e) {
			narrator.sayln(e.getMessage());
		}
		return postings;
	}
	
	public PostingDto searchOne(String postingId) {
		//
		PostingDto postingDto = null;
		try {
			postingDto = postingService.find(postingId);
		} catch (NoSuchPostingException e) {
			narrator.sayln(e.getMessage());
		}
		return postingDto;
	}	

	public void update(PostingDto targetPosting) {
		// 
		try {
			postingService.modify(targetPosting);
		} catch (NoSuchPostingException e) {
			narrator.sayln(e.getMessage());
		}
	}

	public void delete(PostingDto targetPosting) {
		// 
		postingService.remove(targetPosting.getUsid());
	}

	public BoardDto getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(BoardDto currentBoard) {
		this.currentBoard = currentBoard;
	}
}