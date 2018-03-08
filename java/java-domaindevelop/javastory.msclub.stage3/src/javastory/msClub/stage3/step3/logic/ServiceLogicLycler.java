package javastory.msClub.stage3.step3.logic;

import javastory.msClub.stage3.step3.service.BoardService;
import javastory.msClub.stage3.step3.service.ClubService;
import javastory.msClub.stage3.step3.service.MemberService;
import javastory.msClub.stage3.step3.service.PostingService;
import javastory.msClub.stage3.step3.service.ServiceLyclear;

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
}
