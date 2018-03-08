package javastory.budget.service.dto;

import javastory.budget.entity.travel.Travel;
import javastory.budget.share.DatePair;
import javastory.budget.share.IdName;
import javastory.budget.share.Socialian;

public class TravelDto {
	//
	private static final String DEFAULT_DELIMITER = "||";
	
	private IdName club;
	private IdName travel;
	private Socialian leader;
	private DatePair travelTerm;
	private int participantsCount;
	private String memo;
	private String time;
	
	public TravelDto(IdName club, IdName travel) {
		this.club = club;
		this.travel = travel;
	}

	public TravelDto(Travel travelObject) {
		// 
		this.club = travelObject.getClub();
		this.travel = travelObject.getTravel();
		this.leader = travelObject.getLeader();
		this.travelTerm = travelObject.getTravelTerm();
		this.participantsCount = travelObject.getParticipantsCount();
		this.memo = travelObject.getMemo();
		this.time = travelObject.getTime();
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("Travel").append(DEFAULT_DELIMITER);
		builder.append("clubId").append(DEFAULT_DELIMITER);
		builder.append(getClubId()).append(DEFAULT_DELIMITER);
		builder.append("clubName").append(DEFAULT_DELIMITER);
		builder.append(getClubName()).append(DEFAULT_DELIMITER);
		builder.append("travelId").append(DEFAULT_DELIMITER);
		builder.append(getId()).append(DEFAULT_DELIMITER);
		builder.append("travelName").append(DEFAULT_DELIMITER);
		builder.append(getName()).append(DEFAULT_DELIMITER);
		builder.append("leader").append(DEFAULT_DELIMITER);
		builder.append(leader).append(DEFAULT_DELIMITER);
		builder.append("travelTerm").append(DEFAULT_DELIMITER);
		builder.append(travelTerm).append(DEFAULT_DELIMITER);
		builder.append("participantsCount").append(DEFAULT_DELIMITER);
		builder.append(participantsCount).append(DEFAULT_DELIMITER);
		builder.append("memo").append(DEFAULT_DELIMITER);
		builder.append(memo).append(DEFAULT_DELIMITER);
		builder.append("modified time").append(DEFAULT_DELIMITER);
		builder.append(time).append(DEFAULT_DELIMITER);
		
		return builder.toString();
	}
		
	public String getId() {
		//
		return travel.getId();
	}
	
	public String getName() {
		//
		return travel.getName();
	}
	
	public String getClubId() {
		//
		return club.getId();
	}
	
	public String getClubName() {
		//
		return club.getName();
	}
	
	public void setClubId(String id) {
		//
		this.club.setId(id);
	}
	
	public void setClubName(String name) {
		//
		this.club.setName(name);
	}
	
	public void setTravelId(String id) {
		//
		this.travel.setId(id);
	}
	
	public void setTravelName(String name) {
		//
		travel.setName(name);
	}
	
	public IdName getClub() {
		return club;
	}

	public void setClub(IdName club) {
		this.club = club;
	}

	public IdName getTravel() {
		return travel;
	}

	public void setTravel(IdName travel) {
		this.travel = travel;
	}

	public Travel toTravel() {
		// 
		Travel travel = new Travel();
		travel.setClub(this.club);
		travel.setTravel(this.travel);
		
		return travel;
	}

	public Socialian getLeader() {
		return leader;
	}

	public void setLeader(Socialian leader) {
		this.leader = leader;
	}

	public DatePair getTravelTerm() {
		return travelTerm;
	}

	public void setTravelTerm(DatePair travelTerm) {
		this.travelTerm = travelTerm;
	}

	public int getParticipantsCount() {
		return participantsCount;
	}

	public void setParticipantsCount(int participantsCount) {
		this.participantsCount = participantsCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
