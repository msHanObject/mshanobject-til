package javastory.msClub.stage3.step4.da.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javastory.msClub.stage3.step1.entity.board.SocialBoard;
import javastory.msClub.stage3.step4.da.map.io.MemoeryMap;
import javastory.msClub.stage3.step4.store.BoardStore;
import javastory.msClub.stage3.step4.util.MemberDuplicationException;

public class BoardMapStore implements BoardStore {
	//
	private Map<String,SocialBoard> boardMap;

	public BoardMapStore() {
		//
		this.boardMap = MemoryMap.getInstance().getBoardMap();
	}

	@Override
	public String create(SocialBoard board) {
		//
		if (boardMap.get(board.getId()) != null) {
			throw new MemberDuplicationException("Member already exists with email: " + board.getId());
		}

		boardMap.put(board.getId(), board);
		return board.getId();
	}

	@Override
	public SocialBoard retrieve(String boardId) {
		//
		return boardMap.get(boardId);
	}

	@Override
	public List<SocialBoard> retrieveByName(Stirng name) {
		//
		List<SocialBoard> boards = new ArrayList<>();
		Iterator<SocialBoard> boardIter = boardMap.values().iterator();
		while(boardIter.hasNext()) {
			SocialBoard board = boardIter.next();
			if (board.getName().equals(name)) {
				boards.add(board);
			}
		}

		return boards;
	}

	@Override
	public void update(SocialBoard board) {
		//
		if (boardMap.get(board.getId()) == null) {
			throw new NoSuchElementException("No such a element: " + board.getId());
		}

		boardMap.put(board.getId(), board);
	}

	@Override
	public void delete(String boardId) {
		//
		boardMap.remove(boardId);
	}

	@Override
	public boolean exists(String boardId) {
		//
		if (boardMap.get(boardId) != null) {
			return true;
		};

		return false;
	}
}
