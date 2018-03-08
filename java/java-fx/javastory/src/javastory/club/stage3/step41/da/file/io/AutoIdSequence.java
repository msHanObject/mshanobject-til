package javastory.club.stage3.step41.da.file.io;

import javastory.club.stage3.step1.entity.club.TravelClub;

public class AutoIdSequence {
	//
	private String className;
	private int sequence;
	
	public AutoIdSequence(String className) {
		//
		this.className = className;
		this.sequence = 1;
	}
	
	@Override
	public String toString() {
		//
		StringBuilder  builder = new StringBuilder();
		
		builder.append("class name: ").append(className);
		builder.append(", sequence: ").append(sequence);
		
		return builder.toString();
	}
	
	public static AutoIdSequence getSample() {
		//
		AutoIdSequence autoIdSequence = new AutoIdSequence(TravelClub.class.getSimpleName());
		autoIdSequence.nextSequence();
		
		return autoIdSequence;
	}
	
	public String getClassName() {
		return className;
	}
	
	public int nextSequence() {
		return sequence++;
	}
}
