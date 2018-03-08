package javastory.budget.logic;

import javastory.budget.service.CashBookService;
import javastory.budget.service.ServiceLycler;
import javastory.budget.service.TranService;
import javastory.budget.service.TravelService;
import javastory.budget.service.YearlyDueService;
import javastory.club.stage3.step4.logic.BoardServiceLogic;
import javastory.club.stage3.step4.logic.ClubServiceLogic;
import javastory.club.stage3.step4.logic.MemberServiceLogic;
import javastory.club.stage3.step4.logic.PostingServiceLogic;
import javastory.club.stage3.step4.service.BoardService;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.PostingService;

public class ServiceLogicLycler implements ServiceLycler{
	//
	private static ServiceLogicLycler lycler;
	
	private TravelService travelService;
	private CashBookService cashBookService;
	private TranService tranService;
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
	public TranService createTranService() {
		// 
		if (tranService == null) {
			tranService = new TranServiceLogic();
		}
		return tranService;
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
