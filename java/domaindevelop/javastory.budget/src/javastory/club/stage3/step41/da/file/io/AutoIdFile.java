package javastory.club.stage3.step41.da.file.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javastory.budget.da.file.io.FileConfig;
import javastory.club.stage3.util.FileDbWrapper;

public class AutoIdFile {
	//
	private static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("className", 0);
	}
	
	private FileDbWrapper autoIdFile;
	private FileDbWrapper autoIdTempFile;
	
	public AutoIdFile() {
		//
		this.autoIdFile = new FileDbWrapper(
				"autoid",
				FileConfig.getFileName("AutoId"),
				FileConfig.getDelimiter());
		this.autoIdTempFile = new FileDbWrapper(
				"autoid",
				FileConfig.getFileName("AutoIdTemp"),
				FileConfig.getDelimiter());
		
		this.autoIdFile.setKeyIndexMap(keyIndexMap);
		this.autoIdTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String className) {
		//
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = autoIdFile.requestReader();
			
			String line = null;
			while(true) {
				if ((line = reader.readLine()) == null) {
					break;
				}
				
				if (autoIdFile.hasValueOf("className", className, line)) {
					found = true;
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return found;
	}
	
	public void write(AutoIdSequence autoIdSequence) {
		//
		if (this.exists(autoIdSequence.getClassName())) {
			return;
		}
		
		FileWriter fileWriter;
		try {
			fileWriter = autoIdFile.requestFileWriter();
			fileWriter.write(convertToStr(autoIdSequence));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AutoIdSequence read(String className) {
		//
		AutoIdSequence autoIdSequence = null;
		BufferedReader reader = null;
		
		try {
			reader = autoIdFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (autoIdFile.hasValueOf("className", className, line)) {
					autoIdSequence = convertToClub(line);
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return autoIdSequence;
	}
	
	public void update(AutoIdSequence autoIdSequence) {
		//
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = autoIdFile.requestReader();
			writer = autoIdTempFile.requestPrintWriter();
			
			String line = null;
			String className = autoIdSequence.getClassName();
			while((line = reader.readLine()) != null) {
				if (autoIdFile.hasValueOf("className", className, line)) {
					line = convertToStr(autoIdSequence);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!autoIdFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!autoIdTempFile.renameTo(autoIdFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String className) {
		//
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = autoIdFile.requestReader();
			writer = autoIdTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (autoIdFile.hasValueOf("className", className, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			
			writer.close();
			reader.close();
			
			if (!autoIdFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!autoIdTempFile.renameTo(autoIdFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private AutoIdSequence convertToClub(String readLine) {
		//
		return (AutoIdSequence)autoIdFile.convertTo(readLine, AutoIdSequence.class);
	}
	
	private String convertToStr(AutoIdSequence autoIdSequence) {
		//
		return autoIdFile.convertFrom(autoIdSequence);
	}
	
	public static void main(String[] args) {
		//
		AutoIdFile autoIdFile = new AutoIdFile();
		
		AutoIdSequence autoIdSequence = AutoIdSequence.getSample();
		String className = autoIdSequence.getClassName();
		
		autoIdFile.write(autoIdSequence);
		
		AutoIdSequence readAutoId = autoIdFile.read(className);
		
		System.out.println(" > read auto id 1: " + readAutoId);
	}
}