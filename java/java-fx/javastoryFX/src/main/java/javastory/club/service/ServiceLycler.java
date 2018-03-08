package javastory.club.service;

import javastory.budget.service.CashBookService;
import javastory.budget.service.TravelService;
import javastory.budget.service.YearlyDueService;

public interface ServiceLycler {
	//
	public TravelService createTravelService();
	public CashBookService createCashBookService();
	public YearlyDueService createYearlyDueService();
	//
	public ClubService createClubService();
	public MemberService createMemberService();
	public BoardService createBoardService();
	public PostingService createPostingService();
}
