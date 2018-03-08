public class FileConfig {
	//
	private static String DEFAULT_DELIMITER = "||";

	public static String getFileName(String target) {
		//
		return target + "File.db";
	}

	public static String getTempFileName(String target) {
		//
		return target + "TempFile.db";
	}

	public static String getDelimiter() {
		//
		return DEFAULT_DELIMITER;
	}
}
