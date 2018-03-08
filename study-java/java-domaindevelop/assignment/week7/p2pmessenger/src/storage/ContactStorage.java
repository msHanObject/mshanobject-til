package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ContactStorage {
	
	private File file;
	private LinkedHashMap<String, LinkedHashMap<String, String>> contacts;
	private BufferedReader bufferedReader;
	
	public ContactStorage() {
		file = new File("./resources/contact.dat");
		contacts = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		loadContactFile();
		putContactData();
	}
	
	public void loadContactFile() {
		try {
			//System.out.println("loadContactFile invoked");
			bufferedReader = new BufferedReader(new FileReader(file));			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void putContactData() {
		//System.out.println("putContactData invoked");
		String line;
		String[] contactArray = new String[3];
		try {
			while((line = bufferedReader.readLine()) != null) {
				contactArray = line.split(";", 3);
				String clientId = contactArray[0];
				LinkedHashMap<String, String> address = new LinkedHashMap<String,String>();
				address.put(contactArray[1], contactArray[2]);
				contacts.put(clientId, address);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public LinkedHashMap<String, LinkedHashMap<String, String>> getContacts(){
		return contacts;
	}
}
