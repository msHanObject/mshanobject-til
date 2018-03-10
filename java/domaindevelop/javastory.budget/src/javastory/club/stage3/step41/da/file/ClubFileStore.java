package javastory.club.stage3.step41.da.file;

import java.util.NoSuchElementException;

import javastory.club.stage3.step1.entity.AutoIdEntity;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.util.ClubDuplicationException;
import javastory.club.stage3.step41.da.file.io.AutoIdFile;
import javastory.club.stage3.step41.da.file.io.AutoIdSequence;
import javastory.club.stage3.step41.da.file.io.ClubFile;

public class ClubFileStore implements ClubStore{
	//
	private ClubFile clubFile;
	private AutoIdFile autoIdFile;
	
	public ClubFileStore() {
		//
		this.clubFile = new ClubFile();
		this.autoIdFile = new AutoIdFile();
	}
	
	@Override
	public String create(TravelClub club) {
		// 
		if (club instanceof AutoIdEntity) {
			String className = TravelClub.class.getSimpleName();
			
			AutoIdSequence autoIdSequence = autoIdFile.read(className);
			if (autoIdSequence == null) {
				autoIdFile.write(new AutoIdSequence(className));
			}
			club.setAutoId(String.format("%05d", autoIdSequence.nextSequence()));
			
			autoIdFile.update(autoIdSequence);
		}
		
		if (clubFile.exists(club.getId())) {
			throw new ClubDuplicationException("Club already exists with id: " + club.getId());
		}
		
		clubFile.write(club);
		return club.getId();
	}

	@Override
	public TravelClub retrieve(String clubId) {
		// 
		return clubFile.read(clubId);
	}

	@Override
	public TravelClub retrieveByName(String name) {
		// 
		return clubFile.readByName(name);
	}

	@Override
	public void update(TravelClub club) {
		// 
		if (!clubFile.exists(club.getId())) {
			throw new NoSuchElementException("No such a element: " + club.getId());
		}
		
		clubFile.update(club);
	}

	@Override
	public void delete(String clubId) {
		// 
		clubFile.delete(clubId);
	}

	@Override
	public boolean exists(String clubId) {
		// 
		return clubFile.exists(clubId);
	}
	
	public static void main(String[] args) {
		//
		ClubFileStore store = new ClubFileStore();
		TravelClub club = TravelClub.getSample(false);
		String clubId = store.create(club);
		
		TravelClub readClub = store.retrieve(clubId);
		System.out.println("read club: " + readClub.toString());
	}	
}