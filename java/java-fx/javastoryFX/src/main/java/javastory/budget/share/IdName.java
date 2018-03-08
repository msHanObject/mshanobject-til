package javastory.budget.share;

public class IdName {
	//
	private static final String DEFAULT_DELIMITER ="||";
	
	private String id;
	private String name;
	
	public IdName(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static IdName getSampleClub() {
		// 
		String id = "SampelClubId";
		String name = "SampleClub";
		IdName club = new IdName(id, name);
		return club;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(id).append(DEFAULT_DELIMITER);
		builder.append(name).append(DEFAULT_DELIMITER);
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
