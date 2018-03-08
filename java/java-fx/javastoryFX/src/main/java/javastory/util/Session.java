package javastory.util;

import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TravelDto;
import javastory.club.service.dto.BoardDto;
import javastory.club.service.dto.MemberDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.ui.controller.BoardController;
import javastory.club.ui.controller.ClubControllerLycler;

public class Session {
	//
	private static Session instance;
	private MemberDto loginUser;
	private TravelClubDto currentClub;
	private BoardDto currentBoard;
	private TravelDto currentTravel;
	private CashBookDto currentCashBook;
	
	private Session() {
		//
	}
	
	public synchronized static Session shareInstance() {
		//
		if (instance == null) {
			instance = new Session();
		}
		return instance;
	}
	
	public MemberDto getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(MemberDto loginUser) {
		this.loginUser = loginUser;
	}

	public TravelClubDto getCurrentClub() {
		return currentClub;
	}
	
	public void setCurrentClub(TravelClubDto targetClub) {
		this.currentClub = targetClub;
		String clubName = currentClub.getName();

		//Set current Board by club
		BoardController boardController = ClubControllerLycler.shareInstance().requestBoardController();
		this.currentBoard = boardController.searchByClubName(clubName);
	}
	
	public BoardDto getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentTravel(TravelDto currentTravel) {
		// 
		this.currentTravel = currentTravel;
	}
	
	public TravelDto getCurrentTravel() {
		return currentTravel;
	}

	public CashBookDto getCurrentCashBook() {
		return currentCashBook;
	}

	public void setCurrentCashBook(CashBookDto currentCashBook) {
		this.currentCashBook = currentCashBook;
	}
}
