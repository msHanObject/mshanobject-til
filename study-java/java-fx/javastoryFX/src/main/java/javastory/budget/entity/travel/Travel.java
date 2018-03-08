package javastory.budget.entity.travel;

import javastory.budget.share.DatePair;
import javastory.budget.share.Socialian;
import javastory.util.DateUtil;

public class Travel {
	//
	private static final String DEFAULT_DELIMITER = "||";
	
	private String clubId;
	private String travelId;
	private String travelName;
	private Socialian leader;
	private DatePair travelTerm;
	private int participantsCount;
	private String memo;
	private String time;
	
	public Travel() {
		//
		leader = Socialian.getSample();
		travelTerm = DatePair.getSample();
		participantsCount = 0;
		memo = "";
		time = DateUtil.CurrentTime();
	}
	
	public Travel(String clubId, String travelId, String travelName) {
		//
		this();
		this.clubId = clubId;
		this.travelId = travelId;
		this.travelName = travelName;
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("Travel").append(DEFAULT_DELIMITER);
		builder.append("clubId").append(DEFAULT_DELIMITER);
		builder.append(getClubId()).append(DEFAULT_DELIMITER);
		builder.append("travelId").append(DEFAULT_DELIMITER);
		builder.append(getTravelId()).append(DEFAULT_DELIMITER);
		builder.append("travelName").append(DEFAULT_DELIMITER);
		builder.append(getTravelName()).append(DEFAULT_DELIMITER);
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
