package javastory.club.stage3.step41.da.file.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javastory.budget.da.file.io.FileConfig;
import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.util.FileDbWrapper;

public class PostingFile {
	//
	private static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("id", 0);
		keyIndexMap.put("title", 1);
		keyIndexMap.put("writerEmail", 2);
		keyIndexMap.put("boardId", 3);
	}
	
	private FileDbWrapper postingFile;
	private FileDbWrapper postingTempFile;
	
	public PostingFile() {
		//
		this.postingFile = new FileDbWrapper(
				"posting",
				FileConfig.getFileName("Posting"),
				FileConfig.getDelimiter());
		this.postingTempFile = new FileDbWrapper(
				"posting",
				FileConfig.getFileName("PostingTemp"),
				FileConfig.getDelimiter());
		
		this.postingFile.setKeyIndexMap(keyIndexMap);
		this.postingTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String postingId) {
		//
		
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = postingFile.requestReader();
			
			String line = null;
			while (true) {
				if ((line = reader.readLine()) == null) {
					break;
				}
				
				if (postingFile.hasValueOf("id", postingId, line)) {
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
	
	public void write(Posting posting) { 
		//
		if (this.exists(posting.getId())) {
			return;
		}
		
		FileWriter fileWriter;
		
		try {
			fileWriter = postingFile.requestFileWriter();
			fileWriter.write(convertToStr(posting));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Posting read(String postingId) {
		//
		Posting posting = null;
		BufferedReader reader = null;
		
		try {
			reader = postingFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (postingFile.hasValueOf("id", postingId, line)) {
					posting = convertToPosting(line);
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return posting;
	}
	
	public List<Posting> readByWriterEmail(String writerEmail){
		//
		List<Posting> postings = new ArrayList<Posting>();
		BufferedReader reader = null;
		
		try {
			reader = postingFile.requestReader();
			String line = null;
			
			while ((line =reader.readLine()) != null) {
				if (postingFile.hasValueOf("writerEmail", writerEmail, line)) {
					Posting club = convertToPosting(line);
					postings.add(club);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return postings;
	}
	
	public List<Posting> readByBoardId(String boardId) {
		//
		List<Posting> postings = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = postingFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (postingFile.hasValueOf("boardId", boardId, line)) {
					Posting club = convertToPosting(line);
					postings.add(club);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return postings;		
	}
	
	public List<Posting> readByTitle(String title) {
		//
		List<Posting> postings = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = postingFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (postingFile.hasValueOf("title", title, line)) {
					Posting club = convertToPosting(line);
					postings.add(club);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return postings;
	}
	
	public void update(Posting posting) {
		//
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = postingFile.requestReader();
			writer = postingTempFile.requestPrintWriter();
			
			String line = null;
			String postingId = posting.getId();
			while ((line = reader.readLine()) != null) {
				
				if (postingFile.hasValueOf("id", postingId, line)) {
					line = convertToStr(posting);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if(!postingFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!postingTempFile.renameTo(postingFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String postingId) {
		//
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = postingFile.requestReader();
			writer = postingTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (postingFile.hasValueOf("id", postingId, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			
			writer.close();
			reader.close();
			
			if (!postingFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!postingTempFile.renameTo(postingFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Posting convertToPosting(String readLine) {
		//
		return (Posting) postingFile.convertTo(readLine, Posting.class);
	}
	
	private String convertToStr(Posting posting) {
		//
		return postingFile.convertFrom(posting);
	}
	
	public static void main(String[] args) {
		//
		PostingFile boardFile = new PostingFile();
		List<Posting> postings = Posting.getSamples(SocialBoard.getSample(TravelClub.getSample(false)));
		
		for (Posting posting : postings) {
			String postingId = posting.getId();
			boardFile.write(posting);
			Posting readPosting = boardFile.read(postingId);
			System.out.println(" > read posting: " + readPosting);
		}
	}
}