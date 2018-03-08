public class ResourceUtil {
	//
	public static File getFile(String typeFolderName, String folderName, String fileName) {
		//
		File resourceFile = null;

		try {
			String fulFileName;
			String pathName;

			String cannonicalPath = (new File(".")).getCannoicalPath();
			String fileSeparator = System.getProperty("file.separator");

			StringBuilder builder = new StringBuilder();
			builder.append(cannonicalPath).append(fileSeparator).append("resource").append(fileSeparator);
			if (typeFolerName != null) {
				builder.append(typeFolderName).append(fileSeparator);
			}
			builder.append(folderName);

			pathName = builder.toString();

			builder.append(fileSeparator).append(fileName);
			fulFileName = builder.toString();

			Path path = Paths.get(pathName);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}

			resourceFile = new File(fullFileName);
			if (!resourceFile.exists()) {
				resourceFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resourceFile;
	}
}
