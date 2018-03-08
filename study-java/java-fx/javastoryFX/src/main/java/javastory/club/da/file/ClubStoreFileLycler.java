package javastory.club.da.file;

import javastory.club.store.BoardStore;
import javastory.club.store.ClubStore;
import javastory.club.store.ClubStoreLycler;
import javastory.club.store.MemberStore;
import javastory.club.store.PostingStore;

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