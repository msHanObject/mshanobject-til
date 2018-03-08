package javastory.club.entity;

import java.util.ArrayList;
import java.util.List;

import javastory.club.util.exception.InvalidEmailException;
import javastory.entity.Entity;

public class CommunityMember implements Entity {
	//
	private String email;		// key 
	private String name; 
	private String nickName; 
	private String phoneNumber; 
	private String birthDay; 

	private List<Address> addresses; 
	private List<ClubMembership> membershipList; 
	
	public CommunityMember() {
		// 
		this.membershipList = new ArrayList<ClubMembership>(); 
		this.addresses = new ArrayList<Address>(); 
	}
	
	public CommunityMember(String email, String name, String nickName, String phoneNumber, String birthDay) throws InvalidEmailException {
		// 
		this(); 
		setEmail(email);
		this.name = name;
		this.nickName =  nickName;
		this.phoneNumber = phoneNumber;
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("Name:").append(name); 
		builder.append(", email:").append(email); 
		builder.append(", nickname:").append(nickName); 
		builder.append(", phone number:").append(phoneNumber); 
		builder.append(", birthDay:").append(birthDay); 

		if (addresses != null) {
			int i = 1; 
			for(Address address : addresses) {
				builder.append(", Address[" + i + "]").append(address.toString()); 
			}
		}
		
		return builder.toString(); 
	}
	
	public static CommunityMember getSample() {
		// 
		CommunityMember member = null;
		try {
			member = new CommunityMember("mymy@nextree.co.kr", "Minsoo Lee", "ML", "010-3321-1001", "2001.09.23");
			member.getAddresses().add(Address.getHomeAddressSample());
		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
		return member; 
	}

	public String getId() {
		return email; 
	}

	public List<ClubMembership> getMembershipList() {
		return this.membershipList; 
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidEmailException {
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

	public String getNickName() {
		return nickName;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

    private boolean isValidEmailAddress(String email) {
    	//
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
