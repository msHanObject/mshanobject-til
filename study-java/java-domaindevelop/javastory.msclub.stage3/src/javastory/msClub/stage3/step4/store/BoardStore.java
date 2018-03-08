package javastory.msClub.stage3.step4.store;

import java.util.List;

import javastory.msClub.stage3.step1.entity.board.SocialBoard;

public interface BaordStore {
	//
	public String create(SocialBoard board);
	public SocialBoard retrieve(String boardId);
	public List<SocialBoard> retrieveByName(String boardId);
	public void update(SocialBoard board);
	public void delete(String boardId);

	public boolean exists(String boardId);
}
