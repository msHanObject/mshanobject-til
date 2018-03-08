package javastory.club.logic;

import javastory.budget.logic.CashBookServiceLogic;
import javastory.budget.logic.TravelServiceLogic;
import javastory.budget.logic.YearlyDueServiceLogic;
import javastory.budget.service.CashBookService;
import javastory.budget.service.TravelService;
import javastory.budget.service.YearlyDueService;
import javastory.club.service.BoardService;
import javastory.club.service.ClubService;
import javastory.club.service.MemberService;
import javastory.club.service.PostingService;
import javastory.club.service.ServiceLycler;

public class ServiceLogicLycler implements ServiceLycler{
	//
	private static ServiceLogicLycler lycler;
	
	private TravelService travelService;
	private CashBookService cashBookService;
	private YearlyDueService yearlyDueService;
	//
	private ClubService clubService;
	private MemberService memberService;
	private BoardService boardService;
	private PostingService postingService;
	
	public ServiceLogicLycler() {
		//
	}
	
	public synchronized static ServiceLogicLycler shareInstance() {
		//
		if (lycler == null) {
			lycler = new ServiceLogicLycler();
		}
		return lycler;
	}
	

	@Override
	public ClubService createClubService() {
		//
		if (clubService == null) {
			clubService = new ClubServiceLogic();
		}
		return clubService;
	}

	@Override
	public MemberService createMemberService() {
		//
		if (memberService == null) {
			memberService = new MemberServiceLogic();
		}
		return memberService;
	}

	@Override
	public BoardService createBoardService() {
		//
		if (boardService == null) {
			boardService = new BoardServiceLogic();
		}
		return boardService;
	}

	@Override
	public PostingService createPostingService() {
		//
		if (postingService == null) {
			postingService = new PostingServiceLogic();
		}
		return postingService;
	}
	
	@Override
	public TravelService createTravelService() {
		// 
		if (travelService == null) {
			travelService = new TravelServiceLogic();
		}
		return travelService;
	}
	
	@Override
	public CashBookService createCashBookService() {
		// 
		if (cashBookService == null) {
			cashBookService = new CashBookServiceLogic();
		}
		return cashBookService;
	}
	
	@Override
	public YearlyDueService createYearlyDueService() {
		// 
		if (yearlyDueService == null) {
			yearlyDueService = new YearlyDueServiceLogic();
		}
		return yearlyDueService;
	}
}
