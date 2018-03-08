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
import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.util.FileDbWrapper;

public class BoardFile {
	//
	private static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("id", 0);
		keyIndexMap.put("name", 1);
		keyIndexMap.put("adminEmail", 2);
	}
	
	private FileDbWrapper boardFile;
	private FileDbWrapper boardTempFile;
	
	public BoardFile() {
		//
		this.boardFile = new FileDbWrapper(
				"board",
				FileConfig.getFileName("Board"),
				FileConfig.getDelimiter());
		this.boardTempFile = new FileDbWrapper(
				"board",
				FileConfig.getFileName("BoardTemp"),
				FileConfig.getDelimiter());
		
		this.boardFile.setKeyIndexMap(keyIndexMap);
		this.boardTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String boardId) {
		//
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = boardFile.requestReader();
			
			String line = null;
			while (true) {
				if ((line = reader.readLine()) == null) {
					break;
				}
				
				if (boardFile.hasValueOf("id", boardId, line)) {
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
	
	public void write(SocialBoard board) {
		//
		if (this.exists(board.getId())) {
			return;
		}
		
		FileWriter fileWriter;
		try {
			fileWriter = boardFile.requestFileWriter();
			fileWriter.write(convertToStr(board));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SocialBoard read(String boardId) {
		//
		SocialBoard board = null;
		BufferedReader reader = null;
		
		try {
			reader = boardFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (boardFile.hasValueOf("id", boardId, line)) {					
					board  = convertToBoard(line);
					break;
				}
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return board;
	}
	
	public SocialBoard readLast() {
		//
		String lastLine= null;
		BufferedReader reader = null;
		
		try {
			reader = boardFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				lastLine = line;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return convertToBoard(lastLine);
	}
	
	public List<SocialBoard> readByName(String name) {
		//
		List<SocialBoard> boards = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = boardFile.requestReader();
			String line = null;
			
			while((line = reader.readLine()) != null) {
				if (boardFile.hasValueOf("name", name, line)) {
					SocialBoard board = convertToBoard(line);
					boards.add(board);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boards;
	}

	public List<SocialBoard> readAll() {
		// 
		List<SocialBoard> boards = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = boardFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				SocialBoard board = convertToBoard(line);
				boards.add(board);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boards;
	}
	
	public List<SocialBoard> readByAdminEmail(String adminEmail) {
		//
		List<SocialBoard> boards = new ArrayList<>();
		BufferedReader reader = null;
		
		try {
			reader = boardFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (boardFile.hasValueOf("adminEmail", adminEmail, line)) {
					SocialBoard club = convertToBoard(line);
					boards.add(club);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return boards;
	}
	
	public void update(SocialBoard board) {
		//
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = boardFile.requestReader();
			writer = boardTempFile.requestPrintWriter();
			
			String line = null;
			String boardId = board.getId();
			while ((line = reader.readLine()) != null) {
				
				if (boardFile.hasValueOf("id", boardId, line)) {
					line = convertToStr(board);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!boardFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!boardTempFile.renameTo(boardFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String boardId) {
		//
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = boardFile.requestReader();
			writer = boardTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (boardFile.hasValueOf("id", boardId, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			
			writer.close();
			reader.close();
			
			if (!boardFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			if (!boardTempFile.renameTo(boardFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private SocialBoard convertToBoard(String readLine) {
		//
		return (SocialBoard) boardFile.convertTo(readLine, SocialBoard.class);
	}
	
	private String convertToStr(SocialBoard board) {
		//
		return boardFile.convertFrom(board);
	}
	
	public static void main(String[] args) {
		//
		BoardFile boardFile = new BoardFile();
		SocialBoard boardOne = SocialBoard.getSample(TravelClub.getSample(false));
		String boardId = boardOne.getId();
		
		boardFile.write(boardOne);
		SocialBoard readBoardOne  = boardFile.read(boardId);
		System.out.println("> read board: " + readBoardOne);
		
		readBoardOne.setName("NewNAme");
		boardFile.update(readBoardOne);
		List<SocialBoard> updatedBoardsOne = boardFile.readByAdminEmail(readBoardOne.getAdminEmail());
		
		System.out.println("> read board: " + updatedBoardsOne.get(0).toString());
	}
}