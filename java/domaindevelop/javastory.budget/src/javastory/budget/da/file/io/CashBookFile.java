package javastory.budget.da.file.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javastory.budget.entity.budget.CashBook;
import javastory.club.stage3.util.FileDbWrapper;

public class CashBookFile {
	//
	private  static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("id", 0);
		keyIndexMap.put("name", 1);
	}
	
	private FileDbWrapper cashBookFile;
	private FileDbWrapper cashBookTempFile;
	
	public CashBookFile() {
		//
		this.cashBookFile = new FileDbWrapper(
				"cashbook",
				FileConfig.getFileName("CashBook"),
				FileConfig.getDelimiter());
		this.cashBookTempFile = new FileDbWrapper(
				"cashbook",
				FileConfig.getFileName("CashBookTemp"),
				FileConfig.getDelimiter());
		
		this.cashBookFile.setKeyIndexMap(keyIndexMap);
		this.cashBookTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String cashBookId) {
		// 
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = cashBookFile.requestReader();
			
			String line = null;
			while (true) {
				//
				if ((line = reader.readLine()) == null) {
					//
					break;
				}
				
				if (cashBookFile.hasValueOf("id", cashBookId, line)) {
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

	public void write(CashBook cashBook) {
		// 
		if (this.exists(cashBook.getId())) {
			return;
		}
		
		FileWriter fileWriter;
		try {
			fileWriter = cashBookFile.requestFileWriter();
			fileWriter.write(convertToStr(cashBook));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CashBook read(String cashBookId) {
		// 
		CashBook cashBook = null;
		BufferedReader reader = null;
		
		try {
			//
			reader = cashBookFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				//
				if (cashBookFile.hasValueOf("id", cashBookId, line)) {
					cashBook = convertToCashBook(line);
					break;
				}
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return cashBook;
	}

	public CashBook readyByName(String cashBookName) {
		// 
		CashBook cashBook = null;
		BufferedReader reader = null;
		
		try {
			reader = cashBookFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (cashBookFile.hasValueOf("name", cashBookName, line)) {
					cashBook = convertToCashBook(line);
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cashBook;
	}

	public void update(CashBook cashBook) {
		// 
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = cashBookFile.requestReader();
			writer = cashBookTempFile.requestPrintWriter();
			
			String line = null;
			String cashBookId = cashBook.getId();
			
			while ((line = reader.readLine()) != null) {
				if (cashBookFile.hasValueOf("id", cashBookId, line)) {
					line = convertToStr(cashBook);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!cashBookFile.delete()) {
				System.out.println("Could not delete file.");
				return;
			}
			
			if (!cashBookTempFile.renameTo(cashBookFile)) {
				System.out.println("Could not rename file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String cashBookId) {
		// 
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = cashBookFile.requestReader();
			writer = cashBookTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (cashBookFile.hasValueOf("id", cashBookId, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!cashBookFile.delete()) {
				System.out.println("Could not delete file.");
				return;
			}
			
			if (!cashBookTempFile.renameTo(cashBookFile)) {
				System.out.println("Could not rename file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private CashBook convertToCashBook(String readLine) {
		//
		return (CashBook) cashBookFile.convertTo(readLine, CashBook.class);
	}
	
	private String convertToStr(CashBook cashBook) {
		//
		return cashBookFile.convertFrom(cashBook);
	}
}