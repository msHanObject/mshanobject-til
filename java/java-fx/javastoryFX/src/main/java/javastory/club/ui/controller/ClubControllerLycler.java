package javastory.club.ui.controller;

import javastory.budget.ui.contorller.TravelController;

public class ClubControllerLycler {
	//
	private static ClubControllerLycler instance;
	
	private ClubController clubController;
	private MemberController memberController;
	private ClubMembershipController membershipController;
	private BoardController boardController;
	private PostingController postingController;
	private TravelController travelController;
	
	private ClubControllerLycler() {
		//
	}
	
	public synchronized static ClubControllerLycler shareInstance() {
		//
		if (instance == null) {
			instance = new ClubControllerLycler();
		}
		return instance;
	}
	
	public ClubController requestClubController() {
		//
		if (clubController == null) {
			clubController = new ClubController();
		}
		return clubController;
	}
	
	public MemberController requestMemberController() {
		//
		if (memberController == null) {
			memberController = new MemberController();
		}
		return memberController;
	}
	
	public ClubMembershipController requestMembershipController() {
		//
		if (membershipController == null) {
			membershipController = new ClubMembershipController();
		}
		return membershipController;
	}
	
	public BoardController requestBoardController() {
		//
		if (boardController == null) {
			boardController = new BoardController();
		}
		return boardController;
	}
	
	public PostingController requestPostingController() {
		//
		if (postingController == null) {
			postingController = new PostingController();
		}
		return postingController;
	}
	
	public TravelController requestTravelController() {
		//
		if (travelController == null) {
			travelController = new TravelController();
		}
		return travelController;
	}
}
