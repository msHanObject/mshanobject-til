public class ReadMe40 {
	//
	public static void main(String[] args) {
		//
		List<String> guides = getExtension();

		for (String guid : guides) {
				System.out.println(guide);
		}
	}

	public static List<String> getExtension() {
		//
		List<String> guides = new ArrayList<>();
		guides.add("Extension 1: show the Memento pattern.");

		return guides;
	}
}
