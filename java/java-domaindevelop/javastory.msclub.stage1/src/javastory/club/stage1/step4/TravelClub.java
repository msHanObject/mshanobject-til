package javastory.club.stage1.step4;

public class TravelClub {
	//
	private String name;
	private String intro;

	public TravelClub() {
		//
	}

	public TravelClub(String name, String intro) {
		//
		this.name = name;
		this.intro = intro;
	}

	public String tellMeAboutYou() {
		//
		return "Name: " + name + ", Intro: " + intro;
	}

	public String getName() {
		return name;
	}

	public String getIntro() {
		return intro;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntro(String intro) {
		this.intro =  intro;
	}
}
