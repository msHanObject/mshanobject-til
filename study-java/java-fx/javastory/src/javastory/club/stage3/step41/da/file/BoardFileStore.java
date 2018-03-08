package javastory.club.stage3.step41.da.file;

import java.util.List;
import java.util.NoSuchElementException;

import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step4.store.BoardStore;
import javastory.club.stage3.step4.util.MemberDuplicationException;
import javastory.club.stage3.step41.da.file.io.BoardFile;

public class BoardFileStore implements BoardStore{
	//
	private BoardFile boardFile;
	
	public BoardFileStore() {
		//
		this.boardFile = new BoardFile();
	}
	@Override
	public String create(SocialBoard board) {
		//
		if (boardFile.exists(board.getId())) {
			throw new MemberDuplicationException("Member already exists with email: " + board.getId());
		}
		
		boardFile.write(board);
		return board.getId();
	}

	@Override
	public SocialBoard retrieve(String boardId) {
		// 
		return boardFile.read(boardId);
	}

	@Override
	public List<SocialBoard> retrieveByName(String name) {
		// 
		return boardFile.readByName(name);
	}

	@Override
	public void update(SocialBoard board) {
		// 
		if (!boardFile.exists(board.getId())) {
			throw new NoSuchElementException("No such a eleement: " + board.getId());
		}
		
		boardFile.update(board);
	}

	@Override
	public void delete(String boardId) {
		// 
		boardFile.delete(boardId);
	}

	@Override
	public boolean exists(String boardId) {
		// 
		return boardFile.exists(boardId);
	}
	@Override
	public List<SocialBoard> retrieveAll() {
		// 
		return boardFile.readAll();
	}
}