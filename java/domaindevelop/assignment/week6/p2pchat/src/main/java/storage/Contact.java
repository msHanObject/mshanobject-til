package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class Contact implements ContactInterface {

	private File file;
	private BufferedReader br;
	private LinkedHashMap<String, String> address;
	private LinkedHashMap<String, LinkedHashMap<String, String>> contact;
	private String clientId;
	
	public Contact() {
		file = new File("./src/main/resources/clientList.dat");
		address = new LinkedHashMap<String, String>();
		contact = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	}
	
	public void loadContact() {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("couldn't find a file");
			
		}
		String line;
		String[] clientData = new String[3];
		try {
			while((line = br.readLine()) != null) {
				clientData = line.split(";", 3);
				clientId = clientData[0];
				address.put(clientData[1], clientData[2]);
				contact.put(clientId, address);
			}
		} catch (IOException e) {
			System.err.println("I/O Exception by BufferedReader.readLine()");
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("couldn't close a object of BufferedReader");
			}
		}
		
		
	}

	public void addContact(String clientId, LinkedHashMap<String, String> address) {
		// isExist() invoke
		contact.put(clientId, address);
	}

	public String getClientId() {
		
		return clientId;
	}

	public LinkedHashMap<String, LinkedHashMap<String, String>> getContact() {

		return contact;
	}
	
	//public void isExist();

}
