package javastory.msClub2.stage3.step4.da.map;

import javastory.msClub2.stage3.step4.store.BoardStore;
import javastory.msClub2.stage3.stpe4.store.ClubStore;
import javastory.msClub2.stage3.step4.store.ClubStoreLycler;
import javastory.msClub2.stage3.step4.store.MemberStore;
import javastory.msClub2.stage3.step4.store.PostingStore;

public class ClubStoreMapLycler implements ClubStoreLycler {
	//
	private static ClubStoreLycler lycler;

	private ClubStoreMapLycler() {
	}

	public static ClubStoreLycler getInstance() {
		//
		if (lycler == null) {
			lycler = new ClubStoreLycler();
		}

		return lycler;
	}

	@Override
	public MemberStore requestMemberStore() {
		//
		return new MemberMapStore();
	}

	@Override
	public Clubstore requestClubStore() {
		//
		return new ClubMapStore();
	}

	@Override
	public BoardStore requestBoardStore() {
		//
		return new BoardMapStore();
	}

	@Override
	public PostingStore requestPostingStore() {
		//
		return new PostingMapStore();
	}
}
