package javastory.club.stage3.step41.da.file;

import javastory.club.stage3.step4.store.BoardStore;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.store.ClubStoreLycler;
import javastory.club.stage3.step4.store.MemberStore;
import javastory.club.stage3.step4.store.PostingStore;

public class ClubStoreFileLycler implements ClubStoreLycler{
	//
	private static ClubStoreLycler instance;
	
	private ClubStoreFileLycler() {
		//
	}
	
	public synchronized static ClubStoreLycler shareInstance() {
		//
		if (instance == null) {
			instance = new ClubStoreFileLycler();
		}
		return instance;
	}
	@Override
	public MemberStore requestMemberStore() {
		// 
		return new MemberFileStore();
	}

	@Override
	public ClubStore requestClubStore() {
		// 
		return new ClubFileStore();
	}

	@Override
	public BoardStore requestBoardStore() {
		// 
		return new BoardFileStore();
	}

	@Override
	public PostingStore requestPostingStore() {
		// 
		return new PostingFileStore();
	}
}