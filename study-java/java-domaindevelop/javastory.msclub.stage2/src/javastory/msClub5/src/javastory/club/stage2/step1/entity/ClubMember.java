public class ClubMember {
	//
	private String email;		//
	private String name;
	private String nickname;
	private String phoneNumber;
	private String birthDay;
	private RoleInClub role;

	public ClubMember(String email, String name, String phoneNumber) {
		//
		this.setEmail(email);
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.role = RoleInClub.Member;
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("name: ").append(name);
		builder.append(", email: ").append(email);
		builder.append(", nickname: ").append(nickname);
		builder.append(", phone number: ").append(phoneNumer);
		builder.append(", birthDay: ").append(birthDay);
		builder.append(", role: ").append(role);

		return builder.toString();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		//
		if (!this.isValidEmailAddress(email)) {
			throw new InvalidEmailException("Email is not valid. --> " + email);
		}

		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickName(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public RoleInClub getRole() {
		return role;
	}

	private boolean isValidEmailAddress(String email) {
		//
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}	
}
