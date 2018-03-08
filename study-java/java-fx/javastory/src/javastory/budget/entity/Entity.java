package javastory.budget.entity;

public class Entity {
	//
	private static int sequence;
	
	public Entity() {
		//
		sequence = 0;
	}
	
	public static String getSampleId() {
		//
		String id = ++sequence + "";
		return id;
	}
}
