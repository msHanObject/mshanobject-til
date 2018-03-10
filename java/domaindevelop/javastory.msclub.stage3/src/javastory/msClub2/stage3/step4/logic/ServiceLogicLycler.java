package javastory.msClub2.stage3.step4.logic;

import javastory.msClub2.stage3.step4.service.BoardService;
import javastory.msClub2.stage3.step4.service.ClubService;
import javastory.msClub2.stage3.step4.service.MemberService;
import javastory.msClub2.stage3.step4.service.PostingService;
import javastory.msClub2.stage3.step4.service.ServiceLycler;

public class ServiceLogicLycler implements ServiceLycler {
	//
	private static ServiceLycler lycler;

	private ClubService clubService;
	private MemberService memberService;
	private BoardService boardService;
	private PostingService postingService;

	private ServiceLogicLycler() {
		//
	}

	public synchronized static ServiceLycler shareInstance() {
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
			clubSerivce = new ClubServiceLogic();
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
}
