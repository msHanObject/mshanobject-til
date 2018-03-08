package javastory.budget.share;

import java.util.Locale;

public class Socialian extends Entity {
	//
	private Locale locale; 
	private String socialId; 		// social security number 
	private String firstName; 	
	private String familyName; 
	private String email; 
	private String phone;			// optional  
	
	public Socialian() {
		// 
	} 
	
	public Socialian(String id) {
		// 
		super(id); 
	}
	 
	public Socialian(String socialId, 
			String firstName, 
			String familyName, 
			String email) {
		//
		super(); 
		this.locale = Locale.getDefault(); 
		this.socialId = socialId;  
		this.firstName = firstName; 
		this.familyName = familyName; 
		this.email = email; 
		this.phone = "n/a"; 
	}
	
	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("Socialian: ").append(getId());
		builder.append(", Locale: ").append(locale); 
		builder.append(", socialId:").append(socialId); 
		builder.append(", firstName:").append(firstName); 
		builder.append(", familyName:").append(familyName); 
		builder.append(", email:").append(email); 
		builder.append(", phone:").append(phone).append("]"); 
		
		return builder.toString(); 
	}

	public static Socialian getSample() {
		// 
		String socialId = "880909-2134595"; 
		String firstName = "Steve"; 
		String familyName = "Jobs";
		String email = "steve@gmail.com"; 
		
		Socialian sample = new Socialian(socialId, firstName, familyName, email); 
		sample.setPhone("+82 1091156435");
		
		return sample; 
	}
	
	public IdName getIdName() {
		// 
		return new IdName(getId(), getName()); 
	}
	
	public String getName() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		if(locale.getLanguage().equals(Locale.KOREA.getLanguage())) {
			builder.append(getFamilyName()).append(getFirstName()); 
		} else {
			builder.append(getFirstName()).append(" ").append(getFamilyName()); 
		}
		
		return builder.toString(); 
	}
	
	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public static void main(String[] args) {
		// 
		System.out.println(getSample());
	}	
}