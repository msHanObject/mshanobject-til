package javastory.club.stage3.step41.da.file.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javastory.budget.da.file.io.FileConfig;
import javastory.club.stage3.step1.entity.club.Address;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.util.FileDbWrapper;

public class MemberFile {
	//
	private static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("id", 0);
		keyIndexMap.put("name", 1);
	}
	
	private FileDbWrapper memberFile;
	private FileDbWrapper memberTempFile;
	
	public MemberFile() {
		//
		this.memberFile = new FileDbWrapper(
				"member",
				FileConfig.getFileName("Member"),
				FileConfig.getDelimiter());
		this.memberTempFile = new FileDbWrapper(
				"member",
				FileConfig.getFileName("MemberTemp"),
				FileConfig.getDelimiter());
		
		this.memberFile.setKeyIndexMap(keyIndexMap);
		this.memberTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String memberId) {
		//
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = memberFile.requestReader();
			
			String line = null;
			while (true) {
				if ((line = reader.readLine()) == null) {
					break;
				}
				
				if (memberFile.hasValueOf("id", memberId, line)) {
					found = true;
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return found;
	}
	
	public void write(CommunityMember member) {
		//
		if (this.exists(member.getId())) {
			return;
		}
		
		FileWriter fileWriter;
		
		try {
			fileWriter  = memberFile.requestFileWriter();
			fileWriter.write(convertToStr(member));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CommunityMember read(String memberId) {
		//
		CommunityMember member = null;
		BufferedReader reader = null;
		
		try {
			reader = memberFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (memberFile.hasValueOf("id", memberId, line)) {
					member = convertToMember(line);
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	public List<CommunityMember> readByName(String name) {
		//
		List<CommunityMember> members = new ArrayList<CommunityMember>();
		BufferedReader reader = null;
		
		try {
			reader = memberFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (memberFile.hasValueOf("name", name, line)) {
					CommunityMember member = convertToMember(line);
					members.add(member);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return members;
	}
	
	public void update(CommunityMember member) {
		//
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = memberFile.requestReader();
			writer = memberTempFile.requestPrintWriter();
			
			String line = null;
			String memberId = member.getId();
			while ((line = reader.readLine()) != null) {
				
				if (memberFile.hasValueOf("id", memberId, line)) {
					line = convertToStr(member);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!memberFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!memberTempFile.renameTo(memberFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String memberId) {
		//
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = memberFile.requestReader();
			writer = memberTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (memberFile.hasValueOf("id", memberId, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!memberFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!memberTempFile.renameTo(memberFile)) {
				System.out.println("Could not rename file");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private CommunityMember convertToMember(String readLine) {
		//
		return (CommunityMember) memberFile.convertTo(readLine, CommunityMember.class);
	}
	
	private String convertToStr(CommunityMember member) {
		//
		return memberFile.convertFrom(member);
	}
	
	public static void main(String[] args) {
		//
		MemberFile memberFileDb = new MemberFile();
		CommunityMember memberOne = CommunityMember.getSample();
		String memberEmail1 = memberOne.getEmail();
		
		memberFileDb.write(memberOne);
		CommunityMember readMemberOne = memberFileDb.read(memberEmail1);
		System.out.println(" > read member 1: " + readMemberOne);
		
		try {
			CommunityMember memberTwo = new CommunityMember("second@nextree.co.kr", "Second Kim", "010-1234-1234");
			memberTwo.setBirthDay("1999.09.23");
			memberTwo.getAddresses().add(Address.getHomeAddressSample());
			memberFileDb.write(memberTwo);
			CommunityMember readMemberTwo = memberFileDb.read(memberTwo.getId());
			System.out.println("> read member 2: " +  readMemberTwo.toString());
		} catch (InvalidEmailException e) { 
			System.out.println(e.getMessage());
		}
	}

	public List<CommunityMember> readAll() {
		// 
		List<CommunityMember> memberList = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = memberFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				memberList.add(convertToMember(line));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return memberList;
	}
}