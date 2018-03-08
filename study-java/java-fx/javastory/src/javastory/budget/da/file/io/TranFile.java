package javastory.budget.da.file.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javastory.budget.entity.budget.Transaction;
import javastory.club.stage3.util.FileDbWrapper;

public class TranFile {
	//
	private static Map<String,Integer> keyIndexMap;
	
	static {
		keyIndexMap = new LinkedHashMap<String,Integer>();
		keyIndexMap.put("id", 0);
		keyIndexMap.put("name", 1);
	}
	
	private FileDbWrapper tranFile;
	private FileDbWrapper tranTempFile;
	
	public TranFile() {
		//
		this.tranFile = new FileDbWrapper(
				"transaction",
				FileConfig.getFileName("Transaction"),
				FileConfig.getDelimiter());
		this.tranTempFile = new FileDbWrapper(
				"transaction",
				FileConfig.getFileName("TransactionTemp"),
				FileConfig.getDelimiter());
		
		this.tranFile.setKeyIndexMap(keyIndexMap);
		this.tranTempFile.setKeyIndexMap(keyIndexMap);
	}
	
	public boolean exists(String tranId) {
		// 
		boolean found = false;
		BufferedReader reader;
		
		try {
			reader = tranFile.requestReader();
			
			String line = null;
			while (true) {
				//
				if ((line = reader.readLine()) == null) {
					//
					break;
				}
				
				if (tranFile.hasValueOf("id", tranId, line)) {
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

	public void write(Transaction tran) {
		//
		if (this.exists(tran.getId())) {
			return;
		}
		
		FileWriter fileWriter;
		try {
			fileWriter = tranFile.requestFileWriter();
			fileWriter.write(convertToStr(tran));
			fileWriter.write("\r\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Transaction read(String tranId) {
		// 
		Transaction transaction = null;
		BufferedReader reader = null;
		
		try {
			reader = tranFile.requestReader();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				//
				if (tranFile.hasValueOf("id", tranId, line)) {
					transaction = convertToTran(line);
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transaction;
	}

	public void update(Transaction tran) {
		// 
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = tranFile.requestReader();
			writer = tranTempFile.requestPrintWriter();
			
			String line = null;
			String tranId = tran.getId();
			
			while ((line = reader.readLine()) != null) {
				if (tranFile.hasValueOf("id", tranId, line)) {
					line = convertToStr(tran);
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!tranFile.delete()) {
				System.out.println("Could not delte file.");
				return;
			}
			
			if (!tranTempFile.renameTo(tranFile)) {
				System.out.println("Could not rename file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String tranId) {
		// 
		BufferedReader reader;
		PrintWriter writer;
		
		try {
			reader = tranFile.requestReader();
			writer = tranTempFile.requestPrintWriter();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (tranFile.hasValueOf("id", tranId, line)) {
					continue;
				}
				writer.println(line);
				writer.flush();
			}
			writer.close();
			reader.close();
			
			if (!tranFile.delete()) {
				System.out.println("Could not delete file.");
				return;
			}
			
			if (!tranTempFile.renameTo(tranFile)) {
				System.out.println("Could not rename file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Transaction convertToTran(String readLine) {
		//
		return (Transaction) tranFile.convertTo(readLine, Transaction.class);
	}
	
	private String convertToStr(Transaction transaction) {
		//
		return tranFile.convertFrom(transaction);
	}
}