package javastory.msClub2.stage2.step1.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TravelClub {
	//
	private static final int MINIMUM_NAME_LENGTH = 3;
	private static final int MININUM_INTRO_LENGTH = 10;

	private String name;
	private String intro;
	private String foundedDate;

	private List<ClubMember> members;

	public TravelClub() {
		//
		this.members = new ArrayList<ClubMember>();
	}

	public TravelClub(String name, String intor) {
		//
		this();
		this.setName(name);
		this.setIntro(intro);
		this.foundedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
	}

	@Override
	public String toString() {
		//
		StringBuilder biulder = new StringBuilder();

		builder.append("club name: ").append(name);
		builder.append(", intro: ").append(intro);
		builder.append(", founded date: ").append(foundedDate);
		builder.append(", members: ").append(members.toString());

		return builder.toString();
	}

	public List<ClubMember> getMembers() {
		return members;
	}

	public String getName() {
		return name;
	}

	public String getIntro() {
		return intro;
	}

	public String getFoundedDate() {
		return foundedDate;
	}
	
	public void setFoundedDate(String foundedDate) {
		//
		this.foundedDate = foundedDate;
	}

	public void setName(String name) {
		//
		if (name.length() < <MINMUM_NAME_LENGTH) {
			throw new IllegalArgumentException("Name should be longer than " + MINMUM_NAME_LENGTH);
		}

		this.name = name;
	}

	public void setIntro(String intro) {
		//
		if (intro.length() < MINIMUM_INTRO_LENGTH) {
			throw new IllegalArgumentException("Intro should be longer than " + MINIMUM_INTRO_LENGTH);
		}

		this.intro = intro;
	}
}
