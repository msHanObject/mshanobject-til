package javastory.club.da.file;

import java.util.List;
import java.util.NoSuchElementException;

import javastory.club.da.file.io.AutoIdFile;
import javastory.club.da.file.io.AutoIdSequence;
import javastory.club.da.file.io.PostingFile;
import javastory.club.entity.board.Posting;
import javastory.club.store.PostingStore;
import javastory.club.util.exception.PostingDuplicationException;
import javastory.entity.AutoIdEntity;

public class PostingFileStore implements PostingStore{
	//
	private PostingFile postingFile;
	private AutoIdFile autoIdFile;
	
	public PostingFileStore() {
		//
		this.postingFile = new PostingFile();
		this.autoIdFile = new AutoIdFile();
	}
	
	@Override
	public String create(Posting posting) {
		//
		if (posting instanceof AutoIdEntity) {
			String className = Posting.class.getSimpleName();
			
			AutoIdSequence autoIdSequence = autoIdFile.read(className);
			if (autoIdSequence == null) {
				autoIdFile.write(new AutoIdSequence(className));
			}
			posting.setAutoId(String.format("%05d", autoIdSequence.nextSequence()));
			
			autoIdFile.update(autoIdSequence);
		}
		
		if (postingFile.exists(posting.getId())) {
			throw new PostingDuplicationException("Already exists: " + posting.getId());
		}
		
		postingFile.write(posting);
		return posting.getId();
	}
	
	@Override
	public Posting retrieve(String postingId) {
		//
		return postingFile.read(postingId);
	}
	
	@Override
	public List<Posting> retrieveByBoardId(String boardId) {
		//
		return postingFile.readByBoardId(boardId);
	}
	
	@Override
	public List<Posting> retrieveByTitle(String title) {
		// 
		return postingFile.readByTitle(title);
	}
	
	@Override
	public void update(Posting posting) {
		// 
		if (!postingFile.exists(posting.getId())) {
			throw new NoSuchElementException("No such a element: " + posting.getId());
		}
		
		postingFile.update(posting);
	}
	
	@Override
	public void delete(String postingId) {
		// 
		postingFile.delete(postingId);
	}
	
	@Override
	public boolean exists(String postingId) {
		// 
		return postingFile.exists(postingId);
	}
}