package javastory.util;

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
			
			String canonicalPath = (new File(".")).getCanonicalPath();
			String fileSeparator = System.getProperty("file.separator");
			
			StringBuilder builder = new StringBuilder();
//			builder.append(canonicalPath).append(fileSeparator).append("resource").append(fileSeparator);
			builder.append(canonicalPath).append(fileSeparator).append("src").append(fileSeparator).append("main").append(fileSeparator).append("resources").append(fileSeparator);
			
			if (typeFolderName != null) {
				builder.append(typeFolderName).append(fileSeparator);
			}
			builder.append(folderName);
			
			pathName = builder.toString();
			
			builder.append(fileSeparator).append(fileName);
			fullFileName = builder.toString();
			
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
