package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Contact implements ContactInterface{

	private File file;
	private BufferedReader bufferedReader;
	private HashMap<String, String> address;
	private HashMap<String, HashMap<String, String>> contact;
	private String clientId;
	
	public Contact() {
		file = new File("./src/main/resources/clientList.dat");
		address = new HashMap<String, String>();
		contact = new HashMap<String, HashMap<String, String>>();
	}

	public void setContact() {
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			String[] clientData = new String[3];
			while((line = bufferedReader.readLine()) != null) {
				clientData = line.split(";", 3);
				clientId = clientData[0];
				address.put(clientData[1], clientData[2]);
				contact.put(clientId, address);				
			}
			bufferedReader.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}

	public void addContact(String clientId, HashMap<String, String> address) {
		//isExist() invoke
		contact.put(clientId, address);
	}
	
	public String getClientId() {
		
		return clientId;
	}
	
	public HashMap<String, HashMap<String, String>> getContact() {

		return contact;
	}

	//public void isExist();
}
