package javastory.budget.ui.console;

import java.util.List;

import javastory.budget.entity.Entity;
import javastory.budget.logic.ServiceLogicLycler;
import javastory.budget.service.TravelService;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.share.IdName;
import javastory.budget.share.Socialian;
import javastory.budget.util.ConsoleUtil;
import javastory.budget.util.NoSuchTravelException;
import javastory.budget.util.TravelDuplicationException;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.util.NoSuchClubException;
import javastory.club.stage3.util.DateUtil;

public class TravelConsole {
	//
	private ConsoleUtil consoleUtil;
	private TravelService travelService;
	private ClubService clubService;
	
	public TravelConsole() {
		//
		consoleUtil = new ConsoleUtil();
		travelService = ServiceLogicLycler.shareInstance().createTravelService();
		clubService = ServiceLogicLycler.shareInstance().createClubService();
	}
	
	public boolean exists(String travelId) {
		//
		return travelService.exist(travelId);
	}
	
	public void create() {
		//
		while (true) {
			//
			String clubId = consoleUtil.getValueOf("Club Id to connect(0.Travel menu)");
			if (clubId.equals("0")) {
				return;
			}
			if (clubId.isEmpty()) {
				System.out.println("Club id can't be left blank.");
			}
			
			TravelClubDto clubDto;
			try {
				clubDto = clubService.findClub(clubId);	
				System.out.println("Found club: " + clubDto.toString());
			} catch (NoSuchClubException e) {
				System.out.println("No such club with id --> " + clubId);
				return;
			}
			
			String clubName = clubDto.getName();					
			IdName club = new IdName(clubId, clubName);
			
			String travelId = consoleUtil.getValueOf("Travel Id(0.Travel menu) [Enter to AutoId]");
			if (travelId.equals("0")) {
				return;
			}
			if (travelId.isEmpty()) {
				travelId = Entity.getSampleId();
			}
			
			String travelName = consoleUtil.getValueOf("Travel Name(0.Travel menu)");
			if (travelName.equals("0")) {
				return;
			}
			if (travelName.isEmpty()) {
				System.out.println("Travel name can't be left blank.");
				return;
			}
			
			IdName travel = new IdName(travelId, travelName);
			
			try {
				//
				TravelDto currentTravel = new TravelDto(club, travel);
				travelService.createTravel(currentTravel);
				System.out.println("Created travel: " + currentTravel.toString());
			} catch (TravelDuplicationException e) {
				//
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void register(TravelDto currentTravel) {
		// 
		try {			
			travelService.createTravel(currentTravel);
		} catch (TravelDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void find() {
		//
		TravelDto travelFound = null;
		while (true)	{
			String travelId = consoleUtil.getValueOf("Travel id to find(0.Travel menu)");
			if (travelId.equals("0")) {
				break;
			}
			if (travelId.isEmpty()) {
				System.out.println("Travel id can't be left blank.");
				return;
			}
			try {
				travelFound = travelService.findTravel(travelId);
				System.out.println("Found travel: " + travelFound.toString());			
			} catch (NoSuchTravelException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private TravelDto findOne() {
		//
		TravelDto travelFound = null;
		while (true) {
			String travelId = consoleUtil.getValueOf("Travel id to find(0.Travel menu)");
			if (travelId.equals("0")) {
				break;
			}
			if (travelId.isEmpty()) {
				System.out.println("Travel id can't be left blank.");
				break;
			}
			
			try {
				travelFound = travelService.findTravel(travelId);
				System.out.println("Found travel: " + travelFound.toString());
				break;
			} catch (NoSuchTravelException e) {
				System.out.println(e.getMessage());
			}
		}
		return travelFound;
	}

	public List<TravelDto> searchByName(String travelName) {
		// 
		List<TravelDto> travelDtos = null;
		try {
			travelDtos = travelService.findTravelByName(travelName);
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
		return travelDtos;
	}

	public List<TravelDto> findAll() {
		//
		List<TravelDto> travelDtos = null;
		try {
			travelDtos = travelService.findAll();
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
		return travelDtos;
	}

	public void modify() {
		//
		TravelDto targetTravel = findOne();
		
		String travelName = consoleUtil.getValueOf("Travel name to modify(0.Travel menu)");
		if (travelName.equals("0")) {
			return;
		}
		if (travelName.isEmpty()) {
			System.out.println("Travel name can't be left blank.");
			return;
		}
		targetTravel.setTravelName(travelName);
		
		String confirmModifyLeader= consoleUtil.getValueOf("Do you want modify leader? (Yes: y, No: n)");
		if (confirmModifyLeader.toLowerCase().equals("y") || confirmModifyLeader.toLowerCase().equals("yes")) {
			String socialId = consoleUtil.getValueOf("SocialId");
			String firstName = consoleUtil.getValueOf("FirstName");
			String familyName = consoleUtil.getValueOf("FamilyName");
			String email = consoleUtil.getValueOf("Email");
			
			Socialian leader = new Socialian(socialId, firstName, familyName, email);
			targetTravel.setLeader(leader);
		}
		if (confirmModifyLeader.toLowerCase().equals("n") || confirmModifyLeader.toLowerCase().equals("no")) {
			System.out.println("The leader is maintained.");
		}
		
		int participantsCount = consoleUtil.getIntegerOf("Participants count");
		String memo = consoleUtil.getValueOf("Memo");
		String modifiedTime = DateUtil.CurrentTime();
		
		targetTravel.setParticipantsCount(participantsCount);
		targetTravel.setMemo(memo);
		targetTravel.setTime(modifiedTime);
		
		try {			
			travelService.modify(targetTravel);
			System.out.println("Modified travel: " + targetTravel.toString());
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void modify(TravelDto currentTravel) {
		// 
		try {
			travelService.modify(currentTravel);
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void delete() {
		//
		TravelDto targetTravel = findOne();
		if (targetTravel == null) {
			return;
		}
		
		String travelId = targetTravel.getId();
		if (travelId.isEmpty()) {
			return;
		}
		try {			
			travelService.delete(travelId);
			System.out.println("Deleted travel: " + targetTravel.toString());
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(TravelDto currentTravel) {
		// 
		try {
			travelService.delete(currentTravel.getId());
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
	}
}