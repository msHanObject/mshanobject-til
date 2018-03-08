/*
 * COPYRIGHT (c) NEXTREE Consulting 2016
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2016. 7. 12.
 */
package javastory.club.service.dto;

import java.util.ArrayList;
import java.util.List;

import javastory.club.entity.Address;
import javastory.club.entity.ClubMembership;
import javastory.club.entity.CommunityMember;
import javastory.club.util.exception.InvalidEmailException;

public class MemberDto {
	//
	private String email; 
	private String name; 
	private String nickName; 
	private String phoneNumber; 
	private String birthDay;
	
	private List<Address> addresses; 
	private List<ClubMembershipDto> membershipList;
	
	private MemberDto() {
		//
		this.addresses = new ArrayList<Address>();
		this.membershipList = new ArrayList<ClubMembershipDto>();
	}
	
	public MemberDto(String email, String name, String nickName, String phoneNumber, String birthDay) {
		//
		this();
		this.email = email;
		this.name = name;
		this.nickName = nickName;
		this.phoneNumber = phoneNumber;
		this.birthDay = birthDay;
	}
	
	public MemberDto(CommunityMember member) {
		//
		this(member.getEmail(), member.getName(), member.getNickName(), member.getPhoneNumber(), member.getBirthDay());
		this.addresses = member.getAddresses();
		
		for (ClubMembership membership : member.getMembershipList()) {
			//
			membershipList.add(new ClubMembershipDto(membership));
		}
	}

	public CommunityMember toMember() throws InvalidEmailException {
		//
		CommunityMember member = new CommunityMember(email, name, nickName, phoneNumber, birthDay);
		
		for (Address address : addresses) {
			member.getAddresses().add(address);
		}
		
		for (ClubMembershipDto membershipDto : membershipList) {
			member.getMembershipList().add(membershipDto.toMembership());
		}
		return member;
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
		
		int i=0; 
		for(ClubMembershipDto membership : membershipList) {
			builder.append(" ["+ i +"] Club member ").append(membership.toString()).append("\n");
			i++;
		}
		
		return builder.toString(); 
	}
	
	public static MemberDto getSample() {
		// 
		MemberDto member = null;
		try {
			member = new MemberDto("mymy@nextree.co.kr", "Minsoo Lee", "ML","010-3321-1001", "2001.09.23");
			member.getAddresses().add(Address.getHomeAddressSample());
		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
		return member; 
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public List<ClubMembershipDto> getMembershipList() {
		return membershipList;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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
}
