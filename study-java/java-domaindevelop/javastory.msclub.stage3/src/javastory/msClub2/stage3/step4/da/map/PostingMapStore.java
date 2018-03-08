package javastory.msClub2.stage3.step4.da.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javastory.msClub2.stage3.step1.entity.board.Posting;
import javastory.msClub2.stage3.step4.da.map.io.MemoryMap;
import javastory.msClub2.stage3.step4.store.PostingStore;
import javastory.msClub2.stage3.step4.util.MemberDuplicaitonException;

public class PostingMapStore implements PostingStore {
	//
	private Map<String,Posting> postingMap;

	public PostingMapStore() {
		//
		this.postingMap = MemoryMap.getInstance().getPostingMap();
	}

	@Override
	public String create(Posting posting) {
		//
		if (postingMap.get(posting.getId()) != null) {
			throw new MemberDuplicationException("Already exists: " + posting.getId());
		}

		postingMap.put(posting.getId(), posting);
		return posting.getId();
	}

	@Override
	public Posting retrieve(String postingId) {
		//
		return postingMap.get(postingId);
	}

	@Override
	public List<Posting> retrieveByBoardId(String boardId) {
		//
		List<Posting> postings = new ArrayList<Posting>();
		Iterator<Posting> postingIter = postingMap.values().iterator();

		while (postingIter.hasNext()) {
			Posting posting = postingIter.next();
			if (posting.getBoardId().equals(boardId)) {
				postings.add(posting);
			}
		}

		return postings;
	}

	@Override
	public List<Posting> retrieveByTitle(String title) {
		//
		List<Posting> postings = new ArrayList<Posting>();
		Iterator<Posting> postingIter = postingMap.values().iterator();
		
		while(postingIter.hasNext()) {
			Posting posting = postingIter.next();
			if (posting.getTitle().equals(title)) {
				postings.add(posting);
			}
		}

		return postings;
	}

	@Override
	public void update(Posting posting) {
		//
		if (postingMap.get(posting.getId()) == null) {
			throw new NoSuchElementException("No such a element: " + posting.getId());
		}

		postingMap.put(posting.getId(),posting);
	}

	@Override
	public void delete(String postingId) {
		//
		postingMap.remove(postingId);
	}

	@Override
	public boolean exists(String postingId) {
		//
		if (postingMap.get(postingId) != null) {
			return true;
		}

		return false;
	}
}
