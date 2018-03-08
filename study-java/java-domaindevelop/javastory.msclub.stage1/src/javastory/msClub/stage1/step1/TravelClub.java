package javastory.msClub.stage1.step1;

public class TravelClub {

	private String name;
	private String intro;
	
	public TravelClub(String name, String intro) {
		//
		this.name = name;
		this.intro = intro;
	}
	public String tellMeAboutYou() {
		//
		String result ="Club Name: " + name + "Intro: " + intro;
		return result;
	}
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}
