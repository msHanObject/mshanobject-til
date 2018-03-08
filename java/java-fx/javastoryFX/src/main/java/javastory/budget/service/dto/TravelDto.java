package javastory.budget.service.dto;

import javastory.budget.entity.travel.Travel;
import javastory.budget.share.DatePair;
import javastory.budget.share.Socialian;

public class TravelDto {
	//
	private static final String DEFAULT_DELIMITER = ", ";
	
	private String clubId;
	private String travelId;
	private String travelName;
	private Socialian leader;
	private DatePair travelTerm;
	private int participantsCount;
	private String memo;
	private String time;
	
	public TravelDto(String clubId, String travelId, String travelName) {
		this.clubId = clubId;
		this.travelId = travelId;
		this.travelName = travelName;
	}
	
	public TravelDto(Travel travelObject) {
		// 
		this.clubId = travelObject.getClubId();
		this.travelId = travelObject.getTravelId();
		this.travelName = travelObject.getTravelName();
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
		
		builder.append("Travel's clubId: ");
		builder.append(getClubId()).append(DEFAULT_DELIMITER);
		builder.append("travelId: ");
		builder.append(getTravelId()).append(DEFAULT_DELIMITER);
		builder.append("travelName: ");
		builder.append(getTravelName()).append(DEFAULT_DELIMITER);
		builder.append("leader: ");
		builder.append(leader).append(DEFAULT_DELIMITER);
		builder.append("travelTerm: ");
		builder.append(travelTerm).append(DEFAULT_DELIMITER);
		builder.append("participantsCount: ");
		builder.append(participantsCount).append(DEFAULT_DELIMITER);
		builder.append("memo: ");
		builder.append(memo).append(DEFAULT_DELIMITER);
		builder.append("modified time: ");
		builder.append(time);
		
		return builder.toString();
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}

	public Travel toTravel() {
		// 
		Travel travel = new Travel();
		travel.setClubId(clubId);
		travel.setTravelId(travelId);
		travel.setTravelName(travelName);
		if (this.leader != null) {
			travel.setLeader(this.leader);
		}
		if (this.travelTerm != null) {
			travel.setTravelTerm(this.travelTerm);
		}
		travel.setParticipantsCount(participantsCount);
		if (this.memo != null) {
			travel.setMemo(this.memo);
		}
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
