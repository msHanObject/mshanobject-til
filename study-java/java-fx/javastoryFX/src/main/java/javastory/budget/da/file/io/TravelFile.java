package javastory.budget.da.file.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javastory.budget.entity.travel.Travel;
import javastory.club.da.file.io.FileConfig;
import javastory.util.FileDbWrapper;

public class TravelFile {
	//
	private static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("clubId", 0);
		keyIndexMap.put("travelId", 1);
		keyIndexMap.put("travelName", 2);
	}
	
	private FileDbWrapper travelFile;
	private FileDbWrapper travelTempFile;
	
	public TravelFile() {
		//
		this.travelFile = new FileDbWrapper(
				"travel",
				FileConfig.getFileName("Travel"),
				FileConfig.getDelimiter());
		this.travelTempFile = new FileDbWrapper(
				"travel",
				FileConfig.getFileName("TravelTemp"),
				FileConfig.getDelimiter());
		
		this.travelFile.setKeyIndexMap(keyIndexMap);
		this.travelTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String travelId) {
		//
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = travelFile.requestReader();
			
			String line = null;
			while (true) {
				if ((line = reader.readLine()) == null) {
					break;
				}
				
				if (travelFile.hasValueOf("travelId", travelId, line)) {
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
	
	public void write(Travel travel) {
		//
		if (this.exists(travel.getTravelId())) {
			return;
		}
		
		FileWriter fileWriter;
		try {
			fileWriter = travelFile.requestFileWriter();
			fileWriter.write(convertToStr(travel));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Travel read(String travelId) {
		//
		Travel travel = null;
		BufferedReader reader = null;
		
		try {
			reader = travelFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (travelFile.hasValueOf("travelId", travelId, line)) {
					travel = convertToTravel(line);
					break;
				}
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return travel;
	}
	
	public Travel readLast() {
		//
		String lastLine= null;
		BufferedReader reader = null;
		
		try {
			reader = travelFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				lastLine = line;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return convertToTravel(lastLine);
	}
	
	public List<Travel> readByName(String name) {
		//
		List<Travel> travels = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = travelFile.requestReader();
			String line = null;
			
			while((line = reader.readLine()) != null) {
				if (travelFile.hasValueOf("travelName", name, line)) {
					travels.add(convertToTravel(line));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return travels;
	}

	public List<Travel> readAll() {
		//
		List<Travel> travels = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = travelFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				travels.add(convertToTravel(line));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return travels;
	}
	
	public void update(Travel travel) {
		//
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = travelFile.requestReader();
			writer = travelTempFile.requestPrintWriter();
			
			String line = null;
			String travelId = travel.getTravelId();
			
			while ((line = reader.readLine()) != null) {
				if (travelFile.hasValueOf("travelId", travelId, line)) {
					line = convertToStr(travel);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!travelFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!travelTempFile.renameTo(travelFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String travelId) {
		//
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = travelFile.requestReader();
			writer = travelTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (travelFile.hasValueOf("travelId", travelId, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			
			writer.close();
			reader.close();
			
			if (!travelFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!travelTempFile.renameTo(travelFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Travel convertToTravel(String readLine) {
		//
		return (Travel) travelFile.convertTo(readLine, Travel.class);
	}
	
	private String convertToStr(Travel travel) {
		//
		return travelFile.convertFrom(travel);
	}

	public List<Travel> readByClubId(String clubId) {
		//
		List<Travel> travels = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = travelFile.requestReader();
			String line = null;
			
			while((line = reader.readLine()) != null) {
				if (travelFile.hasValueOf("clubId", clubId, line)) {
					travels.add(convertToTravel(line));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return travels;
	}
}