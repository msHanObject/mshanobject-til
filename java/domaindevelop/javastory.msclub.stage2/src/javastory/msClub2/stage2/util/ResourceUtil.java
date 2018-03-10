package javastory.msClub2.stage2.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtil {
	//

	public static File getFile(String typeFolderName, String folderName, String fileName) {
		//
		File resourceFile = null;

		try {
			String fullFileName;
			String pathName;

			String cannonicalPath = (new File(".")).getCanonicalPath();
			String fileSeparator = System.getProperty("file.separator");

			StringBuilder builder = new StringBuilder();
			builder.append(cannonicalPath).append(fileSeparator).append("resource").append(fileSeparator);

			if (typeFolderName != null) {
				builder.append(typeFolderName).append(fileSeparator);
			}
			builder.append(folderName);

			pathName = builder.toString();

			builder.append(fileSeparator).append(fileName);
			fulFileName = builder.toString();

			Path path = Paths.get(pathName);
			if (!Files.exists(path)) {
				Files.createDirectoreis(path);
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
