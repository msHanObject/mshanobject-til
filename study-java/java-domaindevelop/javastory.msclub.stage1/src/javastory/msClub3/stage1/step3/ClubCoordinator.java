package javastory.msClub3.stage1.step3;

import java.util.Arrays;

import javastory.msClub3.stage1.step1.TravelClub;

public class ClubCoordinator {
	//
	private int capacity = 4; 	// constants not variable.
	private int nextIndex;
	private TravelClub[] clubs;

	public ClubCoordinator() {
		//
		this.nextIndex = 0;
		this.clubs = new TravelClub[capacity];
	}

	public boolean hasClubs() {
		//
		if (nextIndex != 0) {
			return true;
		}

		return false;
	}

	public boolean exist(String name) {
		//
		for (int i=0; i<nextIndex; i++) {
			if (clubs[i] != null && club[i].getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public String register(TravelClub newClub) {
		//
		if (this.exist(newClub.getName())) {
			return null;
		}

		// increase size
		if (nextIndex == clubs.length) {
			clubs = Arrays.copyOf(clubs, nextIndex + capacity);
		}

		clubs[nextIndex++] = newClub;

		return newClub.getName();
	}

	public TravelClub find(String name) {
		//
		for (int i=0; i<nextIndex; i++) {
			if (clubs[i].getName().equals(name)) {
				return clubs[i];

			}
		}
		return null;
	}

	public TravelClub[] findAll() {
		//
		return Arrays.copyOf(clubs, nextIndex);
	}

	public void modify(String name, String intro) {
		//
		if (!this.exist(name)) {
			return;
		}

		TraelClub club = this.find(name);
		club.setIntro(intro);
	}

	public void remove(String name) {
		//
		for (int i=0; i<nextIndex; i++) {
			if (clubs[i] != null && clubs[i].getName().equals(name)) {
				clubs[i] = null;
				removeBlankInClubs(i);
				break;
			}
		}
	}

	private void removeBlankInClubs(int blankIndex) {
		//
		int lastIndex = nextIndex-1;
		if (blankIndex == lastIndex) {
			return;
		}

		for (int i=blankIndex; i<lastIndex; i++) {
			clubs[i]  = clubs[i+1];
		}

		clubs[lastIndex] = null;
		nextIndex--;
	}
}

