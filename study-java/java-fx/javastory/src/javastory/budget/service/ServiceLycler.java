package javastory.budget.service;

import javastory.club.stage3.step4.service.BoardService;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.PostingService;

public interface ServiceLycler {
	//
	public TravelService createTravelService();
	public CashBookService createCashBookService();
	public TranService createTranService();
	public YearlyDueService createYearlyDueService();
	//
	public ClubService createClubService();
	public MemberService createMemberService();
	public BoardService createBoardService();
	public PostingService createPostingService();
}
