public class ClubStoreMapLycler implements ClubStoreLycler {
	//
	private static ClubStoreLycler lycler;

	private ClubStoreMapLycler() {
	}

	public static ClubStoreLycler getInstance() {
		//
		if (lycler == null) {
			lycler = new ClubStoreMapLycler();
		}

		return lycler;
	}

	@Override
	public MemberStore requestMemberStore() {
		//
		return new MemberMapStore();
	}

	@Override
	public ClubStore requestClubStore() {
		//
		return new ClubMapStore();
	}

	@Override
	public BoardStore requestBoardStore() {
		//
		return new BoardMapStore();
	}

	@Overrdie
	public PostingStore requestPostingStore() {
		//
		return new PostingMapStore();
	}
}
