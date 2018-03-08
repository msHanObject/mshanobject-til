package javastory.club.stage1.step41;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javastory.club.stage1.step1.TravelClub;

public class ClubFileStorage implements ClubStorage {
	//
	private String fileName;
	private String tempFileName;
	private String delimiter;

	public ClubFileStorage() {
		//
		this.fileName = "ClubFileStorage.db";
		this.tempFileName = "TravelClubTempFile.txt";
		this.delimiter = "||";
	}

	@Override
	public int count() {
		//
		int count = 0;
		BufferedReader reader;

		try {
			reader = requestReader(fileName);

			while(true) {
				if(reader.readLine() == null) {
					break;
				}
				count++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public boolean exist(String name) {
		//
		boolean found = false;
		BufferedReader reader;

		try {
			reader = requestReader(fileName);

			String line = null;
			while(true) {
				if((line = reader.readLine()) == null) {
					break;
				}

				if(hasName(line, name)) {
					found = true;
					break;
				};
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return found;
	}

	@Override
	public String create(TravelClub club) {
		//
		if(this.exist(club.getName())) {
			return null;
		}

		FileWriter fileWriter;
		try {
			fileWriter = requestFileWriter(fileName);
			fileWriter.write(convertToStr(club));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return club.getName();
	}

	@Override
	public TravelClub retrieve(String name) {
		//
		TravelClub resultClub = null;
		BufferedReader reader;
		try {
			reader = requestReader(fileName);

			String line = null;
			while(true) {
				if((line = reader.readLine()) == null) {
					return null;
				}

				if (hasName(line, name)) {
					resultClub = convertToObject(line);
					break;
				};
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultClub;
	}


	@Override
	public Collection<TravelClub>






































