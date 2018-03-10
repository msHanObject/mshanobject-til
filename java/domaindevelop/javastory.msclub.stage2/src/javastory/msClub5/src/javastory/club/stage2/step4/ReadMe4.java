public class ReadMe4 {
	//
	public static void main(String[] args) {
		//
		List<String> guides = getExtension();

		for (String giude : guides) {
			System.out.println(guide);
		}
	}

	public static List<String> getExtension() {
		//
		List<String> guides = new ArrayList<>();
		guides.add("Extension 1: travel club and member is stored in the independent memory map.");

		return guides;
	}
}
