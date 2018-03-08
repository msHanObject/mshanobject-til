package javastory.budget.da.file.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javastory.budget.entity.account.YearlyDue;
import javastory.club.stage3.util.FileDbWrapper;

public class YearlyDueFile {
	//
	private static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap =  new LinkedHashMap<String,Integer>();
		keyIndexMap.put("year", 0);
	}
	
	private FileDbWrapper yearlyDueFile;
	private FileDbWrapper yearlyDueTempFile;
	
	public YearlyDueFile() {
		//
		this.yearlyDueFile = new FileDbWrapper(
				"yearlydue",
				FileConfig.getFileName("YearlyDue"),
				FileConfig.getDelimiter());
		this.yearlyDueTempFile = new FileDbWrapper(
				"yearlydue",
				FileConfig.getFileName("YearlyDueTemp"),
				FileConfig.getDelimiter());
		
		this.yearlyDueFile.setKeyIndexMap(keyIndexMap);
		this.yearlyDueTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String year) {
		// 
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = yearlyDueFile.requestReader();
			
			String line = null;
			while (true) {
				//
				if ((line = reader.readLine()) == null) {
					break;
				}
				
				if (yearlyDueFile.hasValueOf("year", year, line)) {
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

	public void write(YearlyDue yearlyDue) {
		// 
		if (this.exists(yearlyDue.getYear())) {
			return;
		}
		
		FileWriter fileWriter;
		try {
			fileWriter = yearlyDueFile.requestFileWriter();
			fileWriter.write(convertToStr(yearlyDue));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public YearlyDue read(String year) {
		// 
		YearlyDue yearlyDue = null;
		BufferedReader reader = null;
		
		try {
			reader = yearlyDueFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				//
				if (yearlyDueFile.hasValueOf("year", year, line)) {
					yearlyDue = convertToYearlyDue(line);
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return yearlyDue;
	}

	public void update(YearlyDue yearlyDue) {
		// 
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = yearlyDueFile.requestReader();
			writer = yearlyDueTempFile.requestPrintWriter();
			
			String line = null;
			String year = yearlyDue.getYear();
			
			while ((line = reader.readLine()) != null) {
				if (yearlyDueFile.hasValueOf("year", year, line)) {
					line = convertToStr(yearlyDue);
				}
				writer.println(line);
				writer.flush();
			}
			
			writer.close();
			reader.close();
			
			if (!yearlyDueFile.delete()) {
				System.out.println("Could not delete file.");
				return;
			}
			
			if (!yearlyDueTempFile.renameTo(yearlyDueFile)) {
				System.out.println("Could not rename file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String year) {
		// 
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = yearlyDueFile.requestReader();
			writer = yearlyDueTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (yearlyDueFile.hasValueOf("year", year, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!yearlyDueFile.delete()) {
				System.out.println("Could not delete file.");
				return;
			}
			
			if (!yearlyDueTempFile.renameTo(yearlyDueFile)) {
				System.out.println("Could not rename file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private YearlyDue convertToYearlyDue(String readLine) {
		//
		return (YearlyDue) yearlyDueFile.convertTo(readLine, YearlyDue.class);
	}
	
	private String convertToStr(YearlyDue yearlyDue) {
		//
		return yearlyDueFile.convertFrom(yearlyDue);
	}
}